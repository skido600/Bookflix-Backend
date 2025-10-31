package com.Vicvin.Bookflix.controller;

import com.Vicvin.Bookflix.entity.Book;
import com.Vicvin.Bookflix.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // Create book (metadata + optional cover image + optional full book file)
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Book> createBook(
            @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String author,
            @RequestParam(value = "coverImage", required = false) MultipartFile coverImage,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        Book b = bookService.createBook(title, description, author, coverImage, file);
        return ResponseEntity.ok(b);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
