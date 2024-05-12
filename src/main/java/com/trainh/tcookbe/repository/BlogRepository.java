package com.trainh.tcookbe.repository;

import com.trainh.tcookbe.model.entity.Blog;
import com.trainh.tcookbe.model.enums.EStatus;
import com.trainh.tcookbe.model.projection.blog.BriefBlogProjection;
import com.trainh.tcookbe.model.projection.blog.DetailBlogProjection;
import com.trainh.tcookbe.model.projection.blog.SummaryBlogProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findById(long id);
    List<?> getAllByStatusName(EStatus status);
    Optional<DetailBlogProjection> findBlogByLink(String link);
    List<BriefBlogProjection> findFirst3ByStatusNameOrderByCreateAtDesc(EStatus status);
    List<SummaryBlogProjection> findFirst5ByStatusNameOrderByCreateAtDesc(EStatus status);
}