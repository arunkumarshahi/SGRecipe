package com.sg.recipe.recipe.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService  {
    public void saveImageFile(String recipeId, MultipartFile file) throws IOException;
}
