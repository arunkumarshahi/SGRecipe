package com.sg.recipe.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Document
public class Category {//extends BaseEntity{
    @Id
    private String id;
    private String description;
//    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude @EqualsAndHashCode.Exclude private Set<Recipe> recipes=new HashSet<Recipe>();
}
