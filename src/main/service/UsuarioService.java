package main.service;

import main.models.Usuario;
import main.repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository repository;
    // Metodo que vai receber os dados da classe repository
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public boolean login(String email, String senha) {
        // Buscar o usuário e verificar se ele existe.
        Usuario usuario = repository.buscarPorEmail(email);
        if (usuario == null) {
            System.out.println("O objeto não existe (Usuário não encontrado)");
            return false;
        }

        // Verificar se ele já está bloqueado. Se sim, barra o login imediatamente.
        if (usuario.isBloqueado()) {
            System.out.println("Usuário já está bloqueado no sistema.");
            return false;
        }

        //  Comparar as senhas.
        if (usuario.getSenha().equals(senha)) {
            // Se a senha estiver certa, reseta as tentativas e loga
            usuario.setTentativas(0);
            repository.salvar(usuario);
            return true;
        } else {
            //  Se as senhas forem diferentes:
            
            // Acrescenta mais uma tentativa 
            usuario.incrementarTentativas(); 

            // Calcula quantas tentativas ainda restam 
            int restam = 3 - usuario.getTentativas();

            // Menssagens que dizem o status atual do usuario (quantidade de chances que ele ainda tem ou se foi bloqueado)
            if (usuario.getTentativas() >= 3) {
                usuario.setBloqueado(true);
                System.out.println("Limite de tentativas excedido. Seu usuário foi bloqueado do sistema!");
            } else if (usuario.getTentativas() == 2) {
                System.out.println("Você tem " + restam + " tentativa e na próxima, mais um erro e vc será bloqueado!");
            } else {
                System.out.println("Você errou a senha. Você tem " + restam + " tentativas restantes.");
            }

            // Salva o estado atualizado do usuário no repositório.
            repository.salvar(usuario);
            return false;
        }
    }
}