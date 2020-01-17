package com.sg.recipe.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(exclude = {"image","ingredients"})
@ToString(exclude = {"image","ingredients"})
@Slf4j
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
     @Lob
    private Byte[] image;

    public void setNotes(Notes notes) {
        log.debug("notes found here  ..",notes);
        notes.setRecipe(this);
        this.notes = notes;
    }

    @OneToOne(cascade= CascadeType.ALL)
    private Notes notes;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")

    private Set<Ingredient> ingredients=new HashSet<Ingredient>();
    @Enumerated(value=EnumType.STRING)
    private Difficulty difficulty;
    @ManyToMany
    @JoinTable(name="recipe_category", joinColumns = @JoinColumn (name="recipe_id"),inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories=new HashSet<Category>();;

}
