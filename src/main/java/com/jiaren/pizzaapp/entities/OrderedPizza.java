package com.jiaren.pizzaapp.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.jiaren.pizzaapp.types.JpaToJsonConverter;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@RequiredArgsConstructor
public class OrderedPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "info", nullable = false)
    @Convert(converter = JpaToJsonConverter.class)
    private Map<String, Object> info;


}