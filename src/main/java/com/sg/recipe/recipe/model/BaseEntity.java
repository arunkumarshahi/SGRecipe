package com.sg.recipe.recipe.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

//@MappedSuperclass
@Data
public class BaseEntity implements Serializable {
    @Id
    private String id;
    public boolean isNew(){
        return id==null;
    }
}