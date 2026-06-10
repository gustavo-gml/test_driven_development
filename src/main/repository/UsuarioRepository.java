package main.repository;

import java.util.ArrayList;
import java.util.List;
import main.models.Usuario;

public class UsuarioRepository {

    private int countId = 1;
    private static List<Usuario> usuarios = new ArrayList<>();

    public Usuario salvar(Usuario u) {
        u.setId(countId); 
        countId++; 
        usuarios.add(u); 
        return usuarios.getLast(); 
    }
    
    //metodo para buscar o email

    public Usuario buscarPorEmail(String email) {
        
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }
}
