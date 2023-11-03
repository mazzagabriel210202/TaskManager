package Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private String nome;
    private Integer senha;
    private List<String> tarefas;

    public TaskManager(String nome, Integer senha) {
        this.nome = nome;
        this.senha = senha;
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(String tarefa) {
        tarefas.add(tarefa);
        System.out.println("Tarefa adicionada: " + tarefa);
    }

    public void retirarTarefa(String tarefa) {
        if (tarefas.remove(tarefa)) {
            System.out.println("Tarefa removida: " + tarefa);
        } else {
            System.out.println("Tarefa não encontrada: " + tarefa);
        }
    }

    public void atualizarTarefa(String tarefaAntiga, String novaTarefa) {
        if (tarefas.contains(tarefaAntiga)) {
            tarefas.set(tarefas.indexOf(tarefaAntiga), novaTarefa);
            System.out.println("Tarefa atualizada de: " + tarefaAntiga + " para: " + novaTarefa);
        } else {
            System.out.println("Tarefa não encontrada: " + tarefaAntiga);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        String nome = sc.next();

        Integer senha = null;

        boolean senhaValida = false;
        while (!senhaValida) {
            try {
                System.out.print("Digite sua senha (um número positivo): ");
                senha = sc.nextInt();
                if (senha < 0) {
                    throw new IllegalArgumentException("A senha deve ser um número positivo.");
                }
                senhaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: A senha deve ser um número.");
                sc.nextLine(); // Limpar o buffer de entrada
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        TaskManager taskManager = new TaskManager(nome, senha);

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Adicionar Tarefa");
            System.out.println("2 - Retirar Tarefa");
            System.out.println("3 - Atualizar Tarefa");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite a tarefa a ser adicionada: ");
                    String tarefaAdicionar = sc.next();
                    taskManager.adicionarTarefa(tarefaAdicionar);
                    break;

                case 2:
                    System.out.print("Digite a tarefa a ser retirada: ");
                    String tarefaRetirar = sc.next();
                    taskManager.retirarTarefa(tarefaRetirar);
                    break;

                case 3:
                    System.out.print("Digite a tarefa a ser atualizada: ");
                    String tarefaAntiga = sc.next();
                    System.out.print("Digite a nova tarefa: ");
                    String novaTarefa = sc.next();
                    taskManager.atualizarTarefa(tarefaAntiga, novaTarefa);
                    break;

                case 4:
                    System.out.println("Saindo do programa.");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}