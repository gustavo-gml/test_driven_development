package main.services; // ou o pacote que vocês preferirem

import main.models.Usuario;
import main.repository.UsuarioRepository;

public class AuthService {
    
    private UsuarioRepository repository;

    
    public AuthService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public String fazerLogin(String email, String senha) {
        Usuario usuario = repository.buscarPorEmail(email);
        
        if (usuario == null) {
            return "Credenciais inválidas";
        }
        if (usuario.getSenha().equals(senha)) {
            return "Sucesso";
        }
        return "Credenciais inválidas";
    }
}