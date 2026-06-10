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
        Usuario novoUsuario = new Usuario("Matheus", "matheusgedesinho@gmail.com", "122.322.323-15", "senha@123",
                LocalDate.parse("2026-06-05"));

        Usuario usuarioSalvo = repository.salvar(novoUsuario);

        assertNotNull(usuarioSalvo, "O objeto salvo não deveria ser nulo.");
        assertNotNull(usuarioSalvo.getId(), "O id deveria ter sido gerado.");
    }

    @Test
    void criarEmailDuplicado() {
        Usuario novoUsuario = new Usuario("Matheus", "matheusgedesinho@gmail.com", "122.322.323-15","senha@123",
                LocalDate.parse("2026-06-05"));

        Usuario novoUsuario2 = new Usuario("Mathias", "matheusgedesinho@gmail.com", "122.322.323-15","senha@321",
                LocalDate.parse("2026-06-05"));

        repository.salvar(novoUsuario);

        assertThrows(IllegalArgumentException.class, () -> {
            repository.salvar(novoUsuario2);
        }, "Nao deveria ser possivel salvar um usuario com o mesmo email");
    }

    @Test
    void criarCpfDuplicado() {
        Usuario novoUsuario = new Usuario("Matheus", "matheusgedesinho@gmail.com", "122.322.323-15","senha@123",
                LocalDate.parse("2026-06-05"));

        Usuario novoUsuario2 = new Usuario("Mathias", "matheusgedesinho@gmail.com", "122.322.323-15","senha@321",
                LocalDate.parse("2026-06-05"));

        repository.salvar(novoUsuario);

        assertThrows(IllegalArgumentException.class, () -> {
            repository.salvar(novoUsuario2);
        }, "Nao deveria ser possivel salvar um usuario com o mesmo cpf");
    }


    // TESTES DO JOSÉ (Mantidos e adaptados para compilar)
   
    @Test
    void validarRegrasDeComplexidadeDaSenha() {
        // Senha sem números e sem caractere especial explode na criação
        assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Jose", "j1@email.com", "111.111.111-11", "justletters", LocalDate.parse("2026-06-05"));
        }, "Deveria barrar senha sem números/especiais.");

        // Senha com menos de 8 caracteres
        assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Jose", "j2@email.com", "222.222.222-22", "123!", LocalDate.parse("2026-06-05"));
        }, "Deveria barrar senha curta.");

        // Senha com espaços
        assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Jose", "j3@email.com", "333.333.333-33", "Senha 123!", LocalDate.parse("2026-06-05"));
        }, "Deveria barrar senha com espaços.");

        // Senha válida agora pode ser criada e salva
        Usuario uValido = new Usuario("Jose", "j4@email.com", "444.444.444-44", "Senha@123", LocalDate.parse("2026-06-05"));
        assertNotNull(repository.salvar(uValido), "Deveria salvar com sucesso uma senha complexa.");
    }
    @Test
    void fazerTrimNosEspacosDeEmailEUsuario() {
        Usuario usuarioComEspacos = new Usuario("  Jose Trim  ", "  trim@email.com  ", "555.555.555-55", "Senha123!",
                LocalDate.parse("2026-06-05"));
        Usuario salvo = repository.salvar(usuarioComEspacos);

        // Garantir o trim ao salvar
        assertEquals("Jose Trim", salvo.getNome());
        assertEquals("trim@email.com", salvo.getEmail());
    }

    @Test
    void aceitarEmojisNaSenha() {
        Usuario usuarioComEmoji = new Usuario("Jose", "emoji@email.com", "777.777.777-77", "senha🚀🔥123",
                LocalDate.parse("2026-06-05"));
        Usuario salvo = repository.salvar(usuarioComEmoji);

        assertEquals("senha🚀🔥123", salvo.getSenha());
    }

    
    @Test
    void barrarTextoQueExcedaLimiteMaximo() {
        String textLongo = "A".repeat(501);

        // O erro agora é lançado direto no construtor
        assertThrows(IllegalArgumentException.class, () -> {
            new Usuario(textLongo, "longo@email.com", "888.888.888-88", "Senha@123", LocalDate.parse("2026-06-05"));
        }, "Deveria barrar nome maior que 500 caracteres.");

        assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("jose", textLongo, "888.888.888-88", "Senha@123", LocalDate.parse("2026-06-05"));
        }, "Deveria barrar email maior que 500 caracteres.");
    }
}