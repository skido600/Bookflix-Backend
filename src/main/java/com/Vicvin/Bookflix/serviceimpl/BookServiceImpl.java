package com.Vicvin.Bookflix.serviceimpl;

import com.Vicvin.Bookflix.entity.Book;
import com.Vicvin.Bookflix.repository.BookRepository;
import com.Vicvin.Bookflix.service.BookService;
import com.Vicvin.Bookflix.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final StorageService storageService;

    @Override
    @Transactional
    public Book createBook(String title, String description, String author, MultipartFile coverImage, MultipartFile fullBook) throws IOException {
        String coverUrl = null;
        String fileUrl = null;

        if (coverImage != null && !coverImage.isEmpty()) {
            coverUrl = storageService.store(coverImage, "covers");
        }

        if (fullBook != null && !fullBook.isEmpty()) {
            fileUrl = storageService.store(fullBook, "books");
        }

        Book book = Book.builder()
                .title(title)
                .description(description)
                .author(author)
                .coverImageUrl(coverUrl)
                .fileUrl(fileUrl)
                .published(false)
                .build();

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
