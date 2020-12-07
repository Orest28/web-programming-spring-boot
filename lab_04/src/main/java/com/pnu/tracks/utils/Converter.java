package com.pnu.tracks.utils;

import com.pnu.tracks.dto.TrackModel;

public class Converter {

    public  static String convert(TrackModel model, int depth, double eastLon, double westLon, double northLat, double southLat) {
        StringBuilder result = new StringBuilder();

        String[][] matrix = {
                {"a", "b"},
                {"c", "d"}
        };

        int indexLon;
        int indexLat;

        int index = 0;
        while (index++ <= depth) {
            double lon = model.getLongitude();
            double lat = model.getLatitude();

            double midLatitude = (northLat + southLat) / 2;
            double midLongitude = (eastLon + westLon) / 2;

            if (lon <= midLongitude)
                indexLon = 0;
            else indexLon = 1;

            if (lat <= midLatitude)
                indexLat = 1;
            else indexLat = 0;

            String letter = matrix[indexLat][indexLon];

            switch (letter) {
                case "a":
                    eastLon = midLongitude;
                    southLat = midLatitude;
                    break;
                case "b":
                    westLon = midLongitude;
                    southLat = midLatitude;
                    break;
                case "c":
                    eastLon = midLongitude;
                    northLat = midLatitude;
                    break;
                case "d":
                    westLon = midLongitude;
                    northLat = midLatitude;
                    break;
            }

            result.append(letter);
        }

        return result.toString();
    }
}
