package com.Vicvin.Bookflix.serviceimpl;

import com.Vicvin.Bookflix.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class StorageServiceImpl  implements StorageService {
    private final Path root = Paths.get("uploads");

    public StorageServiceImpl() throws IOException {
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
    }

    @Override
    public String store(MultipartFile file, String subfolder) throws IOException {
        Path folder = root;
        if (subfolder != null && !subfolder.isBlank()) {
            folder = root.resolve(subfolder);
            if (!Files.exists(folder)) Files.createDirectories(folder);
        }
        String filename = System.currentTimeMillis() + "_" + Path.of(file.getOriginalFilename()).getFileName();
        Path target = folder.resolve(filename);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        // Return relative path; in production you'd return CDN/S3 URL
        return target.toString();
    }
}
