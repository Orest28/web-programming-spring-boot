package com.pnu.tracks.services;

import com.pnu.tracks.dto.ComparisonResult;
import com.pnu.tracks.dto.ComparisonResultResponse;
import com.pnu.tracks.models.Track;
import com.pnu.tracks.repositories.TrackRepository;
import com.pnu.tracks.utils.ParserUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrackService {

    private final TrackRepository trackRepository;
    private final ParserUtil parserUtil;

    public TrackService(TrackRepository trackRepository, ParserUtil parserUtil) {
        this.trackRepository = trackRepository;
        this.parserUtil = parserUtil;
    }

    public void convertFileToSymbolicRepresentationAndSave(String fileName) {

        Set<String> trackPoints = parserUtil.parseFile(fileName);

        for(String point : trackPoints) {
            trackRepository.save(new Track(fileName, point));
        }
    }

    public List<String> getSymbolicRepresentationOfTrack(String trackId) {
        return trackRepository.getSymbolicRepresentationOfTrack(trackId);
    }

    public ComparisonResultResponse compareTrackWithOthers(String trackId, int deep) {
        List<ComparisonResult> comparisonResults = new ArrayList<>();
        List<String> baseTrackRepresentation = trackRepository.getSymbolicRepresentationOfTrack(trackId);
        List<Track> othersTracks = trackRepository.getAllTracksWithout(trackId);

        Map<String, List<Track>> map = divideTracks(othersTracks);
        for(Map.Entry<String, List<Track>> track : map.entrySet()) {
            List<String> comparedTrackRepresentation = track.getValue().stream().map(Track::getSymbolicRepresentation).collect(Collectors.toList());
            List<String> tempBaseTrackRepresentation = new ArrayList<String>(baseTrackRepresentation);
            comparisonResults.add(new ComparisonResult(track.getValue().get(0).getTrackId(),
                    Integer.toString(compareTwoRepresentation(tempBaseTrackRepresentation, comparedTrackRepresentation, deep)) + " percent similar"));
        }

        return new ComparisonResultResponse(trackId, comparisonResults);
    }

    private int compareTwoRepresentation(List<String> baseRepresentation, List<String> comparedRepresentation, int deep) {

        if(deep < baseRepresentation.get(0).length()) {

            baseRepresentation = baseRepresentation.stream().map(el -> el.substring(0, deep)).collect(Collectors.toList());
            comparedRepresentation = comparedRepresentation.stream().map(el -> el.substring(0, deep)).collect(Collectors.toList());

        }

        int matchesFound = 0;
        int length = Math.min(baseRepresentation.size(), comparedRepresentation.size());

        for(int i = 0; i < length; i++)
            if(checkEqual(baseRepresentation.get(i), comparedRepresentation.get(i)))
                matchesFound++;

        return (100 * matchesFound) / baseRepresentation.size();
    }

    private boolean checkEqual(String point1, String point2) {
        return point1.equals(point2);
    }

    private Map<String, List<Track>> divideTracks(List<Track> tracks) {
        return tracks.stream().collect(Collectors.groupingBy(Track::getTrackId));
    }
}
