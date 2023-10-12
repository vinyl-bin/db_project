package db_project.db_project.service;

import db_project.db_project.domain.User;
import db_project.db_project.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@Rollback(value = false)
public class UserServiceTest {


    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 유저등록() throws Exception {
        //given
        User user = new User();
        user.setName("test1");
        user.setPassword("test1password");

        //when
        Long savedId = userService.join(user);

        //then
        em.flush();
        assertEquals(user, userRepository.findOne(savedId));

    }

    @Test(expected = IllegalStateException.class)      //예외 처리하는 테스트는 꼭 이거 넣어줘야함!!!
    public void 유저_패스워드_불일치() throws Exception {
        //given
        User user = new User();
        user.setName("test1");
        user.setPassword("1234");
        Long savedId = userService.join(user);
        em.persist(user);


        //when
        String userName = user.getName();
        String userPassword = user.getPassword();
        String wrongPassword = "4443";
        userService.checkPassword(userName, wrongPassword);  //예외가 발생해야한다!!

        //then
        fail("예외가 발생해야 한다. 혹은 유저와 패스워드가 일치한다.");

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        User user1 = new User();
        user1.setName("Kim");
        user1.setPassword("1234");

        User user2 = new User();
        user2.setName("Kim");
        user2.setPassword("1234");

        //when
        userService.join(user1);
        userService.join(user2);   //예외가 발생해야 함.

        //then
        fail("예외가 발생해야 함.");

    }


}