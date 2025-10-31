package com.Vicvin.Bookflix.dto;

import lombok.Data;

@Data
public class CreateChapterRequest {
        private Long bookId;
        private String title;
        private Integer chapterNumber;
        private String content;

}
