package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.person.Director;
import ua.com.alevel.persistence.entity.search.SearchMovieInfo;
import ua.com.alevel.persistence.repository.movie.DirectorRepository;
import ua.com.alevel.persistence.repository.search.SearchMovieInfoRepository;
import ua.com.alevel.service.SearchMovieInfoService;
import ua.com.alevel.util.WebUtil;

import java.util.Optional;


@Service
public class SearchMovieInfoServiceImpl implements SearchMovieInfoService {

    private final DirectorRepository directorRepository;
    private final SearchMovieInfoRepository searchMovieInfoRepository;

    public SearchMovieInfoServiceImpl(DirectorRepository directorRepository, SearchMovieInfoRepository searchMovieInfoRepository) {
        this.directorRepository = directorRepository;
        this.searchMovieInfoRepository = searchMovieInfoRepository;
    }

    @Override
    public void process(String searchParam, Long id) {
        if (searchParam.equals(WebUtil.DIRECTOR_PARAM)) {
            Optional<Director> optionalDirector = directorRepository.findById(id);
            if (optionalDirector.isPresent()) {
                SearchMovieInfo searchMovieInfo = searchMovieInfoRepository.findByDirector(optionalDirector.get().getLastName());
                if (searchMovieInfo == null) {
                    searchMovieInfo = new SearchMovieInfo();
                    searchMovieInfo.setDirector(optionalDirector.get().getLastName());
                    searchMovieInfo.setCountDirector(1L);
                } else {
                    Long count = searchMovieInfo.getCountDirector();
                    ++count;
                    searchMovieInfo.setCountDirector(count);
                }
                searchMovieInfoRepository.save(searchMovieInfo);
            }
        }
    }
}
