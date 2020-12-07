package com.pnu.tracks.controllers;

import com.pnu.tracks.services.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/compare/{trackId}/{deep}")
    public ResponseEntity<?> compareTracks(@PathVariable String trackId, @PathVariable String deep) {
        return new ResponseEntity<>(trackService.compareTrackWithOthers(trackId, Integer.parseInt(deep)), HttpStatus.OK);
    }
}
