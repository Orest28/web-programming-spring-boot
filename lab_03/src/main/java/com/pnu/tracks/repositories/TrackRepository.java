package com.pnu.tracks.repositories;

import com.pnu.tracks.models.Track;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {

    @Query("select t.symbolicRepresentation from Track t where t.trackId = ?1")
    List<String> getSymbolicRepresentationOfTrack(String trackId);
}
