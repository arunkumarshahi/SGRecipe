package com.sg.recipe.recipe.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@Data
@Accessors(chain = true)
@Document
public class Ingredient {//extends BaseEntity{
    @Id
    private String id;
    private String description;
    private BigDecimal amount;
//    @ManyToOne
//    private Recipe recipe;
//    @OneToOne
    @DBRef
    private UnitOfMeasure uom;
}
