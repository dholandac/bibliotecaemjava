import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Biblioteca {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Obra> publicacoes = new ArrayList<>();

    Biblioteca() {
        exibirMenu();
    }

    void exibirMenu() {
        int opcao;

        System.out.println("\n/////////////// Biblioteca ///////////////");
        System.out.println("1) Adicionar publicação");
        System.out.println("2) Buscar publicação");
        System.out.println("3) Carregar publicação");
        System.out.println("4) Exibir publicações cadastradas");
        System.out.println("5) Sair");

        try {
            opcao = sc.nextInt();
        }
        catch (Exception e) {
            opcao = 0;
            sc.nextLine();
        }

        switch (opcao) {
            case 1:
                adicionarPublicacao();
                break;
            case 2:
                buscarPublicacoes();
                break;
            case 3:
                carregarPublicacao();
                break;
            case 4:
                exibirPublicacoes();
                break;
            case 5:
                sc.close();
                System.out.println("\n!!!!!! Saindo do programa !!!!!!");
                break;
            default:
                System.out.println("\n!!!!!! Opção inválida, voltando ao menu !!!!!!");
                exibirMenu();
        }
    }

    void adicionarPublicacao() {
        int tipoPublicao;
        String respostaSalvar;
        String titulo;
        String autor;
        String resumo;
        String periodicidade;
        String refUnica;
        String isbn;

        System.out.println("\n\n\n\n\n\n///////////// Criação de Publicação /////////////");

        while (true) {
            System.out.println("/////// Informe o tipo da sua publicação: ///////\n1) Revista\n2) Artigo\n3) Livro");

            try {
                tipoPublicao = sc.nextInt();

                if (tipoPublicao > 3 || tipoPublicao < 1) {
                    System.out.println("\n!!!!!! Tipo de publicação inválida !!!!!!\n");
                }
                else {
                    break;
                }
            }
            catch (Exception e) {
                System.out.println("\n!!!!!! O valor inserido não é um número !!!!!!\n");
                sc.nextLine();
            }
        }

        sc.nextLine();
        while (true) {
            System.out.println("/////// Informe o título da sua publicação: ///////");
            titulo = sc.nextLine();

            if (titulo.length() < 2) {
                System.out.println("\n!!!!!! Título de publicação inválido !!!!!!\n");
            }
            else {
                break;
            }
        }

        while (true) {
            System.out.println("/////// Informe o autor da sua publicação: ///////");
            autor = sc.nextLine();

            if (autor.length() < 2) {
                System.out.println("\n!!!!!! Autor da publicação inválido !!!!!!\n");
            }
            else {
                break;
            }
        }

        while (true) {
            System.out.println("/////// Escreva um resumo da sua publicação: ///////");
            resumo = sc.nextLine();

            if (resumo.length() < 15) {
                System.out.println("\n!!!!!! O resumo da publicação está curto demais !!!!!!\n");
            }
            else {
                break;
            }
        }

        switch (tipoPublicao) {
            case 1:
                while (true) {
                    System.out.println("/////// Insira a periodicidade de sua revista: ///////");
                    periodicidade = sc.nextLine();

                    if (periodicidade.length() < 5) {
                        System.out.println("\n!!!!!! Periodicidade inválida !!!!!!\n");
                    }
                    else {
                        break;
                    }
                }
                publicacoes.add(new Revista(titulo, autor, resumo, periodicidade));
                break;
            case 2:
                while (true) {
                    System.out.println("/////// Insira a referência única de seu artigo: ///////");
                    refUnica = sc.nextLine();

                    if (refUnica.length() < 15) {
                        System.out.println("\n!!!!!! Referência única inválida !!!!!!\n");
                    }
                    else {
                        break;
                    }
                }
                publicacoes.add(new Artigo(titulo, autor, resumo, refUnica));
                break;
            case 3:
                while (true) {
                    System.out.println("/////// Insira o ISBN de seu livro: ///////");
                    isbn = sc.nextLine();

                    if (isbn.length() < 10) {
                        System.out.println("\n!!!!!! ISBN inválida !!!!!!\n");
                    }
                    else {
                        break;
                    }
                }
                publicacoes.add(new Livro(titulo, autor, resumo, isbn));
                break;
        }

        while (true) {
            System.out.println("///// Você deseja salvar a obra em um arquivo? (s/n) /////");
            respostaSalvar = sc.nextLine();

            if (respostaSalvar.equalsIgnoreCase("s")) {
                Obra obra = publicacoes.get(publicacoes.size() - 1);
                obra.toFile();

                System.out.println("\n!!!!!! Você exportou a obra como arquivo com sucesso !!!!!!\n");
                break;
            }
            else if (respostaSalvar.equalsIgnoreCase("n")) {
                System.out.println("\n!!!!!! Você optou por não exportar a obra como arquivo !!!!!!\n");
                break;
            }
            else {
                System.out.println("\n!!!!!! O valor inserido não é uma opção válida !!!!!!\n");
            }
        }

        exibirMenu();
    }

    void carregarPublicacao() {
        String titulo;
        int contador = 0;

        System.out.println("\n\n\n\n\n\n///// Insira o título da obra que deseja carregar /////");
        sc.nextLine();
        titulo = sc.nextLine();

        System.out.println("\n///// Obra carregada /////");
        for (Obra o : publicacoes) {
            if (Objects.equals(o.titulo, titulo)) {
                contador++;
                o.loadFile();
            }
        }

        if (contador == 0) {
            System.out.println("\n!!!!!! O título inserido não corresponde a uma obra salva !!!!!!\n");
        }

        exibirMenu();
    }

    void exibirPublicacoes() {
        System.out.println("\n\n\n\n\n\n///// Lista de obras criadas /////");
        for (Obra o : publicacoes) {
            System.out.println(o.toString());
        }

        exibirMenu();
    }

    void buscarPublicacoes() {
        String busca;
        boolean encontrou = false;

        System.out.println("\n\n\n\n\n\n///// Insira uma palavra chave /////");
        sc.nextLine();
        busca = sc.nextLine();

        System.out.println("\n///// Lista de obras encontradas /////");
        for(Obra o : publicacoes) {
            if(o.titulo.toLowerCase().contains(busca)) {
                System.out.println(o);
                encontrou = true;
            }
        }

        if(!encontrou) {
            System.out.println("!!!!!! Título não encontrado !!!!!!");
        }

        exibirMenu();
    }
}