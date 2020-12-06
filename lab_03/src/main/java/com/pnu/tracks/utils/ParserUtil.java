package com.pnu.tracks.utils;

import com.pnu.tracks.dto.TrackModel;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

@Component
public class ParserUtil {

    private final String latAttribute = "lat";
    private final String lonAttribute = "lon";
    private final String nodeAttribute = "trkpt";

    public Set<String> parseFile(String fileName) {
        List<TrackModel> trackList = new ArrayList<>();

        try {
            File inputFile = new File(fileName + ".gpx");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(nodeAttribute);

            for (int i = 0, l = nList.getLength(); i < l; i++) {
                Node nNode = nList.item(i);
                Element element = (Element) nNode;
                String lat = element.getAttribute(latAttribute);
                String lon = element.getAttribute(lonAttribute);
                trackList.add(new TrackModel(Double.parseDouble(lat), Double.parseDouble(lon)));
            }

            return trackList.stream()
                    .map(model -> Converter.convert(model, 24, 180, -180, 90, -90))
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
