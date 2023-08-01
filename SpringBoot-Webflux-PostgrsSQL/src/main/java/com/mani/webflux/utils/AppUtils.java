package com.mani.webflux.utils;

import com.mani.webflux.dto.ProductDto;
import com.mani.webflux.entity.ProductEntity;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static ProductDto entityToDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productEntity, productDto);
        return productDto;
    }

    public static ProductEntity dtoToEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);
        return productEntity;
    }

}
