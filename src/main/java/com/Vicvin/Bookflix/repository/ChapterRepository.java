package com.Vicvin.Bookflix.repository;

import com.Vicvin.Bookflix.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findByBookIdOrderByChapterNumberAsc(Long bookId);
    boolean existsByBookIdAndChapterNumber(Long bookId, Integer chapterNumber);
}
