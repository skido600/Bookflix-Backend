package com.Vicvin.Bookflix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "chapters",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"book_id", "chapter_number"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "chapter_number")
    private Integer chapterNumber;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; // store HTML/Markdown/plain text

    private Instant publishedAt = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
}
