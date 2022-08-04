package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.DTO.GenreDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource {

    @Autowired
    private MovieService service;

    @GetMapping()
    public ResponseEntity<List<GenreDTO>> findAll(){

        List<GenreDTO> result = service.findAllCategories();

        return ResponseEntity.ok().body(result);

    }
}
