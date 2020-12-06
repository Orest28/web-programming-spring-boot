package com.pnu.tracks.services;

import com.pnu.tracks.models.Track;
import com.pnu.tracks.repositories.TrackRepository;
import com.pnu.tracks.utils.ParserUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TrackService {

    private final TrackRepository trackRepository;
    private final ParserUtil parserUtil;

    public TrackService(TrackRepository trackRepository, ParserUtil parserUtil) {
        this.trackRepository = trackRepository;
        this.parserUtil = parserUtil;
    }

    public void convertFileToSymbolicRepresentation(String fileName) {

        Set<String> trackPoints = parserUtil.parseFile(fileName);

        for(String point : trackPoints) {
            trackRepository.save(new Track(fileName, point));
        }
    }

    public List<String> getSymbolicRepresentationOfTrack(String trackId) {
        return track
    }
}
