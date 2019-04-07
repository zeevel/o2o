package com.zeevel.o2o.service.impl;

import com.zeevel.o2o.dao.ProductDao;
import com.zeevel.o2o.dao.ProductImgDao;
import com.zeevel.o2o.dto.ImageHolder;
import com.zeevel.o2o.dto.ProductExecution;
import com.zeevel.o2o.entity.Product;
import com.zeevel.o2o.entity.ProductImg;
import com.zeevel.o2o.enums.ProductStateEnum;
import com.zeevel.o2o.exceptions.ProductCategoryOperationException;
import com.zeevel.o2o.service.ProductService;
import com.zeevel.o2o.util.ImageUtil;
import com.zeevel.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    //1.处理缩略图，获取缩略图相对路径并复制给product
    //2.往tb_product写入商品信息,获取productId
    //3.结合productId批量处理商品详情图
    //4.将商品详情图列表批量插入tb_product_img中
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
            throws RuntimeException {
        if(product!=null&&product.getShop()!=null&&product.getShop().getShopId()!=null){
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            //默认为上架的状态
            product.setEnableStatus(1);
            if(thumbnail!=null){
                addThumbnail(product,thumbnail);
            }
            try{
                //创建商品信息
                int effectedNum = productDao.insertProduct(product);//product 会获得productId
                if(effectedNum <= 0){
                    throw new RuntimeException("创建商品失败");
                }
            }catch (Exception e){
                throw new RuntimeException("创建商品失败: " + e.toString());
            }
            if(productImgHolderList!=null&&productImgHolderList.size()>0){
                addProductImgList(product,productImgHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS,product);
        }else{
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public Product getProductById(Long productId) {
        return productDao.queryProductByProductId(productId);
    }

    /**
     * 添加缩略图
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail,dest);
        product.setImgAddr(thumbnailAddr);
    }

    /**
     * 批量添加详情图片
     * @param product
     * @param productImgHolderList
     */
    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        //遍历图片列表，并添加进productImg实体类里
        for(ImageHolder productImgHolder:productImgHolderList){
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder,dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        //如果确实是有图片需要添加，执行此操作
        if(productImgList.size() > 0){
            try{
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if(effectedNum <= 0){
                    throw new RuntimeException("创建商品详情图片失败");
                }
            }catch (Exception e){
                throw new RuntimeException("创建商品详情图片失败:" + e.toString());
            }
        }
    }

}
