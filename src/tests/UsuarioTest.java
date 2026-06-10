package tests;

import org.junit.jupiter.api.*;

import main.models.Usuario;
import main.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class UsuarioTest {
    UsuarioRepository repository;

    @BeforeEach
    void setUp() {
        repository = new UsuarioRepository();
    }

    @Test
    void barrarEmailSemArrobaEPonto() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Matheus", "matheusgedesinhogmailcom", "122.322.323-15", "senha123",
                    LocalDate.parse("2026-06-05"));
        });
    }

    @Test
    void impedirCadastroComCamposVazios() {
        // nome ou e-mail vazios
        assertThrows(IllegalArgumentException.class, () -> {
            // Passando strings vazias nos campos obrigatórios
            new Usuario("", "", "122.322.323-15", "", LocalDate.parse("2026-06-05"));
        });
    }


    

}
