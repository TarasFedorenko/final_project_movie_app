package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.person.Actor;
import ua.com.alevel.persistence.repository.movie.ActorRepository;
import ua.com.alevel.service.ActorService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Optional<Actor> findById(Long id) {
        return actorRepository.findById(id);
    }

    @Override
    public Collection<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public List<Actor> findAllByMovie(Long movieId) {
        return actorRepository.findAllByMovie(movieId);
    }

    @Override
    public Integer count() {
        return actorRepository.countActors();
    }
}

