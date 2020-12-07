package com.pnu.tracks.services;

import com.pnu.tracks.exceptions.exceptions.FileEmptyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class StorageService {

    private final TrackService trackService;

    private final String UPLOADED_FOLDER = "tracks/";

    public StorageService(TrackService trackService) {
        this.trackService = trackService;
    }

    public String fileUpload(MultipartFile file) {

        if(file.isEmpty()) throw new FileEmptyException("File is empty, please load another one");

        try {
            byte[] fileBytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, fileBytes);
            trackService.convertFileToSymbolicRepresentationAndSave(Objects.requireNonNull(file.getOriginalFilename()).substring(0, file.getOriginalFilename().length() - 4));

            return "file was loaded and processed successfully";
        }catch(IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
