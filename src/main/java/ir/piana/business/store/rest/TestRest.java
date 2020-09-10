package ir.piana.business.store.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {
    @GetMapping("app-info")
    public ResponseEntity getAppInfo() {
        AppInfo appInfo = new AppInfo();
        appInfo.setAdmin(false);
        appInfo.setLoggedIn(true);
        appInfo.setUsername("ali");
        return ResponseEntity.ok(appInfo);
    }

    static class AppInfo {
        private String username;
        private boolean isLoggedIn;
        private boolean isAdmin;

        public AppInfo() {
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isLoggedIn() {
            return isLoggedIn;
        }

        public void setLoggedIn(boolean loggedIn) {
            isLoggedIn = loggedIn;
        }

        public boolean isAdmin() {
            return isAdmin;
        }

        public void setAdmin(boolean admin) {
            isAdmin = admin;
        }
    }
}
