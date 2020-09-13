package ir.piana.business.store.rest;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class TestRest {
    @CrossOrigin(origins = "http://localhost", maxAge = 3600)
    @PostMapping(path = "vlogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody Map map) {
        Object accessToken = map.get("accessToken");
        GoogleCredential credential = new GoogleCredential().setAccessToken((String) accessToken);


        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential).setApplicationName(
                "Oauth2").build();
        try {
            Userinfo userinfo = oauth2.userinfo().get().execute();
            String email = userinfo.getEmail();
            String name = userinfo.getName();
            String picture = userinfo.getPicture();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("Hello");
    }
}
