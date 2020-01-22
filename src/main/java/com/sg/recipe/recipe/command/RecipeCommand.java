package com.sg.recipe.recipe.command;

import com.sg.recipe.recipe.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;

    @Size(min = 3,max = 255)
    private String description;
    @Min(3)
    @Max(100)
    @NotNull
    private Integer prepTime;
    @Min(3)
    @Max(100)
    @NotNull
    private Integer cookTime;
    @Min(10)
    @Max(30)
    @NotNull
    private Integer servings;
    private String source;
    @URL
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();
    private Byte[] image;
}
