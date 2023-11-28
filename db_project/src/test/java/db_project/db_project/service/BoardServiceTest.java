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

//    @Test
//    public void 보드_작성() throws Exception{
//        //given
//        User user = createUser();
//        Long user_id = user.getUser_id();
//
//        Feed feed1 = createFeed("감자 -> 삶은 감자", "감자", "농산물");
//        Feed feed2 = createFeed("고구마 -> 삶은 고구마", "고구마", "농산물");
//        Feed feed3 = createFeed("가자미 -> 생물 가자미", "가자미", "수산물");
//        Feed feed4 = createFeed("찹쌀 -> 찹쌀", "찹쌀", "농산물");
//        Feed feed5 = createFeed("소고기 -> 삶은 소고기", "소고기", "축산물");
//
//
//        //when
//        Board boards = boardService.writeBoard("테스트 감자 레시피", "건강에 좋아요", user_id, feed1.getFeed_id());
//        Long boardFeed_id = boards.getBoardFeed().getBoardFeed_id();
//
//        //then
//        BoardFeed boardFeed = boardFeedRepository.findOne(boardFeed_id);
//        assertEquals("board feedClNm", "감자 -> 삶은 감자", boardFeed.getFeed().getFeedClCodeNm());
//        assertEquals("board 작성자 강다빈", "강다빈", boardFeed.getBoard().getUser().getName());
//        assertEquals("board 비밀번호", "123245", boardFeed.getBoard().getUser().getPassword());
//        assertEquals("board title", "테스트 감자 레시피", boardFeed.getBoard().getTitle());
//
//
//    }

    private User createUser() {
        User user = new User();
        user.setName("강다빈");
        user.setPassword("123245");
        em.persist(user);
        return user;
    }

    private Feed createFeed(Integer mtralPc, Double clciQy, Double naQy, Double dryMatter, Double ashsQy, Integer feedSeqNo, Double crfbQy, Double totEdblfibrQy, Integer feedClCode,
                            Double ptssQy, Double mitrQy, Double slwtEdblfibrQy, Double liacQy, Integer upperFeedClCode, Double fatQy, Double lnacQy,
                            Double vtmaQy, Double trypQy, String feedNm, Double crbQy, String feedClCodeNm, Double phphQy, Double protQym,
                            String originNm, Double inslbltyEdblfibrQy) {
        Feed feed = new Feed(mtralPc, clciQy, naQy, dryMatter, ashsQy, feedSeqNo, crfbQy, totEdblfibrQy, feedClCode,
                ptssQy, mitrQy, slwtEdblfibrQy, liacQy, upperFeedClCode, fatQy, lnacQy,
                vtmaQy, trypQy, feedNm, crbQy, feedClCodeNm, phphQy, protQym,
                originNm, inslbltyEdblfibrQy);
        feed.setFeedClCodeNm(feedClCodeNm);    //상세 이름
        feed.setFeedClCode(feedClCode);      // 소분류
        feed.setUpperFeedClCode(upperFeedClCode); // 대분류
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