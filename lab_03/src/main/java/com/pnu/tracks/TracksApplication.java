package com.pnu.tracks;

import com.pnu.tracks.services.TrackService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TracksApplication implements CommandLineRunner {

    private final TrackService trackService;

    public TracksApplication(TrackService trackService) {
        this.trackService = trackService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TracksApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        trackService.convertFileToSymbolicRepresentation("10");
        trackService.convertFileToSymbolicRepresentation("71");
        trackService.convertFileToSymbolicRepresentation("73");
        trackService.convertFileToSymbolicRepresentation("77");
    }
}
