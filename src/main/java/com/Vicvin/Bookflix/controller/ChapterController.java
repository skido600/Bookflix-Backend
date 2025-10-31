package com.Vicvin.Bookflix.controller;

import com.Vicvin.Bookflix.dto.CreateChapterRequest;
import com.Vicvin.Bookflix.entity.Chapter;
import com.Vicvin.Bookflix.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
@RequiredArgsConstructor
public class ChapterController {
    private final ChapterService chapterService;

    @PostMapping
    public ResponseEntity<Chapter> create(@RequestBody CreateChapterRequest req) {
        Chapter c = chapterService.createChapter(req.getBookId(), req.getTitle(), req.getChapterNumber(), req.getContent());
        return ResponseEntity.ok(c);
    }

    @PostMapping("/bulk/{bookId}")
    public ResponseEntity<Void> bulkCreate(@PathVariable Long bookId, @RequestBody List<CreateChapterRequest> requests) {
        List<Chapter> chapters = requests.stream().map(r ->
                Chapter.builder()
                        .chapterNumber(r.getChapterNumber())
                        .title(r.getTitle())
                        .content(r.getContent())
                        .build()
        ).toList();

        chapterService.createChaptersBulk(bookId, chapters);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Chapter>> listByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(chapterService.getChaptersForBook(bookId));
    }
}
