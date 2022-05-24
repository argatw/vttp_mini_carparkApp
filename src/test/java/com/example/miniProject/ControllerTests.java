package com.example.miniProject;

import com.example.miniProject.Repo.StationRepo;
import com.example.miniProject.Repo.UserRepo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {
    @Autowired
    private MockMvc mMvc;

    @Autowired
    private UserRepo uRepo;

    @Autowired
    private StationRepo sRepo;

    // protected controller
    @Test
    void testProtectedView() throws Exception {
        mMvc.perform(MockMvcRequestBuilders
            .get("/protected/favourites")
            .sessionAttr("email", "test@aol.com"))
            .andExpect(status().isOk());  
    }

    //login, user controller
    @Test
    void testLogin() throws Exception {
        // mMvc.perform(MockMvcRequestBuilders
        //         .post("/auth")
        //         .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        //         .content("email=test@aol.com&password=testing123"))
        //         .andExpect(status().isForbidden());
    
        mMvc.perform(MockMvcRequestBuilders
                .post("/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("email=test@aol.com&password=testing123"))
                .andExpect(status().isOk());

        mMvc.perform(MockMvcRequestBuilders
                .post("/auth")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("email=test@aol.com&password=testing123"))
                .andExpect(status().isFound());

    }

    // station controllers
    @Test
    void testGetCrowdLevels() throws Exception {
        String trainLine = "CEL";
        mMvc.perform(MockMvcRequestBuilders
            .get("/search")
            .queryParam("trainLine", trainLine))
            .andExpect(status().isOk());
    }

    @Test
    void testLoginPage() throws Exception{
        mMvc.perform(MockMvcRequestBuilders
            .get("/login"))
            .andExpect(status().isOk());
    }

    // @Test
    // void testRegisterPage() throws Exception{
    //     mMvc.perform(MockMvcRequestBuilders
    //         .post("/createNewUser")
    //         // .get("/createNewUser"))
    //         .content("email=test@aol.com&password=testing123"))
    //         .andExpect(status().isOk());
    // }

    // @Test
    // void testRegisteredPage() throws Exception{
    //     mMvc.perform(MockMvcRequestBuilders
    //         .post("/directToLogin")
    //         .content("email=test@aol.com&password=testing123"))
    //         .andExpect(status().isOk());
    // }

    @Test
    void testFavouritesPage() throws Exception{
        String email = "test@aol.com";
        mMvc.perform(MockMvcRequestBuilders
            .get("/protected/favourites")
            .sessionAttr("email", email))
            .andExpect(status().isOk());
    }

    @Test
    void testAddFavourite() throws Exception{      
        String email = "test@aol.com";       
        mMvc.perform(MockMvcRequestBuilders
            .post("/addFav")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .content("trainLine=CEL&station=Bayfront")
            .sessionAttr("email", email))
            .andExpect(status().isOk());          
    }

    
}
