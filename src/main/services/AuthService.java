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
        
        // Se o usuário não existe
        if (usuario == null) {
            return "Credenciais inválidas";
        }

        // Se o usuário já está bloqueado, barra logo de cara
        if (usuario.isBloqueado()) {
            return "Conta bloqueada"; 
        }

        // Validação da senha
        if (usuario.getSenha().equals(senha)) {
            // Sucesso! Zera as tentativas e libera o acesso
            usuario.setTentativas(0);
            return "Sucesso";
        } else {
            // Errou a senha! Incrementa a contagem
            usuario.incrementarTentativas();
            
            // Se chegou a 3 (ou mais), trava a conta
            if (usuario.getTentativas() >= 3) {
                usuario.setBloqueado(true);
            }
            
            return "Credenciais inválidas";
        }
    }
}