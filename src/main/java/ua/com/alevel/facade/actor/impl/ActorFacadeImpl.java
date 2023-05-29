package ua.com.alevel.facade.actor.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.actor.ActorFacade;
import ua.com.alevel.persistence.entity.person.Actor;
import ua.com.alevel.service.ActorService;
import ua.com.alevel.web.dto.request.ActorRequestDto;
import ua.com.alevel.web.dto.response.ActorResponseDto;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class ActorFacadeImpl implements ActorFacade {
    private final ActorService actorService;

    public ActorFacadeImpl(ActorService actorService) {
        this.actorService = actorService;
    }

    public ActorResponseDto findById(Long id) {
        Optional<Actor> actorOptional = actorService.findById(id);
        if (actorOptional.isEmpty()) {
            throw new RuntimeException("actor not exist");
        }
        Actor actor = actorOptional.get();
        return new ActorResponseDto(actor);
    }

    @Override
    public Collection<ActorRequestDto> findAll(WebRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        if (MapUtils.isNotEmpty(map)) {
            String[] movies = map.get("movies");
            if (movies != null) {
                String movieId = movies[0];
                return actorService.findAllByMovie(Long.parseLong(movieId)).stream().map(ActorRequestDto::new).toList();
            }
        }
        Collection<Actor> actors = actorService.findAll();
        return actors.stream().map(ActorRequestDto::new).toList();
    }
}
