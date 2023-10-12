package db_project.db_project.repository;

import db_project.db_project.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor     //private final로 간단히 할 수 있는 것
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long user_id) {
        return em.find(User.class, user_id);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public List<User> findByName(String name) {
        return em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<User> findByPassword(String password) {
        return em.createQuery("select u from User u where u.password = :password", User.class)
                .setParameter("password", password)
                .getResultList();
    }

}
