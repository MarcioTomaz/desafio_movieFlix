package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.DTO.GenreDTO;
import com.devsuperior.movieflix.DTO.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository repository;

    @Transactional
    public List<GenreDTO> findAllGenres(){

        List<Genre> dto = repository.findAll();

        return dto.stream().map( x -> new GenreDTO(x)).collect(Collectors.toList());
    }
}
