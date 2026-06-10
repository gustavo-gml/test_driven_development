package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import main.models.Usuario;
import main.repository.UsuarioRepository;
import main.service.UsuarioService;

public class UsuarioServiceTest {

    private UsuarioRepository repository;
    private UsuarioService service;

    @BeforeEach
    void setUp() {
        // Prepara o ambiente antes de cada teste rodar
        repository = new UsuarioRepository();
        service = new UsuarioService(repository);
    }

    @Test
    void testarTentativasEBloqueio() {
        
        Usuario usuario = new Usuario("Tiago", "tiago@email.com", "000.000.000-00", java.time.LocalDate.now(), "12345");
        repository.salvar(usuario);

        // Simulação de erros 
        service.login("tiago@email.com", "senha_errada_1"); 
        service.login("tiago@email.com", "senha_errada_2"); 
        service.login("tiago@email.com", "senha_errada_3"); 

        //Busca na memoria o usuario atualizado, para exibi-lo
        Usuario usuarioAposErros = repository.buscarPorEmail("tiago@email.com");

        // Junit valida se o estado mudou para bloqueado 
        assertTrue(usuarioAposErros.isBloqueado());
    }
}
