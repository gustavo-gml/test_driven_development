package main.repository;

import java.util.ArrayList;
import java.util.List;
import main.models.Usuario;

public class UsuarioRepository {
    private int countId = 1;
  
    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario salvar(Usuario u) throws IllegalArgumentException {
        // O repositório só checa duplicidade no "banco"
        if (verificarEmail(u.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        if (verificarCpf(u.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        // Lógica puramente operacional de persistência
        u.setId(countId); 
        countId++; 
        usuarios.add(u); 

        return usuarios.getLast(); 
    }

    private boolean verificarEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true; // email ja foi utilizado
            }
        }
        return false;
    }

    private boolean verificarCpf(String cpf) {
        for (Usuario u : usuarios) {
            if (u.getCpf().equalsIgnoreCase(cpf)) {
                return true; // cpf ja foi utilizado
            }
        }
        return false;
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
}
