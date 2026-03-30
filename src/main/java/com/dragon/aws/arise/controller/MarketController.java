package com.dragon.aws.arise.controller;


import com.dragon.aws.arise.entity.dto.WeatherResponseDto;
import com.dragon.aws.arise.service.MarketService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class MarketController {
    @Autowired
    MarketService marketService;

    public ResponseEntity<?> getUser(int userId){
        return ResponseEntity.status(201).body(marketService.getUser(userId));
    }
    @GetMapping("/current/{city}")
    public ResponseEntity<String> getCurrentWeather(@PathVariable("city") String city){
        WeatherResponseDto weatherResponseDto=marketService.getCurrentWheather(city);
        int currentTemperature=0;
        if(weatherResponseDto.getCurrent().feelslike!=0){
            currentTemperature=weatherResponseDto.getCurrent().feelslike;
        }
        return new ResponseEntity<>("Hi "+"current Temperature feels like"+currentTemperature, HttpStatusCode.valueOf(200));

    }
}
