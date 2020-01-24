package com.sg.recipe.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = {"image", "ingredients"})
@ToString(exclude = {"image"})
@Slf4j
@Document
public class Recipe { //extends BaseEntity{
    @Id
    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    //     @Lob
    private Byte[] image;

    public void setNotes(Notes notes) {
        log.debug("notes found here  ..", notes);
        // notes.setRecipe(this);
        this.notes = notes;
    }

    //    @OneToOne(cascade= CascadeType.ALL)
//    @DBRef
    private Notes notes;
    //    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    @DBRef
    private Set<Ingredient> ingredients = new HashSet<Ingredient>();
    //    @Enumerated(value=EnumType.STRING)
    private Difficulty difficulty;
    //    @ManyToMany
//    @JoinTable(name="recipe_category", joinColumns = @JoinColumn (name="recipe_id"),inverseJoinColumns = @JoinColumn(name="category_id"))
    @DBRef
    private Set<Category> categories = new HashSet<Category>();
    ;

}
