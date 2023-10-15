package db_project.db_project.service;

import db_project.db_project.domain.Board;
import db_project.db_project.domain.BoardFeed;
import db_project.db_project.domain.Feed;
import db_project.db_project.domain.User;
import db_project.db_project.repository.BoardFeedRepositoryImpl;
import db_project.db_project.repository.BoardRepository;
import db_project.db_project.repository.FeedRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.constraints.Null;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@Rollback(value = false)
public class BoardServiceTest {

    @Autowired EntityManager em;
    @Autowired FeedService feedService;
    @Autowired BoardService boardService;
    @Autowired BoardRepository boardRepository;
    @Autowired BoardFeedRepositoryImpl boardFeedRepository;

    @Test
    public void 보드_작성() throws Exception{
        //given
        User user = createUser();
        Long user_id = user.getUser_id();

        Feed feed1 = createFeed("감자 -> 삶은 감자", "감자", "농산물");
        Feed feed2 = createFeed("고구마 -> 삶은 고구마", "고구마", "농산물");


        //when
        Long boardFeed_id = boardService.writeBoard("테스트 감자 레시피", "건강에 좋아요", user_id, feed1.getFeed_id());

        //then
        BoardFeed boardFeed = boardFeedRepository.findOne(boardFeed_id);
        assertEquals("board feedClNm", "감자 -> 삶은 감자", boardFeed.getFeed().getFeedClCodeNm());
        assertEquals("board 작성자 강다빈", "강다빈", boardFeed.getBoard().getUser().getName());
        assertEquals("board 비밀번호", "123245", boardFeed.getBoard().getUser().getPassword());
        assertEquals("board title", "테스트 감자 레시피", boardFeed.getBoard().getTitle());


    }

    private User createUser() {
        User user = new User();
        user.setName("강다빈");
        user.setPassword("123245");
        em.persist(user);
        return user;
    }

    private Feed createFeed(String small, String middle, String big) {
        Feed feed = new Feed();
        feed.setFeedClCodeNm(small);    //상세 이름
        feed.setFeedClCode(middle);      // 소분류
        feed.setUpperFeedClCode(big); // 대분류
        em.persist(feed);
        return feed;
    }

//    @Test
//    public void 보드_수정() throws Exception {
//        //given
//        User user = createUser();
//        Long user_id = user.getUser_id();
//
//        Feed feed1 = createFeed("감자 -> 삶은 감자", "감자", "농산물");
//        Feed feed2 = createFeed("고구마 -> 삶은 고구마", "고구마", "농산물");
//
//        Long boardFeed_id = boardService.writeBoard("테스트 감자 레시피", "건강에 좋아요", user_id, feed1.getFeed_id());
//
//        //when
//
//
//
//        //then
//
//    }
}