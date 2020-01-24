package com.sg.recipe.recipe.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document
public class UnitOfMeasure {//extends BaseEntity{
    @Id
    private String id;
    private String uom;
}
