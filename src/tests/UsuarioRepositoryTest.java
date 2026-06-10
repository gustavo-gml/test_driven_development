package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import main.models.Usuario;
import main.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class UsuarioRepositoryTest {

    UsuarioRepository repository;

    @BeforeEach
    void setUp() {
        repository = new UsuarioRepository();
    }

    @Test
    void testAdicionarUsuario() {

        // Adicionei a senha "SenhaDoMatheus123" no final
        Usuario novoUsuario = new Usuario("Matheus", "matheusgedesinho@gmail.com", "122.322.323-15", java.time.LocalDate.parse("2000-01-01"), "SenhaDoMatheus123");

        Usuario usuarioSalvo = repository.salvar(novoUsuario);

        assertNotNull(usuarioSalvo, "O objeto não poderia estar vazio.");

        assertNotNull(usuarioSalvo.getId(), "O id não foi inserido corretamente.");

    }

}
