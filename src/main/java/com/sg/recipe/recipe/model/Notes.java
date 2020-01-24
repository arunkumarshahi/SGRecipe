package com.sg.recipe.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@EqualsAndHashCode(exclude = {"recipe"})
@ToString(exclude = {"recipe"})
@Document
public class Notes {//extends BaseEntity{
    @Id
    private String id;
//    @OneToOne
//    private Recipe recipe;
    private String recipeNotes;
}
