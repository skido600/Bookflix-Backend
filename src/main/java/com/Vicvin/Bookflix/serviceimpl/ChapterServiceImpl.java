package com.Vicvin.Bookflix.serviceimpl;

import com.Vicvin.Bookflix.entity.Book;
import com.Vicvin.Bookflix.entity.Chapter;
import com.Vicvin.Bookflix.repository.BookRepository;
import com.Vicvin.Bookflix.repository.ChapterRepository;
import com.Vicvin.Bookflix.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public Chapter createChapter(Long bookId, String title, Integer chapterNumber, String content) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        if (chapterRepository.existsByBookIdAndChapterNumber(bookId, chapterNumber)) {
            throw new RuntimeException("Chapter number already exists for this book");
        }

        Chapter chapter = Chapter.builder()
                .book(book)
                .title(title)
                .chapterNumber(chapterNumber)
                .content(content)
                .build();

        Chapter saved = chapterRepository.save(chapter);
        // keep bidirectional list in sync
        book.getChapters().add(saved);
        bookRepository.save(book);
        return saved;
    }

    @Override
    public List<Chapter> getChaptersForBook(Long bookId) {
        return chapterRepository.findByBookIdOrderByChapterNumberAsc(bookId);
    }

    @Override
    @Transactional
    public void createChaptersBulk(Long bookId, List<Chapter> chapters) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        List<Integer> duplicates = chapters.stream()
                .map(Chapter::getChapterNumber)
                .collect(Collectors.toList());

        // simple uniqueness check - better: query DB for existing numbers
        for (Chapter c : chapters) {
            if (chapterRepository.existsByBookIdAndChapterNumber(bookId, c.getChapterNumber())) {
                throw new RuntimeException("Chapter number " + c.getChapterNumber() + " already exists");
            }
            c.setBook(book);
        }
        chapterRepository.saveAll(chapters);
    }
}
