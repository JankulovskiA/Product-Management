package com.example.ordermanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Random;
import java.util.random.RandomGenerator;

@Getter
public class Product {
    Long id;
    String name;
    Integer availability;

    public Product()
    {
        this.id= RandomGenerator.getDefault().nextLong();
        this.name="";
        this.availability=0;
    }

    @JsonCreator
    public Product(@JsonProperty("id") Long id, @JsonProperty("name") String name,
                   @JsonProperty("availability") Integer availability){
        this.id=id;
        this.name=name;
        this.availability=availability;
    }
}
