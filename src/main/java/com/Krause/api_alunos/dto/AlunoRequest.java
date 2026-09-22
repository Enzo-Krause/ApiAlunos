package com.Krause.api_alunos.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class AlunoRequest {

    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 50, message = "Nome deve possuir no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Formato de Email inválido")
    @Size(max = 50, min = 5, message = "Deve possuir entre 5 e 50 caracteres")
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    @Size(max = 50)
    private String senha;

    @NotNull(message = "Data de nascimento não pode ser vazia")
    @PastOrPresent
    private LocalDate dataNascimento;

    @Max(value = 10, message = "Média deve ser no máximo 10")
    @PositiveOrZero(message = "Média não pode ser menor que 0")
    private double media;

    public AlunoRequest() {
    }

    public AlunoRequest(String nome, String email, String senha, LocalDate dataNascimento, double media) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.media = media;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public double getMedia() { return media; }
    public void setMedia(double media) { this.media = media; }
}
