package com.br.rankup.backend.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.rankup.backend.Model.User;
import com.br.rankup.backend.Repository.UserRepository;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public List<User> obterUsers() {
        return userRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> cadastrarUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(error -> {
                        if (error instanceof FieldError) {
                            FieldError fieldError = (FieldError) error;
                            return fieldError.getField() + ": " + fieldError.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.joining(", "));
            
            // Retorna um erro 400 (Bad Request) com a mensagem de erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + errorMessage);
        }

        // Se não houver erros, salva o usuário no banco
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
