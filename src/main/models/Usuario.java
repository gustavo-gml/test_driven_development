package main.models;

import java.time.LocalDate;

public class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private LocalDate dataNascimento;

    public Usuario(String nome, String email, String cpf, String senha, LocalDate dataNascimento) throws IllegalArgumentException{
        if (!validarArrobaEPonto(email)) {
            throw new IllegalArgumentException("Email invalido: e necessario inserir um arroba e um .");
        }
        if (!validarVazio(email,senha)) {
            throw new IllegalArgumentException("Campos obrigatórios não podem estar vazios.");
        }
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    //geters e setters 

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if (!validarArrobaEPonto(email)) {
            throw new IllegalArgumentException("Email invalido: e necessario inserir um arroba e um .");
        }
        this.email = email;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() { return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    public LocalDate getDataNascimento() {return dataNascimento;}

    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }


    private boolean validarArrobaEPonto(String email) {
    int posicaoArroba = email.indexOf('@');
    int posicaoPonto = email.indexOf('.', posicaoArroba); // Procura o ponto APÓS o arroba

    return posicaoArroba >= 0 && posicaoPonto > posicaoArroba; 
}
    private boolean validarVazio(String email, String senha) {
  
    boolean emailInvalido = (email == null || email.isBlank());
    boolean senhaInvalida = (senha == null || senha.isBlank());

    
    return !emailInvalido && !senhaInvalida;
}


}
