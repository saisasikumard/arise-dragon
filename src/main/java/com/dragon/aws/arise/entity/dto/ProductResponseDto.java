package com.dragon.aws.arise.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

        public int id;
        public String title;
        public double price;
        public String description;
        public String category;
        public List<String> images;
    }
