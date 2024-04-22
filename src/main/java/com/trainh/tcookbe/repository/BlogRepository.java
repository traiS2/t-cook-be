package com.trainh.tcookbe.repository;

import com.trainh.tcookbe.model.entity.Blog;
import com.trainh.tcookbe.model.enums.EStatus;
import com.trainh.tcookbe.model.projection.blog.BlogBriefProjection;
import com.trainh.tcookbe.model.projection.blog.BlogSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findById(long id);
    List<?> getAllByStatusName(EStatus status);
    Optional<Blog> findBlogByLink(String link);
    List<BlogBriefProjection> findFirst3ByStatusNameOrderByCreateAtDesc(EStatus status);
    List<BlogSummaryProjection> findFirst5ByStatusNameOrderByCreateAtDesc(EStatus status);
}