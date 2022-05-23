package com.example.miniProject.Model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Station {
    String searchId;
    String trainLine;
    String stationName;
    String crowdLevel;

    public String getTrainLine() { return trainLine; }
    public void setTrainLine(String trainLine) { this.trainLine = trainLine; }

    public String getSearchId() { return searchId; }
    public void setSearchId(String searchId) { this.searchId = searchId; }

    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }

    // public String getStation() { return station; }
    // public void setStation(String station) { this.station = station; }

    public String getCrowdLevel() { return crowdLevel; }
    public void setCrowdLevel(String crowdLevel) { this.crowdLevel = crowdLevel; }

    // public Carpark create(JsonObject o) {
    //     Carpark carpark = new Carpark();
    //     carpark.development = o.getString("Development");
    //     carpark.availableLots = o.getInt("AvailableLots"); // change to int

    //     return carpark;
        
    // }

    public static List<Station> create(String json) throws IOException {
        List<Station> stations = new LinkedList<>();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            JsonArray cpArray = o.getJsonArray("value");
            for (int i = 0; i < cpArray.size(); i++) {
                JsonObject obj = cpArray.getJsonObject(i);
                Station station = new Station();
                station.setStationName(obj.getString("Station"));
                station.setCrowdLevel(obj.getString("CrowdLevel"));
                stations.add(station);
            }
        
        }
        return stations;
    }

    public static Station create2(SqlRowSet rowSet) {
        Station station = new Station();
        station.setStationName(rowSet.getString("station"));
        station.setTrainLine(rowSet.getString("trainLine"));
        
        return station;
    }

    

    // @Override
	// public String toString() {
	// 	return "development: %s, availableLots: %d"
	// 			.formatted(development, availableLots);
	// }
    

}
