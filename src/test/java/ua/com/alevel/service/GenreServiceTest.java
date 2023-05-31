package ua.com.alevel.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.persistence.entity.movie.Genre;
import ua.com.alevel.persistence.repository.movie.GenreRepository;
import ua.com.alevel.service.impl.GenreServiceImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GenreServiceTest {

    private final Long id1 = 1L;
    private final Long id2 = 2L;
    private final Long id3 = 3L;
    private final String action = "Action";
    private final String comedy = "Comedy";
    private final String drama = "Drama";

    @Test
    public void testFindById() {
        GenreRepository genreRepositoryMock = mock(GenreRepository.class);
        Genre expectedGenre = new Genre();
        expectedGenre.setId(id1);
        expectedGenre.setGenreName(action);

        when(genreRepositoryMock.findById(1L)).thenReturn(Optional.of(expectedGenre));

        GenreService genreService = new GenreServiceImpl(genreRepositoryMock);

        Optional<Genre> result = genreService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(expectedGenre, result.get());
    }

    @Test
    public void testFindAll() {
        // Создание заглушки репозитория
        GenreRepository genreRepositoryMock = mock(GenreRepository.class);
        List<Genre> expectedGenres = Arrays.asList(
                new Genre(),
                new Genre(),
                new Genre()
        );
        expectedGenres.get(0).setId(id1);
        expectedGenres.get(0).setGenreName(action);
        expectedGenres.get(1).setId(id2);
        expectedGenres.get(1).setGenreName(comedy);
        expectedGenres.get(2).setId(id3);
        expectedGenres.get(2).setGenreName(drama);

        when(genreRepositoryMock.findAll()).thenReturn(expectedGenres);

        GenreService genreService = new GenreServiceImpl(genreRepositoryMock);

        Collection<Genre> result = genreService.findAll();

        assertEquals(expectedGenres.size(), result.size());
        assertTrue(result.containsAll(expectedGenres));
    }

    @Test
    public void testFindAllByMovie() {
        // Создание заглушки репозитория
        GenreRepository genreRepositoryMock = mock(GenreRepository.class);
        Long movieId = 1L;
        List<Genre> expectedGenres = Arrays.asList(
                new Genre(),
                new Genre()
        );
        expectedGenres.get(0).setId(id1);
        expectedGenres.get(0).setGenreName(action);
        expectedGenres.get(1).setId(id2);
        expectedGenres.get(1).setGenreName(comedy);

        // Определение поведения заглушки репозитория
        when(genreRepositoryMock.findAllByMovie(movieId)).thenReturn(expectedGenres);

        // Создание объекта GenreServiceImpl с заглушкой репозитория
        GenreService genreService = new GenreServiceImpl(genreRepositoryMock);

        // Вызов метода для тестирования
        List<Genre> result = genreService.findAllByMovie(movieId);

        // Проверка результата
        assertEquals(expectedGenres.size(), result.size());
        assertTrue(result.containsAll(expectedGenres));
    }
}
