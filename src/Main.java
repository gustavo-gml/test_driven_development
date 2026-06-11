

import main.models.Usuario;
import main.repository.UsuarioRepository;
import main.services.AuthService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        UsuarioRepository repository = new UsuarioRepository();
        AuthService authService = new AuthService(repository);

        boolean executando = true;

        System.out.println("========================================");
        System.out.println("🚀 BEM-VINDO AO SISTEMA DE AUTENTICAÇÃO");
        System.out.println("========================================");

        while (executando) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Fazer Login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    cadastrarUsuario(scanner, repository);
                    break;
                case "2":
                    fazerLogin(scanner, authService);
                    break;
                case "3":
                    System.out.println("\nSaindo do sistema... Até logo!");
                    executando = false;
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void cadastrarUsuario(Scanner scanner, UsuarioRepository repository) {
        System.out.println("\n--- CADASTRO DE USUÁRIO ---");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Senha (mín. 8 chars, 1 número, 1 especial): ");
            String senha = scanner.nextLine();

            System.out.print("Data de Nascimento (AAAA-MM-DD): ");
            String dataStr = scanner.nextLine();
            LocalDate dataNascimento = LocalDate.parse(dataStr);

            // Tenta criar e salvar. Se algo estiver errado, vai cair direto no catch!
            Usuario novoUsuario = new Usuario(nome, email, cpf, senha, dataNascimento);
            repository.salvar(novoUsuario);

            System.out.println("\n✅ Usuário cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            // Captura as validações do Gustavo e do José
            System.out.println("\n❌ Erro de Validação: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("\n❌ Erro: Formato de data inválido. Use o padrão AAAA-MM-DD.");
        } catch (Exception e) {
            System.out.println("\n❌ Erro inesperado ao cadastrar.");
        }
    }

    private static void fazerLogin(Scanner scanner, AuthService authService) {
        System.out.println("\n--- LOGIN ---");
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Chama o serviço do Ágabo e do Tiago
        String resultado = authService.fazerLogin(email, senha);

        if (resultado.equals("Sucesso")) {
            System.out.println("\n🔓 Login realizado com sucesso! Bem-vindo.");
        } else if (resultado.equals("Conta bloqueada")) {
            System.out.println("\n⛔ " + resultado + "! Contate o administrador.");
        } else {
            System.out.println("\n❌ " + resultado);
        }
    }
}