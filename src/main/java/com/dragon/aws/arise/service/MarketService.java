package com.dragon.aws.arise.service;

import com.dragon.aws.arise.entity.dto.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MarketService {
    private final String EX_API_KEY = "2fbe86ef02af9da29217231a763e49f5";
    @Autowired
    private RestTemplate restTemplate;
    private String BASE_URL = "http://api.weatherstack.com/current";
    private String API_CALL = "http://api.weatherstack.com/current?access_key=API_KEY&query=API_CITY";

    public Object getUser(int userId) {
        return null;
    }

    public WeatherResponseDto getCurrentWheather(String city) {
        String replaced_Url = API_CALL.replace("API_KEY", EX_API_KEY).replace("API_CITY", city);

        URI uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("access_key", EX_API_KEY)
                .queryParam("query", city) // Handles encoding automatically
                .build()
                .encode()
                .toUri();

        WeatherResponseDto weatherResponseDto = restTemplate
                .exchange(uri, HttpMethod.GET, null, WeatherResponseDto.class).getBody();
        return weatherResponseDto;
    }
}
