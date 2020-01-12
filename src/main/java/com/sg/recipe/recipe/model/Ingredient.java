package com.sg.recipe.recipe.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@Entity
@Accessors(chain = true)
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    private Recipe recipe;
    @OneToOne
    private UnitOfMeasure uom;
}
