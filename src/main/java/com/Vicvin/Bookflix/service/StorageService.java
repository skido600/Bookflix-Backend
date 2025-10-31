package com.Vicvin.Bookflix.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
public interface StorageService {

        /**
         * Save a file and return a public path / url
         */
        String store(MultipartFile file, String subfolder) throws IOException;


}
