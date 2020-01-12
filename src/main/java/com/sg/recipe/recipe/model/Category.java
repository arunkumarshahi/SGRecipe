package com.sg.recipe.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude @EqualsAndHashCode.Exclude private Set<Recipe> recipes=new HashSet<Recipe>();
}
