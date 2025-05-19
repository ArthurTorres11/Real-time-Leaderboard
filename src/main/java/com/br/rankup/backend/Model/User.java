package com.br.rankup.backend.Model;

import com.br.rankup.backend.Model.Enums.Rank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name", nullable = false)
    private String name;
    
    @NotNull(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Formato de CPF inválido")
    @Column(name = "user_cpf", nullable = false)
    private String cpf;

    @Column(name = "user_score", nullable = false)
    private int score;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_rank", nullable = false)
    private Rank rank;

    @Column(name = "user_position", nullable = false)
    private int position;

    @Column(name = "user_email", nullable = false)
    @Email(message = "Email inválido")
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
