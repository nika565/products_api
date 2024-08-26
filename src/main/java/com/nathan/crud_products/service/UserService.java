package com.nathan.crud_products.service;

import com.nathan.crud_products.DTO.LoginRequestDTO;
import com.nathan.crud_products.DTO.RegisterRequestDTO;
import com.nathan.crud_products.DTO.ResponseDTO;
import com.nathan.crud_products.domain.user.User;
import com.nathan.crud_products.infra.security.TokenService;
import com.nathan.crud_products.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if(passwordEncoder.matches(body.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user, token));
        }

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity register(@RequestBody RegisterRequestDTO body){

        Optional<User> user = this.repository.findByEmail((body.email()));

        if(user.isEmpty()) {
            User newUser = new User();

            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setRoles(body.role());

            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser, token));
        }
        return ResponseEntity.badRequest().build();
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findOneUser(Long id) {
        return repository.findById(id);
    }

    public User editUser(Long id, User body) {
        User user = repository.findById(id).orElseThrow(() -> null);
        user.setName(body.getName());
        user.setEmail(body.getEmail());
        user.setPassword(body.getPassword());
        repository.save(user);
        return user;
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }

}
