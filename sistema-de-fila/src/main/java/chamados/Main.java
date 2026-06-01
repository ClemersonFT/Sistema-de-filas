package chamados;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SistemaDeChamado sistema = new SistemaDeChamado();

    public static void main(String[] args) {
        cabecalho();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            opcao = lerInteiro("Opcao: ");
            System.out.println();

            switch (opcao) {
                case 1 -> adicionarChamado();
                case 2 -> atenderProximo();
                case 3 -> consultarProximo();
                case 4 -> listarPendentes();
                case 5 -> quantidadeNaFila();
                case 0 -> System.out.println("Sistema encerrado. Ate logo!");
                default -> System.out.println("Opcao invalida. Tente novamente.");
            }
            System.out.println();
        }
    }


    private static void cabecalho() {
        System.out.println("==========================================");
        System.out.println("   SISTEMA DE CHAMADOS — DEPARTAMENTO TI  ");
        System.out.println("==========================================");
    }

    private static void exibirMenu() {
        System.out.println("------------------------------------------");
        System.out.println(" 1. Registrar novo chamado");
        System.out.println(" 2. Atender proximo chamado");
        System.out.println(" 3. Ver proximo (sem atender)");
        System.out.println(" 4. Listar chamados pendentes");
        System.out.println(" 5. Quantidade de chamados na fila");
        System.out.println(" 0. Encerrar sistema");
        System.out.println("------------------------------------------");
    }


    private static void adicionarChamado() {
        System.out.println("=== Novo Chamado ===");
        int prioridade = lerInteiro("Nivel de prioridade (inteiro, menor = mais urgente): ");
        System.out.print("Descricao do problema: ");
        String descricao = scanner.nextLine().trim();

        Chamado novo = new Chamado(prioridade, descricao);
        sistema.enfileirar(novo);

        System.out.printf("[OK] Chamado registrado com sucesso! %s%n", novo);
        System.out.printf("     Chamados na fila: %d%n", sistema.tamanho());
    }

    private static void atenderProximo() {
        try {
            Chamado atendido = sistema.proximo();
            System.out.println("[OK] Chamado atendido: " + atendido);
            System.out.printf("     Chamados restantes: %d%n", sistema.tamanho());
        } catch (IllegalStateException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    private static void consultarProximo() {
        try {
            Chamado proximo = sistema.consultarProximo();
            System.out.println("Proximo a ser atendido: " + proximo);
        } catch (IllegalStateException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    private static void listarPendentes() {
        System.out.printf("--- Chamados pendentes (%d) ---%n", sistema.tamanho());
        sistema.exibirChamadosPendentes();
    }

    private static void quantidadeNaFila() {
        System.out.printf("Total de chamados aguardando atendimento: %d%n", sistema.tamanho());
    }


    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Entrada invalida. Digite um numero inteiro.");
            }
        }
    }
}
