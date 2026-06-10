package main.models;

import java.time.LocalDate;

public class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private LocalDate dataNascimento;

    public Usuario(String nome, String email, String cpf, String senha, LocalDate dataNascimento) throws IllegalArgumentException {
        // 1. Aplica o trim preventivo para validar o texto já limpo
        String nomeLimpo = nome != null ? nome.trim() : null;
        String emailLimpo = email != null ? email.trim() : null;

        // 2. Validações de presença de dados
        if (!validarVazio(emailLimpo, senha)) {
            throw new IllegalArgumentException("Campos obrigatórios não podem estar vazios.");
        }
        
        // 3. Validações de formato e limites (Gustavo + José)
        if (!validarArrobaEPonto(emailLimpo)) {
            throw new IllegalArgumentException("Email invalido: e necessario inserir um arroba e um .");
        }
        if (nomeLimpo != null && nomeLimpo.length() > 500) {
            throw new IllegalArgumentException("O nome não pode exceder 500 caracteres.");
        }
        if (emailLimpo != null && emailLimpo.length() > 500) {
            throw new IllegalArgumentException("O e-mail não pode exceder 500 caracteres.");
        }
        if (senha == null || !validarComplexidadeDaSenha(senha)) {
            throw new IllegalArgumentException("A senha não cumpre os requisitos de complexidade.");
        }

        // 4. Atribuição segura
        this.nome = nomeLimpo;
        this.email = emailLimpo;
        this.cpf = cpf;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    //geters e setters 


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

private boolean validarComplexidadeDaSenha(String senha) {
        if (senha.length() < 8 || senha.contains(" ")) {
            return false;
        }
        boolean temNumero = senha.matches(".*\\d.*");
        boolean temEspecialOuEmoji = senha.matches(".*[^a-zA-Z0-9].*");
        return temNumero && temEspecialOuEmoji;
    }


}
