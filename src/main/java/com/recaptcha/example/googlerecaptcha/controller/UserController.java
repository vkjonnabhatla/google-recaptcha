package com.recaptcha.example.googlerecaptcha.controller;

import com.recaptcha.example.googlerecaptcha.dto.GoogleRecaptchaResponse;
import com.recaptcha.example.googlerecaptcha.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    public List<User> userList = new ArrayList<>();

    @Autowired
    public RestTemplate restTemplate;

    @GetMapping(path = "/save")
    public ResponseEntity<String> save(@RequestParam("username") String username, @RequestParam("password") String password,
                     @RequestParam("email") String mail, @RequestParam("token") String clientToken){
         User user = new User();
         user.setMail(mail);
         user.setPassword(password);
         user.setUsername(username);
         userList.add(user);

         // google recaptcha server side implementation

        String url = "https://www.google.com/recaptcha/api/siteverify?secret=6Lf_07QZAAAAAHiOQzJWPF33fS03Bb161Go-MNQo&response="+ clientToken;
        ResponseEntity<GoogleRecaptchaResponse> recaptchaResponse = restTemplate.exchange(url, HttpMethod.POST, null, GoogleRecaptchaResponse.class);

        if(recaptchaResponse.getBody().getSuccess()){
            return ResponseEntity.ok("User successfully added");
        }else{
            if(recaptchaResponse.getBody().getErrorCodes().size() > 0){
                for(String obj : recaptchaResponse.getBody().getErrorCodes()){
                    System.out.println(obj);
                }
            }
        }
        return ResponseEntity.ok("Captcha error");
    }

    @GetMapping(path = "/savev2")
    public ResponseEntity<String> savev2(@RequestParam("username") String username, @RequestParam("password") String password,
                                       @RequestParam("email") String mail, @RequestParam("g-recaptcha-response") String clientToken){//@RequestParam("token") String clientToken
        User user = new User();
        user.setMail(mail);
        user.setPassword(password);
        user.setUsername(username);
        userList.add(user);

        // google recaptcha server side implementation

        String url = "https://www.google.com/recaptcha/api/siteverify?secret=6LeS07QZAAAAAGMXVc2kL8tEm1VuloZT9cyHvP_L&response="+ clientToken;
        ResponseEntity<GoogleRecaptchaResponse> recaptchaResponse = restTemplate.exchange(url, HttpMethod.POST, null, GoogleRecaptchaResponse.class);
//v2 = 6Lf_07QZAAAAAHiOQzJWPF33fS03Bb161Go-MNQo
        if(recaptchaResponse.getBody().getSuccess()){
            return ResponseEntity.ok("User successfully added");
        }else{
            if(recaptchaResponse.getBody().getErrorCodes().size() > 0){
                for(String obj : recaptchaResponse.getBody().getErrorCodes()){
                    System.out.println(obj);
                }
            }
        }
        return ResponseEntity.ok("Captcha error");
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userList);
    }


}
