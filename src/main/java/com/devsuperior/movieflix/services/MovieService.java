package com.devsuperior.movieflix.services;


import com.devsuperior.movieflix.DTO.GenreDTO;
import com.devsuperior.movieflix.DTO.MovieDTO;
import com.devsuperior.movieflix.DTO.MovieListDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;


    @Transactional
    public Page<MovieListDTO> findByGenre(Long genreId, Pageable pageable){

        Genre genre = ( genreId == 0 ) ? null : genreRepository.getOne(genreId);

        Page<Movie> page = movieRepository.findByGenre(genre, pageable);
        movieRepository.findMoviesAndGenres(page.getContent());

        return page.map( x -> new MovieListDTO(x));
    }

    @Transactional
    public MovieDTO findById(Long id){

        Optional<Movie> obj = movieRepository.findById(id);
        Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Filme nao encontrado"));

        return new MovieDTO(entity);
    }


}
