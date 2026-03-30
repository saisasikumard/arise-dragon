package com.dragon.aws.arise.service;

import com.dragon.aws.arise.entity.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ProductService {
    @Autowired
    RestTemplate restTemplate;
    private final String API_BASE_URL="https://fakestoreapi.com/products/";
    public ProductResponseDto getProduct(int id) {
        URI uri= UriComponentsBuilder.fromUriString(API_BASE_URL+id).build().encode().toUri();
        ResponseEntity<ProductResponseDto> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, ProductResponseDto.class);
        return  exchange.getBody();
    }
}
