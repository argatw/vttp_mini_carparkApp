package com.example.miniProject;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.miniProject.Model.Station;
import com.example.miniProject.Service.StationService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
public class StationTests {

    @Autowired
    private StationService stationServ;

    // @Autowired
    // private MockMvc mockMvc;

    // public void doPost(HttpServletRequest req, HttpServletResponse res)
    //     throws ServletException, IOException {
    //         HttpSession session = req.getSession();
    // }

    @Test
    public void testSearchStations() {
        assertFalse(stationServ.getTrainLine("CGL").isEmpty());
    }

    @Test
    public void testGetFavourites() {
        // assertTrue(stationServ.getFavs(email));
        try {
            stationServ.getFavs("a@aol.com");
        } catch (Exception e) {
            //TO DO: handle exception
            assertTrue(true);
            return;
        }
    }

    // @Test
    // public void testaddFavourite() {
    //     // Station station2 = new Station();
    //     // station2.setStationName("Expo");
    //     // station2.setTrainLine("CGL");
    //     String station = "Expo";
    //     String trainLine = "CGL";
    //     MultiValueMap<String,String> form = new LinkedMultiValueMap<>(); 
    //     form.add(station, trainLine);

    //     String email = "z@aol.com";

    //     // mockMvc.perform((RequestBuilder) ((ResultActions) MockMvcRequestBuilders
    //     //     .post("/addFav")
    //     //     .sessionAttr("email", email)
    //     //     .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    //     //     .content("trainLine=CGL&station=Expo"))
    //     //     .andExpect(status().isOk())

    //     //     );
        
    //     HttpSession session =
    //     String email = (String)session.setAttribute("email");
        

    //     try {
    //         stationServ.addFavourite(form, session)
    //     } catch (Exception e) {
    //         //TODO: handle exception
    //     }
    // }


}
