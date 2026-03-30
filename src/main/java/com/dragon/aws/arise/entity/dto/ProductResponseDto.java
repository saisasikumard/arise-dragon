package com.dragon.aws.arise.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

        public int id;
        public String title;
        public double price;
        public String description;
        public String category;
        public String image;
    }
