package db_project.db_project.repository;


import db_project.db_project.domain.Feed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FeedRepository {

    private final EntityManager em;

    public void save(Feed feed) {
        em.persist(feed);
    }

    public Feed findOne(long feed_id) {
        return em.find(Feed.class, feed_id);
    }

    public List<Feed> findAll() {
        return em.createQuery("select f from Feed f", Feed.class)
                .getResultList();
    }
}
