package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.movie.Genre;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.repository.movie.GenreRepository;
import ua.com.alevel.persistence.repository.movie.MovieRepository;
import ua.com.alevel.service.GenreService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public Collection<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> findAllByMovie(Long movieId) {
        return genreRepository.findAllByMovie(movieId);
    }
}
