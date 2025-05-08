package com.hmsapp.controller;


import com.hmsapp.entity.Property;
import com.hmsapp.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    @Autowired
    private PropertyRepository propertyRepository;
    @PostMapping  ("/addProperty")
    public String addProperty(){

        return "added";
    }
    @DeleteMapping ("/deleteProperty")
    public String deleteProperty(){

        return "delete";
    }
    //http://localhost:8080/v1/property/{searchParam}
    @GetMapping("/{searchParam}")
   public List<Property> searchProperty(
           @PathVariable String searchParam
    ){
       return propertyRepository.searchProperty(searchParam);
   }
}
