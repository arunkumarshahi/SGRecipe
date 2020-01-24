package com.sg.recipe.recipe.controller;

import com.sg.recipe.recipe.command.RecipeCommand;
import com.sg.recipe.recipe.repositories.RecipeRepository;
import com.sg.recipe.recipe.service.ImageService;
import com.sg.recipe.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
@Slf4j
public class ImageController {
    private final RecipeService recipeService;
    private final ImageService imageService;
    public ImageController( RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;

        this.imageService = imageService;
    }

    @GetMapping("recipe/{id}/upload")
    public String getUploadFile(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/imageUploadForm";
    }

    @PostMapping("recipe/{id}/image")
    public String postImage(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
        log.debug("postImage :: --> ", id);
        try {
            imageService.saveImageFile(id,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/recipe/" + id + "/show";
    }
    //th:src="@{'/recipe/' + ${recipe.id} + '/recipeimage'}"
    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response){
        RecipeCommand recipeCommand=recipeService.findCommandById(id);
        Byte[] image = recipeCommand.getImage();
        byte[] byteArray=new byte[image.length];
        int index = 0;
        for(Byte imageByte:image){
            byteArray[index++]=imageByte;
        }
        response.setContentType("image/jpeg");
        try {
            IOUtils.copy(new ByteArrayInputStream(byteArray),response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
