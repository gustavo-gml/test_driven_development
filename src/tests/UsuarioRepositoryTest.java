package tests;

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

        Usuario novoUsuario = new Usuario("Matheus", "matheusgedesinho@gmail.com", "122.322.323-15","senha123",
                LocalDate.parse("2026-06-05"));

        Usuario usuarioSalvo = repository.salvar(novoUsuario);

        assertNotNull(usuarioSalvo, "O objeto não poderia estar vazio.");

        assertNotNull(usuarioSalvo.getId(), "O id não foi inserido corretamente.");

    }
}
