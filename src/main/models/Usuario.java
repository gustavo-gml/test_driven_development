package main.models;

import java.time.LocalDate;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;
    private String senha;
    private int tentativas;
    private boolean bloqueado;

    // Construtor para passar os dados do usuario
    public Usuario(String nome, String email, String cpf, LocalDate dataNascimento, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public Usuario() {
    }

    // Getters e Setters para obter e enviar os dados do usuario 
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public void setSenha(String senha) { this.senha = senha; }
    public String getSenha() { return this.senha; }

    // Métodos de controle de login e bloqueio
    public boolean isBloqueado() {
        return this.bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getTentativas() {
        return this.tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }

    public void incrementarTentativas() {
        this.tentativas++;
    }
}