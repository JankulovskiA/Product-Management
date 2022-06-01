package com.example.ordermanagement.web;

import com.example.ordermanagement.domain.exceptions.ProductDoesNotExitsException;
import com.example.ordermanagement.domain.valueobjects.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

@Service
public class ProductClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public ProductClient(@Value("${app.product.inventory.url}") String serverUrl) {
        this.restTemplate = new RestTemplate();
        this.serverUrl = serverUrl;
        var requestFactory=new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri()
    {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Product> findAll(){
        try {
            return restTemplate.exchange(uri().path("/api/product").build().toUri(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Product>>(){}).getBody();
        } catch (Exception e){
            return Collections.emptyList();
        }
    }
    public Product findById(Long productId){
        return this.findAll().stream()
                .filter(p->p.getId().equals(productId)).findFirst()
                .orElseThrow(()->new ProductDoesNotExitsException(productId));
    }
}
