package ua.com.alevel.persistence.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Subscriber;

import java.util.List;

@Repository
public interface SubscriberRepository extends UserRepository<Subscriber> {
    @Query("SELECT COUNT(*) FROM User WHERE roleType = 'ROLE_SUBSCRIBER'")
    Integer countSubscribers();

    @Query("from Subscriber g join g.movies gm where gm.id =: movieId")
    List<Subscriber> findAllByMovie(Long movieId);





}