package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;

import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.movie.Movie;

import ua.com.alevel.persistence.repository.movie.MovieRepository;
import ua.com.alevel.service.MovieService;

import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CrudRepositoryHelper<Movie, MovieRepository> crudRepositoryHelper;

    public MovieServiceImpl(MovieRepository movieRepository, CrudRepositoryHelper<Movie, MovieRepository> crudRepositoryHelper) {
        this.movieRepository = movieRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Movie entity) {
        crudRepositoryHelper.create(movieRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Movie entity) {
        crudRepositoryHelper.update(movieRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(movieRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> findById(Long id) {
        return crudRepositoryHelper.findById(movieRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Movie> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(movieRepository, request);
    }

    @Override
    public Integer count() {
        return movieRepository.countMovies();
    }
}
