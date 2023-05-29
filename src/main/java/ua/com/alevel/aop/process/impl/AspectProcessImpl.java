package ua.com.alevel.aop.process.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import ua.com.alevel.aop.process.AspectProcess;
import ua.com.alevel.service.SearchMovieInfoService;
import ua.com.alevel.util.WebUtil;

import java.util.Map;

@Component
public class AspectProcessImpl implements AspectProcess {

    private final SearchMovieInfoService searchMovieInfoService;

    public AspectProcessImpl(SearchMovieInfoService searchMovieInfoService) {
        this.searchMovieInfoService = searchMovieInfoService;
    }

    @Override
    public Object process(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        if (args != null) {
            if (args[0] instanceof Map<?, ?>) {
                Map<String, Object> queryMap = (Map<String, Object>) args[0];
                Long directorId = (Long) queryMap.get(WebUtil.DIRECTOR_PARAM);
                if (directorId != null) {
                    searchMovieInfoService.process(WebUtil.DIRECTOR_PARAM, directorId);
                }
            }
        }
        return pjp.proceed();
    }
}