package com.Vicvin.Bookflix.service;

import com.Vicvin.Bookflix.entity.Chapter;

import java.util.List;

public interface ChapterService {
    Chapter createChapter(Long bookId, String title, Integer chapterNumber, String content);
    List<Chapter> getChaptersForBook(Long bookId);
    void createChaptersBulk(Long bookId, List<Chapter> chapters);
}
