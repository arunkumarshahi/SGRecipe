package com.sg.recipe.recipe.service;

import com.sg.recipe.recipe.model.Recipe;
import com.sg.recipe.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(String recipeId, MultipartFile file) throws IOException {
        log.debug("recipe id ::"+recipeId);
        log.debug("recipe id ::"+file);
        Byte[] byteArray=new Byte[file.getBytes().length];
        int index=0;
        for(byte b:file.getBytes()){
            byteArray[index++]=b;
        }
        Recipe recipe=recipeRepository.findById(recipeId).orElseThrow(()-> new RuntimeException("no recipe found !"));
        recipe.setImage(byteArray);
        recipeRepository.save(recipe);
    }
}
