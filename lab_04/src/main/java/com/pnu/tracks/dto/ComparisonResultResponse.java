package com.pnu.tracks.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ComparisonResultResponse {


    private String baseTrackName;

    private List<ComparisonResult> resultOfComparison;

}
