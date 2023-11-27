package com.api.sweetshop.controller;

import com.api.sweetshop.model.UserProfile;
import com.api.sweetshop.repository.UserProfileRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/SweetShop")
public class UserProfileController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody UserProfile userProfile) {
        try {
            userProfileRepository.save(userProfile);
        } catch (Exception e) {
            e.printStackTrace();
            return "User already exists!";
        }
        return "User registered successfully!";
    }

    @GetMapping(value = { "/users" })
    public List<UserProfile> getUsers() {
        return this.userProfileRepository.findAll();
    }

    @PostMapping(value = { "/login" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(HttpServletResponse response, HttpSession session,
        @RequestBody UserProfile userProfile) {
        UserProfile foundUser = userProfileRepository.findByEmailAndPassword(userProfile.getEmail(),
            userProfile.getPassword());
        if (foundUser != null) {
            Cookie userIdCookie = new Cookie("userId", Long.toString(foundUser.getId()));
            userIdCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(userIdCookie);
            session.setAttribute("userId", foundUser.getId());
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @GetMapping("/get-user-id-cookie")
    public long getUserIdCookie(HttpServletRequest request) {
        // Retrieve the user's id from the cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> userIdCookie = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("userId"))
                .findFirst();

            if (userIdCookie.isPresent()) {
                return Long.parseLong(userIdCookie.get().getValue());
            }
        }
        return -1;
    }

    @GetMapping("/check-login")
    public boolean checkLogin(HttpSession session) {
        // Check if the user's id is set in the session
        Long userId = (Long) session.getAttribute("userId");
        System.out.println(userId != null);
        return userId != null;
    }
}
