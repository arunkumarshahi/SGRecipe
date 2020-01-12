package com.sg.recipe.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @ToString.Exclude @EqualsAndHashCode.Exclude private Recipe recipe;
    private String recipeNotes;
}
