package com.Vicvin.Bookflix.service;

import com.Vicvin.Bookflix.entity.Book;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface BookService {
    Book createBook(String title, String description, String author, MultipartFile coverImage, MultipartFile fullBook) throws IOException;
    List<Book> getAllBooks();
    Book getBookById(Long id);
    void deleteBook(Long id);
}

