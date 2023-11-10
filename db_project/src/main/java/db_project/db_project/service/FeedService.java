package db_project.db_project.service;

import db_project.db_project.domain.Feed;
import db_project.db_project.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;

    @Transactional
    public void saveFeed(Feed feed) {
        feedRepository.save(feed);
    }

    public Feed findOne(long feed_id) {
        return feedRepository.findOne(feed_id);
    }

    public List<Feed> findFeeds() {
        return feedRepository.findAll();
    }

}
