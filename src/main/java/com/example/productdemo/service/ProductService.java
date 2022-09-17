package com.example.productdemo.service;

import com.datastax.oss.driver.shaded.guava.common.reflect.TypeToken;
import com.example.productdemo.dto.request.ProductRequestDTO;
import com.example.productdemo.dto.response.*;
import com.example.productdemo.enums.Responses;
import com.example.productdemo.model.Product;
import com.example.productdemo.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ProductRepository productRepository;

    //region Add New Product
    public ProductSaveResponseDTO addProduct(ProductRequestDTO newProduct) {
        //return objesi olusturulur
        ProductSaveResponseDTO responseDTO = new ProductSaveResponseDTO();
        try{

           /* Product  product = new Product();
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setQuantity(newProduct.getQuantity());*/

            //dto modele map edilir. Yukarıdaki sekilde de yapılabilir.
            Product product=  modelMapper.map(newProduct, Product.class);

            //Kullanıcıya kayıt islemi sonrası notification numarası dönülür
            UUID notificationId = UUID.randomUUID();
            UUID productId = UUID.randomUUID();
            product.setNotification(notificationId.toString());

            product.setId(productId.toString());
            product.setActive(true);

            //kayıt islemi yapılır
            Product savedProduct = productRepository.save(product);

            if(savedProduct!=null){
                responseDTO.setDescription(Responses.SAVE_OPERATION_SUCCESS.getDescription());
                responseDTO.setResult(Responses.SAVE_OPERATION_SUCCESS.getCode());
                responseDTO.setNotificationId(savedProduct.getNotification());
            }
            else{
                responseDTO.setDescription(Responses.SAVE_OBJECT_NULL.getDescription());
                responseDTO.setResult(Responses.SAVE_OBJECT_NULL.getCode());
                responseDTO.setNotificationId(null);
            }

        }catch (Exception e){
            responseDTO.setResult(Responses.SYSTEM_ERROR.getCode());
            responseDTO.setDescription(Responses.SYSTEM_ERROR.getDescription());
        }

        return  responseDTO;
    }
    //endregion

    //region Get Product By Id
    public ProductGetResponseDTO getProductById(String notificationId) {
        //return objesi olusturulur
        ProductGetResponseDTO responseDTO = new ProductGetResponseDTO();
        try{
            //veritabanından kullanıcının notification bilgisini gönderdigi ürün bulunur
            Product foundProduct = productRepository.findProductByNotificationAndActiveTrue(notificationId);


           /* ProductDTO productDTO = new ProductDTO();
            productDTO.setName(foundProduct.getName());
            productDTO.setDescription(foundProduct.getDescription());
            productDTO.setQuantity(foundProduct.getQuantity());
            productDTO.setNotification(foundProduct.getNotification());*/

            //model dtoya maplenir
            ProductDTO productDTO=  modelMapper.map(foundProduct, ProductDTO.class);


            if(foundProduct!=null){
                responseDTO.setDescription(Responses.GET_OPERATION_SUCCESS.getDescription());
                responseDTO.setResult(Responses.GET_OPERATION_SUCCESS.getCode());
                responseDTO.setProductDTO(productDTO);
            }
            else{
                responseDTO.setDescription(Responses.GET_OPERATION_FAILURE.getDescription());
                responseDTO.setResult(Responses.GET_OPERATION_FAILURE.getCode());
                responseDTO.setProductDTO(null);
            }

        }catch (Exception e){
            responseDTO.setResult(Responses.SYSTEM_ERROR.getCode());
            responseDTO.setDescription(Responses.SYSTEM_ERROR.getDescription());
        }

        return  responseDTO;
    }
    //endregion

    //region Get All Products
    public ProductListResponseDTO getAllProducts() {
        //return objesi olusturulur
        ProductListResponseDTO responseDTO = new ProductListResponseDTO();
        try{

            //aktif olan yani silinmeyen ürünler bulunur.
            List<Product> foundProductList = productRepository.findAllByActiveTrue();


            if(foundProductList!=null && foundProductList.size()>0){

                //bulunan ürünler dtoya map edilir. List oldugu icin list tipi bulunur
                Type listType = new TypeToken<List<ProductDTO>>(){}.getType();
                List<ProductDTO> dtoList = modelMapper.map(foundProductList,listType);

                responseDTO.setDescription(Responses.GET_OPERATION_SUCCESS.getDescription());
                responseDTO.setResult(Responses.GET_OPERATION_SUCCESS.getCode());
                responseDTO.setProductList(dtoList);
            }
            else{
                responseDTO.setDescription(Responses.GET_OPERATION_FAILURE.getDescription());
                responseDTO.setResult(Responses.GET_OPERATION_FAILURE.getCode());
                responseDTO.setProductList(null);
            }

        }catch (Exception e){
            responseDTO.setResult(Responses.SYSTEM_ERROR.getCode());
            responseDTO.setDescription(Responses.SYSTEM_ERROR.getDescription());
        }

        return  responseDTO;
    }
    //endregion

    //region Update Product
    public ProductSaveResponseDTO updateProduct(ProductDTO product) {
        //return objesi olusturulur
        ProductSaveResponseDTO responseDTO = new ProductSaveResponseDTO();
        try{
            //veritabanından update edilecek ürün bulunur
            Product foundProduct = productRepository.findProductByNotificationAndActiveTrue(product.getNotification());

            if(foundProduct!=null){

                //ürünün yeni bilgileri setlenir
                foundProduct.setName(product.getName());
                foundProduct.setDescription(product.getDescription());
                foundProduct.setQuantity(product.getQuantity());
                foundProduct.setOrigin(product.getOrigin());

                //update edilir
                Product savedProduct = productRepository.save(foundProduct);


                responseDTO.setDescription(Responses.UPDATE_OPERATION_SUCCESS.getDescription());
                responseDTO.setResult(Responses.UPDATE_OPERATION_SUCCESS.getCode());
                responseDTO.setNotificationId(savedProduct.getNotification());
            }
            else{
                responseDTO.setDescription(Responses.UPDATE_OPERATION_NULL.getDescription());
                responseDTO.setResult(Responses.UPDATE_OPERATION_NULL.getCode());
                responseDTO.setNotificationId(null);
            }

        }catch (Exception e){
            responseDTO.setResult(Responses.SYSTEM_ERROR.getCode());
            responseDTO.setDescription(Responses.SYSTEM_ERROR.getDescription());
        }

        return  responseDTO;

    }
    //endregion

    //region Delete Product By Id
    public ProductDeleteResponseDTO deleteProductById(String productNotificationId) {
        //return objesi olusturulur
        ProductDeleteResponseDTO responseDTO = new ProductDeleteResponseDTO();
        try{
            //silinecek ürün bulunur
            Product foundProduct = productRepository.findProductByNotificationAndActiveTrue(productNotificationId);
            //ürünün aktifliği false yapılır.
            foundProduct.setActive(false);
            Product deletedProduct = productRepository.save(foundProduct);


            if(deletedProduct!=null){
                responseDTO.setDescription(Responses.DELETE_OPERATION_SUCCESS.getDescription());
                responseDTO.setResult(Responses.DELETE_OPERATION_SUCCESS.getCode());
            }
            else{
                responseDTO.setDescription(Responses.DELETE_OPERATION_FAILURE.getDescription());
                responseDTO.setResult(Responses.DELETE_OPERATION_FAILURE.getCode());
            }

        }catch (Exception e){
            responseDTO.setResult(Responses.SYSTEM_ERROR.getCode());
            responseDTO.setDescription(Responses.SYSTEM_ERROR.getDescription());
        }

        return  responseDTO;
    }
    //endregion
}
