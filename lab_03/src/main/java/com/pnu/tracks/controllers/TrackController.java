package com.pnu.tracks.controllers;

import com.pnu.tracks.services.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/track")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/{trackId}")
    public ResponseEntity<?> displayTrack(@PathVariable String trackId) {
        return new ResponseEntity<>(trackService.getSymbolicRepresentationOfTrack(trackId), HttpStatus.OK);
    }
}
