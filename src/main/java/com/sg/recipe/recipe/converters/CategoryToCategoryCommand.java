package com.sg.recipe.recipe.converters;

import com.sg.recipe.recipe.command.CategoryCommand;
import com.sg.recipe.recipe.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public CategoryCommand convert(Category source) {
        if(source==null) {
            return null;
        }
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setDescription(categoryCommand.getDescription());
        categoryCommand.setId(categoryCommand.getId());
        return categoryCommand;
    }
}
