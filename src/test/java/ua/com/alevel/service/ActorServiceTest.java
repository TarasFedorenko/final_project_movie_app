package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.persistence.entity.person.Actor;
import ua.com.alevel.persistence.repository.movie.ActorRepository;
import ua.com.alevel.service.impl.ActorServiceImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActorServiceTest {

    private final Long id = 1L;
    private final Long id2 = 1L;
    private final String firstName = "John";
    private final String lastName = "Doe";
    private final String firstName2 = "Jane";
    private final String lastName2 = "Smith";

    @Test
    @Order(1)
    public void testFindById() {
        // Arrange
        ActorRepository actorRepository = Mockito.mock(ActorRepository.class);
        ActorService actorService = new ActorServiceImpl(actorRepository);
        Actor actor = new Actor();
        actor.setId(id);
        actor.setFirstName(firstName);
        actor.setLastName(lastName);

        Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));

        // Act
        Optional<Actor> result = actorService.findById(id);

        // Assert
        Assertions.assertEquals(Optional.of(actor), result);
    }

    @Test
    @Order(2)
    public void testFindAll() {
        // Arrange
        ActorRepository actorRepository = Mockito.mock(ActorRepository.class);
        ActorService actorService = new ActorServiceImpl(actorRepository);
        List<Actor> actors = Arrays.asList(
                new Actor(),
                new Actor()
        );
        actors.get(0).setId(id);
        actors.get(0).setFirstName(firstName);
        actors.get(0).setLastName(lastName);

        actors.get(1).setId(id2);
        actors.get(1).setFirstName(firstName2);
        actors.get(1).setLastName(lastName2);

        Mockito.when(actorRepository.findAll()).thenReturn(actors);

        // Act
        Collection<Actor> result = actorService.findAll();

        // Assert
        Assertions.assertEquals(actors, result);
    }

    @Test
    @Order(3)
    public void testFindAllByMovie() {
        // Arrange
        ActorRepository actorRepository = Mockito.mock(ActorRepository.class);
        ActorService actorService = new ActorServiceImpl(actorRepository);
        Long movieId = 1L;
        List<Actor> actors = Arrays.asList(
                new Actor(),
                new Actor()
        );
        actors.get(0).setId(id);
        actors.get(0).setFirstName(firstName);
        actors.get(0).setLastName(lastName);

        actors.get(1).setId(id2);
        actors.get(1).setFirstName(firstName2);
        actors.get(1).setLastName(lastName2);

        Mockito.when(actorRepository.findAllByMovie(movieId)).thenReturn(actors);

        // Act
        List<Actor> result = actorService.findAllByMovie(movieId);

        // Assert
        Assertions.assertEquals(actors, result);
    }

    @Test
    @Order(4)
    public void testCount() {
        // Arrange
        ActorRepository actorRepository = Mockito.mock(ActorRepository.class);
        ActorService actorService = new ActorServiceImpl(actorRepository);
        int count = 5;

        Mockito.when(actorRepository.countActors()).thenReturn(count);

        // Act
        int result = actorService.count();

        // Assert
        Assertions.assertEquals(count, result);
    }
}
