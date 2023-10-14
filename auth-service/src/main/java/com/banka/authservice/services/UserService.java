package com.banka.authservice.services;

import com.banka.authservice.dto.requests.MailRequest;
import com.banka.authservice.entity.User;
import com.banka.authservice.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private OkHttpClientServiceImpl okHttpClientService;
    @Autowired
    ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<User> addUser(User user) throws Exception {
        user.setId(user.getId());
        user.setName(user.getName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        MailRequest mailRequest = new MailRequest();
        mailRequest.setSubject("Uyelik Hk. deneme");
        mailRequest.setText("Uyeliginiz basariyla gerceklestirilmistir.");
        mailRequest.setTo(user.getEmail());

        okhttp3.MediaType mediaType = okhttp3.MediaType.parse(MediaType.APPLICATION_JSON_VALUE);

        RequestBody body = RequestBody.create(mediaType, objectMapper.writeValueAsString(mailRequest));
        Request request = new Request.Builder()
                .url("http://localhost:8083/mail-sender/send-email")
                .method("POST", body)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .addHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
        Response response = okHttpClientService.mailSend(request);
        if (response.isSuccessful()) {
            userRepository.save(user);
        }else {
            throw new Exception(response.body().toString());
        }
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> updateUser(User user) {
        user.setName(user.getName());
        user.setLastName(user.getLastName());
        return ResponseEntity.ok(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return "Silindi" + userId;
    }

    public Optional<User> findUser(Long userId) {
        return userRepository.findById(userId);
    }
    /*public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }*/
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
