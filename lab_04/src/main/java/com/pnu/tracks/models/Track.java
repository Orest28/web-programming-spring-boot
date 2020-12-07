package com.pnu.tracks.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackId;

    private String symbolicRepresentation;

    public Track() {

    }

    public Track(String trackId, String symbolicRepresentation) {
        this.trackId = trackId;
        this.symbolicRepresentation = symbolicRepresentation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getSymbolicRepresentation() {
        return symbolicRepresentation;
    }

    public void setSymbolicRepresentation(String symbolicRepresentation) {
        this.symbolicRepresentation = symbolicRepresentation;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", trackId='" + trackId + '\'' +
                ", symbolicRepresentation='" + symbolicRepresentation + '\'' +
                '}';
    }
}
