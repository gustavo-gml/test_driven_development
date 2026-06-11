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
        Usuario novoUsuario = new Usuario("Agabo", "agabo@email.com", "111.222.333-44", "Senha@123", LocalDate.parse("1990-01-01"));
        repository.salvar(novoUsuario);

        
        String resultado = authService.fazerLogin("agabo@email.com", "Senha@123");
        assertEquals("Sucesso", resultado, "O login deveria ser realizado com sucesso.");
    }

    @Test
    void negarLoginComEmailNaoCadastrado() {
        String resultado = authService.fazerLogin("naoexiste@email.com", "Senha@123");
        assertEquals("Credenciais inválidas", resultado, "Deveria retornar erro para e-mail não cadastrado.");
    }

    @Test
    void negarLoginComSenhaIncorreta() {
        Usuario novoUsuario = new Usuario("Agabo", "agabo@email.com", "111.222.333-44", "Senha@123", LocalDate.parse("1990-01-01"));
        repository.salvar(novoUsuario);

        String resultado = authService.fazerLogin("agabo@email.com", "senhaErrada");
        assertEquals("Credenciais inválidas", resultado, "Deveria negar o acesso para senha incorreta.");
    }

   
    // GUSTAVO 
    

    @Test
    void devePrevenirSqlInjectionNoLogin() {
        Usuario usuarioReal = new Usuario("Admin", "admin@email.com", "122.322.323-15", "senha@123", LocalDate.parse("2026-06-05"));
        repository.salvar(usuarioReal);

       
        String emailMalicioso = "admin@email.com' OR '1'='1";
        String senhaQualquer = "qualquercoisa";

       
        String resultado = authService.fazerLogin(emailMalicioso, senhaQualquer);
        assertEquals("Credenciais inválidas", resultado, "O sistema não deve permitir o login com tentativa de injeção de SQL");
    }

    @Test
    void deveBloquearUsuarioAposTresTentativasErradas() {
        // Criando com a ordem correta e senha forte
        Usuario usuario = new Usuario("Tiago", "tiago@email.com", "000.000.000-00", "Senha@123", LocalDate.parse("2000-01-01"));
        repository.salvar(usuario);

        // Simulação de erros (usando o nosso authService)
        authService.fazerLogin("tiago@email.com", "senha_errada_1");
        authService.fazerLogin("tiago@email.com", "senha_errada_2");
        authService.fazerLogin("tiago@email.com", "senha_errada_3");

        // Busca na memoria o usuario atualizado para exibi-lo
        Usuario usuarioAposErros = repository.buscarPorEmail("tiago@email.com");

        // Junit valida se o estado mudou para bloqueado
        assertTrue(usuarioAposErros.isBloqueado(), "O usuário deveria ser bloqueado após a terceira tentativa falha.");
    }
}