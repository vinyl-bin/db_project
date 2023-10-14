package db_project.db_project.repository;

import db_project.db_project.domain.BoardFeed;
import db_project.db_project.domain.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardFeedCustomRepository {
    public List<BoardFeed> findBySearchOption(SearchCondition searchCondition);
}