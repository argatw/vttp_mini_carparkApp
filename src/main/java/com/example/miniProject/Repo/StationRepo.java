package com.example.miniProject.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.miniProject.Model.Station;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class StationRepo {

    @Autowired
    private JdbcTemplate temp;

    public boolean addFav(Station station, String email) {
        final int added = temp.update(
            "insert into favourite (email,trainLine,station) values (?,?,?)"
            ,email ,station.getTrainLine(),station.getStationName()
        );
        return added > 0;
    }

    // public List<Station> getFav(String email){
    //     List<Station> stations = new ArrayList<>();
    //     SqlRowSet q = temp.queryForRowSet(
    //         "select * from favourite where email = ?", email);
    //     while (q.next()){
    //         Station station = new Station();
    //         station.setTrainLine(q.getString("trainLine"));
    //         station.setStation(q.getString("station"));
    //         stations.add(station);
    //     }
    //     return stations;
    // }

    // public Optional<Station> getFav2(String email) {
    //     final SqlRowSet q = temp.queryForRowSet(
    //         "select * from favourite where email like ?", email
    //     );
    //     if(!q.next())
    //         return Optional.empty();

    //     return Optional.of(Station.create2(q));
    // }

    public List<Station> getFav2(String email) {
        List<Station> stations = new ArrayList<>();
        final SqlRowSet q = temp.queryForRowSet(
            "select * from favourite where email like ?", email
        );
        while (q.next()){
            stations.add(Station.create2(q));
        }
        return stations;
    }

    // public Optional<Station> getFav3(String email) {
    //     List<Station> stations = new ArrayList<>();
    //     final SqlRowSet q = temp.queryForRowSet(
    //         "select * from favourite where email like ?", email
    //     );
    //     if(!q.next())
    //         return Optional.empty();

    //     return Optional.of(Station.create2(q));
    // }
}
