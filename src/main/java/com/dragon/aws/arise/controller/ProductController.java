package com.dragon.aws.arise.controller;

import com.dragon.aws.arise.entity.dto.ProductResponseDto;
import com.dragon.aws.arise.entity.dto.WeatherResponseDto;
import com.dragon.aws.arise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("id") int id){
        ProductResponseDto productResponseDto=productService.getProduct(id);

        return new ResponseEntity<>(productResponseDto, HttpStatusCode.valueOf(200));

    }
}
