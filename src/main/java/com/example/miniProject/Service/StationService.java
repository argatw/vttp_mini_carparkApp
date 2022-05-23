package com.example.miniProject.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.example.miniProject.Model.Station;
import com.example.miniProject.Repo.StationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonArray;

@Service
public class StationService {
    private static final String LINK = "http://datamall2.mytransport.sg/ltaodataservice/PCDRealTime";

    @Value("${lta.api.key}")
    private String apiKey;

    @Autowired
    private StationRepo sRepo;

    public List<Station> getTrainLine(String trainLine) {
        String trainUrl = UriComponentsBuilder.fromUriString(LINK)
            .queryParam("TrainLine", trainLine)
            .toUriString();

        RequestEntity req = RequestEntity
            .get(trainUrl)
            .accept(MediaType.APPLICATION_JSON)
            .header("AccountKey", apiKey)
            .build();
        // System.out.println(">>>API: " + apiKey);

        RestTemplate rTemplate = new RestTemplate();
        ResponseEntity<String> resp = rTemplate.exchange(req, String.class);
        List<Station> stations = new LinkedList<>();

        try {
            stations = Station.create(resp.getBody());
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

        return stations;
        
    }

    public boolean addFavourite(MultiValueMap<String, String> form, HttpSession session) {
        String station = form.getFirst("station");
        String trainLine = form.getFirst("trainLine");
        String email = (String)session.getAttribute("email");
        // user.setId(id);
        Station station2 = new Station();
        station2.setStationName(station);
        station2.setTrainLine(trainLine);

        
        // String station = station2.setStation(form.getFirst("station"));


        return sRepo.addFav(station2, email);

    }


    public List<Station> getFavs(String email){
        List<Station> stations = sRepo.getFav2(email);
        return stations;
    }



    // public List<Carpark> getAllCarparks() {
    //     RequestEntity req = RequestEntity
    //         .get(LINK)
    //         .accept(MediaType.APPLICATION_JSON)
    //         .header("AccountKey", apiKey)
    //         .build();
    //     // System.out.println(">>>API: " + apiKey);

    //     RestTemplate rTemplate = new RestTemplate();
    //     ResponseEntity<String> resp = rTemplate.exchange(req, String.class);
    //     List<Carpark> carparks = new LinkedList<>();

    //     try {
    //         carparks = Carpark.create(resp.getBody());
    //     } catch (Exception e) {
    //         //TODO: handle exception
    //         e.printStackTrace();
    //     }

    //     return carparks;
        
    // }


    // public List<Carpark> getAllCarparks() {
    //     RequestEntity req = RequestEntity
    //         .get(LINK)
    //         .accept(MediaType.APPLICATION_JSON)
    //         .header("key", apiKey)
    //         .build();
    //     // System.out.println(">>>API: " + apiKey);

    //     RestTemplate rTemplate = new RestTemplate();
    //     ResponseEntity<String> resp = rTemplate.exchange(req, String.class);
    //     List<Carpark> carparks = new LinkedList<>();

    //     try(InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
    //         JsonReader r = Json.createReader(is);
    //         JsonObject o = r.readObject();
    //         JsonArray cpArray = o.getJsonArray("value");
    //         for (int i = 0; i < cpArray.size(); i++) {
    //             Carpark carpark = new Carpark();
    //             carparks.add(carpark.create(cpArray.getJsonObject(i)));
    //         }
            
    //     } catch (Exception e) {
    //         //TODO: handle exception
    //         e.printStackTrace();
    //     }
    //     return carparks;
        
    // }


    

}
