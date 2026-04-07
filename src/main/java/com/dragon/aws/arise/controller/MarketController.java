package com.dragon.aws.arise.controller;


import com.dragon.aws.arise.entity.dto.WeatherResponseDto;
import com.dragon.aws.arise.service.MarketService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market")
public class MarketController {
    @Autowired
    MarketService marketService;

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam("userId") int userId){
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
