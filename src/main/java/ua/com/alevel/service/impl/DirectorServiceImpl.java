package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.person.Director;
import ua.com.alevel.persistence.repository.movie.DirectorRepository;
import ua.com.alevel.service.DirectorService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public Optional<Director> findById(Long id) {
        return directorRepository.findById(id);
    }

    @Override
    public Collection<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public List<Director> findAllByMovie(Long movieId) {
        return directorRepository.findAllByMovie(movieId);
    }

    @Override
    public Integer count() {
        return directorRepository.countDirectors();
    }


}
