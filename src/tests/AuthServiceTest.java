package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import main.models.Usuario;
import main.repository.UsuarioRepository;
import main.services.AuthService;

import java.time.LocalDate;

public class AuthServiceTest {
    
    UsuarioRepository repository;
    AuthService authService;

    @BeforeEach
    void setUp() {
  
        repository = new UsuarioRepository();
        
        authService = new AuthService(repository);
    }

   
    // ÁGABO 


    @Test
    void fazerLoginComSucesso() {
        Usuario novoUsuario = new Usuario("Agabo", "agabo@email.com", "111.222.333-44", "senhaSegura", LocalDate.parse("1990-01-01"));
        repository.salvar(novoUsuario);

        
        String resultado = authService.fazerLogin("agabo@email.com", "senhaSegura");
        assertEquals("Sucesso", resultado, "O login deveria ser realizado com sucesso.");
    }

    @Test
    void negarLoginComEmailNaoCadastrado() {
        String resultado = authService.fazerLogin("naoexiste@email.com", "senha123");
        assertEquals("Credenciais inválidas", resultado, "Deveria retornar erro para e-mail não cadastrado.");
    }

    @Test
    void negarLoginComSenhaIncorreta() {
        Usuario novoUsuario = new Usuario("Agabo", "agabo@email.com", "111.222.333-44", "senhaCorreta", LocalDate.parse("1990-01-01"));
        repository.salvar(novoUsuario);

        String resultado = authService.fazerLogin("agabo@email.com", "senhaErrada");
        assertEquals("Credenciais inválidas", resultado, "Deveria negar o acesso para senha incorreta.");
    }

   
    // GUSTAVO 
    

    @Test
    void devePrevenirSqlInjectionNoLogin() {
        Usuario usuarioReal = new Usuario("Admin", "admin@email.com", "122.322.323-15", "senha123", LocalDate.parse("2026-06-05"));
        repository.salvar(usuarioReal);

       
        String emailMalicioso = "admin@email.com' OR '1'='1";
        String senhaQualquer = "qualquercoisa";

       
        String resultado = authService.fazerLogin(emailMalicioso, senhaQualquer);
        assertEquals("Credenciais inválidas", resultado, "O sistema não deve permitir o login com tentativa de injeção de SQL");
    }
}