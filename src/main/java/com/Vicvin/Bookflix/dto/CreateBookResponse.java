package com.Vicvin.Bookflix.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBookResponse {
    private Long id;
    private String title;
    private String coverImageUrl;
    private String fileUrl;
}
