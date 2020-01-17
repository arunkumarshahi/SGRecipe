package com.sg.recipe.recipe.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService  {
    public void saveImageFile(Long recipeId, MultipartFile file) throws IOException;
}
