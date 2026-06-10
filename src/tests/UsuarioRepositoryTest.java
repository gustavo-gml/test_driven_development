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

        Usuario novoUsuario = new Usuario("Matheus", "matheusgedesinho@gmail.com", "122.322.323-15",
                LocalDate.parse("2026-06-05"));

        Usuario usuarioSalvo = repository.salvar(novoUsuario);

        assertNotNull(usuarioSalvo, "O objeto salvo não deveria ser nulo.");
        assertNotNull(usuarioSalvo.getId(), "O id deveria ter sido gerado.");
    }

    @Test
    void criarEmailDuplicado() {
        Usuario novoUsuario = new Usuario("Matheus", "matheusgedesinho@gmail.com", "122.322.323-15",
                LocalDate.parse("2026-06-05"));

        Usuario novoUsuario2 = new Usuario("Mathias", "matheusgedesinho@gmail.com", "122.322.323-15",
                LocalDate.parse("2026-06-05"));

        repository.salvar(novoUsuario);

        assertThrows(IllegalArgumentException.class, () -> {
            repository.salvar(novoUsuario2);
        }, "Nao deveria ser possivel salvar um usuario com o mesmo email");

    }

    @Test
    void criarCpfDuplicado() {
        Usuario novoUsuario = new Usuario("Matheus", "matheusgedesinho@gmail.com", "122.322.323-15",
                LocalDate.parse("2026-06-05"));

        Usuario novoUsuario2 = new Usuario("Mathias", "matheusgedesinho@gmail.com", "122.322.323-15",
                LocalDate.parse("2026-06-05"));

        repository.salvar(novoUsuario);

        assertThrows(IllegalArgumentException.class, () -> {
            repository.salvar(novoUsuario2);
        }, "Nao deveria ser possivel salvar um usuario com o mesmo cpf");

    }

}
