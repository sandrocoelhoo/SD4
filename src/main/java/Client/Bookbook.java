package Client;

import Grafo.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import java.util.Scanner;

public class Bookbook {
    public static void main(String[] args) throws Exception {
                
        Scanner sc = new Scanner(System.in);
        
        Vertice v;
        Aresta a;
        int nomeUsuario, nomeUsuarioIncremental, nomeLivro, nomeLivroIncremental, cor, opc, nomeUsuarioAux, aux;
        float peso;
        boolean direcao;
        String descricao;
        
        opc = -1;
        nomeUsuario = 0;
        //nomeUsuarioIncremental = 0;
        //nomeLivroIncremental = 10000;
        nomeUsuarioAux = 0;
        nomeUsuarioIncremental = conectar(args).listVerticesDoGrafo().size();
        nomeLivroIncremental = conectar(args).listVerticesDoGrafo().size();
        aux = 0;
        nomeLivro = 1;
        
        while(opc != 15){
            System.out.println("\nBem vindo(a) a maior e melhor rede social de empréstimos de livros do universo!");
            System.out.println("-----BOOKBOOK-----");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Criar perfil");
            System.out.println("2 - Editar perfil");
            System.out.println("3 - Buscar perfil");
            System.out.println("4 - Remover perfil");
            System.out.println("5 - Cadastrar novo livro");
            System.out.println("6 - Editar livro");
            System.out.println("7 - Buscar livro mais próximo");
            System.out.println("8 - Remover livro");
            System.out.println("9 - Solicitar conexão");
            System.out.println("10 - Aceitar conexão");
            System.out.println("11 - Remover conexão");
            System.out.println("12 - Solicitar Empréstimo de livro");
            System.out.println("13 - Aceitar Pedido de Empréstimo");
            System.out.println("14 - Remover Empréstimo");
            System.out.println("15 - Sair");
            opc = sc.nextInt();
            sc.nextLine();
            
            switch(opc){
                case 1:
                    /*
                    nomeUsuarioIncremental++;
                    try {
                        conectar(args).readVertice(nomeUsuarioIncremental);
                    }catch (NullException ex) {
                    }
                    */
                    
                    nomeUsuario = nomeUsuarioIncremental++;
                    try {
                        Vertice vertice;
                        while (true) {
                            vertice = conectar(args).readVertice(nomeUsuario);
                            nomeUsuario = nomeUsuarioIncremental++;
                        }
                    } catch (NullException ex) {
                    }
                    
                    cor = 1;
                    
                    System.out.println("Digite seu nome: ");
                    descricao = sc.nextLine();
                    sc.nextLine();
                    
                    peso = 0;
                    
                    v = new Vertice(nomeUsuarioIncremental, cor, descricao, peso);
                    if (conectar(args).createVertice(v)) {
                        System.out.println("Parabéns "+descricao+"! Seu perfil foi criado com sucesso. ID: "+nomeUsuarioIncremental);
                    } else {
                        System.out.println("Ops! Algo deu errado. Seu perfil não pôde ser criado :(");
                    }
                    
                    break;
                /************************************************************************************/
                case 2:
                    System.out.println("Digite o ID do Usuário: ");
                    nomeUsuario = sc.nextInt();
                    sc.nextLine();                    
                    
                    cor = 1;
                    
                    System.out.println("Digite seu nome: ");
                    descricao = sc.nextLine();
                    sc.nextLine();
                    
                    peso = 1;

                    v = new Vertice(nomeUsuario, cor, descricao, peso);
                    if (v.cor == 1) {
                        if (conectar(args).updateVertice(v)) {
                            System.out.println("Dados alterados com sucesso!");
                        } else {
                            System.out.println("O usuário "+descricao+" não existe.");
                        }
                    }
                    
                    break;
                /************************************************************************************/    
                case 3:
                    System.out.println("Digite o ID do Usuário: ");
                    nomeUsuario = sc.nextInt();
                    sc.nextLine();

                    try {
                        v = conectar(args).readVertice(nomeUsuario);
                        if (v.cor == 1) {
                            System.out.println("Usuário encontrado: "+v.desc);
                        }
                    } catch (NullException ex) {
                        System.out.println("Usuário não encontrado.");
                    }
                    
                    break;
                /************************************************************************************/    
                case 4:
                    System.out.println("Digite o ID do Usuário: ");
                    nomeUsuario = sc.nextInt();
                    sc.nextLine();
                    
                    try {
                        v = conectar(args).readVertice(nomeUsuario);
                        if (v.cor == 1) {
                            if (conectar(args).deleteVertice(nomeUsuario)) {
                                System.out.println("Usuário removido com sucesso!");
                            } else {
                                System.out.println("Usuário não encontrado.");
                            }
                        }
                    } catch (NullException ex) {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                /************************************************************************************/    
                case 5:
                    System.out.println("ID do Usuário que adicionará um livro: ");
                    nomeUsuario = sc.nextInt();
                    sc.nextLine();

                    try {
                        v = conectar(args).readVertice(nomeUsuario);
                        String usuario = v.getDesc(); // usuário é o dono do livro (nome da aresta)
                        if (v.cor == 1) {
                            
                            nomeLivro = nomeLivroIncremental++;
                            try {
                                Vertice vertice;
                                while (true) {
                                    vertice = conectar(args).readVertice(nomeLivro);
                                    nomeLivro = nomeLivroIncremental++;
                                }
                            } catch (NullException ex) {
                            }
                            
                            /*
                            nomeLivroIncremental++;
                            try {
                                conectar(args).readVertice(nomeLivroIncremental);
                            }catch (NullException ex) {
                            }
                            */

                            cor = 2;

                            System.out.println("Digite o título: ");
                            descricao = sc.nextLine();
                            sc.nextLine();

                            peso = 0;

                            v = new Vertice(nomeLivro, cor, descricao, peso);
                            a = new Aresta(nomeUsuario, nomeLivro, peso, true, usuario);
                            if (conectar(args).createVertice(v) && conectar(args).createAresta(a)) {
                                System.out.println("Livro cadastrado com sucesso no nome de: "+usuario);
                                System.out.println("ID do livro: "+nomeLivro);
                            } else {
                                System.out.println("Ops! Algo deu errado. O livro não pôde ser cadastrado :(");
                            }
                        }
                    } catch (NullException ex) {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                /************************************************************************************/
                case 6:
                    System.out.println("Digite o ID do livro: ");
                    nomeLivro = sc.nextInt();
                    sc.nextLine();                    
                    
                    cor = 2;
                    
                    System.out.println("Digite o novo Título: ");
                    descricao = sc.nextLine();
                    sc.nextLine();
                    
                    peso = 1;

                    v = new Vertice(nomeLivro, cor, descricao, peso);
                    if (v.cor == 2) {
                        if (conectar(args).updateVertice(v)) {
                            System.out.println("Dados alterados com sucesso!");
                        } else {
                            System.out.println("O livro "+descricao+" não existe.");
                        }
                    }
                    break;
                /************************************************************************************/    
                case 7:
                    System.out.println("Digite o ID do livro: ");
                    nomeLivro = sc.nextInt();
                    sc.nextLine();

                    try {
                        v = conectar(args).readVertice(nomeLivro);
                        if (v.cor == 2) {
                            System.out.println("Livro encontrado: "+v.desc);
                        }
                    } catch (NullException ex) {
                        System.out.println("Livro não encontrado.");
                    }
                    
                    break;
                /************************************************************************************/    
                case 8:
                    System.out.println("Digite o ID do livro: ");
                    nomeLivro = sc.nextInt();
                    sc.nextLine();
                    
                    try {
                        v = conectar(args).readVertice(nomeLivro);
                        if (v.cor == 2) {
                            if (conectar(args).deleteVertice(nomeLivro)) {
                                System.out.println("Livro removido com sucesso!");
                            } else {
                                System.out.println("Livro não encontrado.");
                            }
                        }
                    } catch (NullException ex) {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                /************************************************************************************/    
                case 9:
                    System.out.println("Solicitação de: ");
                    nomeUsuario = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Solicitação para: ");
                    nomeUsuarioAux = sc.nextInt();
                    sc.nextLine();
                    peso = 0;                    

                    try {
                        v = conectar(args).readVertice(nomeUsuario);
                        if (v.cor == 1) {
                            try {
                                Vertice vAux = conectar(args).readVertice(nomeUsuarioAux);
                                descricao = "Solicitação de: "+v.getDesc();
                                if (vAux.cor == 1) {
                                    a = new Aresta(nomeUsuario, nomeUsuarioAux, peso, true, descricao);
                                    if (conectar(args).createAresta(a)) {
                                        System.out.println("Solicitação enviada!");
                                    } else {
                                        System.out.println("Não foi possível realizar essa solicitação :(");
                                    }
                                }
                            } catch (NullException ex) {
                                System.out.println("Usuário não encontrado");
                            }
                        }
                    } catch (NullException ex) {
                        System.out.println("Usuário não encontrado");
                    }
                    break;
                /************************************************************************************/    
                case 10:
                    System.out.println("ID do usuário: ");
                    nomeUsuario = sc.nextInt();
                    sc.nextLine();

                    try {
                        v = conectar(args).readVertice(nomeUsuario);
                        if (v.cor == 1) {
                            try {
                                List<Aresta> solicitacoes = conectar(args).listArestasDoVertice(nomeUsuario);
                                for (Aresta aresta : solicitacoes) {
                                    if (nomeUsuario == aresta.vertice2 && aresta.isDirec() && aresta.getPeso() == 0) {
                                        System.out.println("Deseja aceitar a "+aresta.getDesc()+"?");
                                        System.out.println("1 - Sim");
                                        System.out.println("2 - Não");
                                        System.out.println("--> ");
                                        aux = sc.nextInt();
                                        sc.nextLine();
                                        
                                        if(aux == 1){
                                            a = new Aresta(aresta.vertice1, aresta.vertice2, 0, false, "Conexão");
                                            if(conectar(args).deleteAresta(aresta.vertice1, aresta.vertice2) && conectar(args).createAresta(a)){
                                                System.out.println("Parabéns! Você possui uma nova conexão.");
                                            }
                                        }
                                        else if(conectar(args).deleteAresta(aresta.vertice1, aresta.vertice2))
                                            System.out.println("Conexão rejeitada.");
                                    }
                                }
                                System.out.println("Não há mais solicitações");
                            } catch (NullException ex) {
                                System.out.println("Não há nenhuma solicitação para este perfil!\n");
                            }
                        }
                    } catch (NullException ex) {
                        System.out.println("Usuário não encontrado\n");
                    }

                    break;
                /************************************************************************************/    
                case 11:
                    System.out.println("ID do Usuário: ");
                    nomeUsuario = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Remover conexão com: ");
                    nomeUsuarioAux = sc.nextInt();
                    sc.nextLine();

                    try {
                        a = conectar(args).readAresta(nomeUsuario, nomeUsuarioAux);
                        if (!a.direc) {
                            if (conectar(args).deleteAresta(nomeUsuario, nomeUsuarioAux)) {
                                System.out.println("Conexão desfeita!");
                            } else {
                                System.out.println("A conexão não pôde ser desfeita.");
                            }
                        }
                    } catch (NullException e) {
                        System.out.println("Vocês não estão conectados.");
                    }

                    break;
                /************************************************************************************/   
                case 12:
                    System.out.println("ID do Usuário: ");
                    nomeUsuario = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Pedir empréstimo para: ");
                    nomeUsuarioAux = sc.nextInt();
                    sc.nextLine();
                    
                    Aresta arestaUsuario;
                    //Aresta arestaLivro;
                    
                    try {
                    a = conectar(args).readAresta(nomeUsuario, nomeUsuarioAux);
                    
                        if(!a.direc) {
                            List<Vertice> vertices = conectar(args).listVizinhosDoVertice(nomeUsuarioAux);
                            for(Vertice vert : vertices){
                                if(vert.getCor() == 2 && vert.getPeso() == 0){
                                    System.out.println("Deseja pegar o livro "+vert.getDesc()+"?");
                                    System.out.println("1 - Sim");
                                    System.out.println("2 - Não");
                                    System.out.println("--> ");
                                    aux = sc.nextInt();
                                    sc.nextLine();

                                    if(aux == 1){
                                        arestaUsuario = conectar(args).readAresta(nomeUsuarioAux, vert.getNome());
                                        if(arestaUsuario.direc){
                                            List<Aresta> arestaLivro = conectar(args).listArestasDoVertice(vert.getNome());
                                            if(arestaLivro.size() == 1)
                                            {
                                                Aresta aresta = new Aresta(vert.getNome(), nomeUsuarioAux, 2, true, "O usuário "+nomeUsuario+" solicitou o livro: "+vert.getDesc());
                                                if(conectar(args).createAresta(aresta)){
                                                    System.out.println("Empréstimo solicitado.");
                                                }
                                                else{
                                                    System.out.println("Erro ao pedir o empréstimo.");
                                                }
                                            }
                                            else{
                                                System.out.println("Esse livro já está emprestado.");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (NullException e) {
                        System.out.println("Vocês não estão conectados.");
                    } 
                    break;
                /************************************************************************************/   
                case 13:
                    System.out.println("ID do usuário: ");
                    nomeUsuario = sc.nextInt();
                    sc.nextLine();
                    
                    List<Vertice> vertices = conectar(args).listVizinhosDoVertice(nomeUsuario);
                    for(Vertice vert : vertices){
                        if(vert.getCor() == 2 && vert.getPeso() == 0){
                            List<Aresta> arestasLivro = conectar(args).listArestasDoVertice(vert.getNome());
                            for(Aresta aresta : arestasLivro){
                                if(aresta.getPeso() == 2){
                                    System.out.println(aresta.getDesc());
                                    System.out.println("Deseja emprestar o livro?");
                                    System.out.println("1 - Sim");
                                    System.out.println("2 - Não");
                                    System.out.println("--> ");
                                    aux = sc.nextInt();
                                    sc.nextLine();

                                    if(aux == 1){
                                        int vertice1, vertice2;
                                        vertice1 = aresta.vertice1;
                                        vertice2 = aresta.vertice2;
                                        if(conectar(args).deleteAresta(aresta.vertice1, aresta.vertice2)){ 
                                            Aresta arestaAux = new Aresta(vertice1, vertice2, 1, true, "Livro "+vert.getDesc()+" emprestado");
                                            if(conectar(args).createAresta(arestaAux)){
                                                System.out.println("Livro emprestado.");
                                            }
                                            else{
                                                System.out.println("Erro ao emprestar o livro.");
                                            }
                                        }
                                        else{
                                            System.out.println("Ops! Erro ao emprestar o livro :(");
                                        }
                                    }
                                    else{
                                        if(conectar(args).deleteAresta(aresta.vertice1, aresta.vertice2)){
                                            System.out.println("Solicitaçao de emprestimo excluida.");
                                        }
                                        else{
                                            System.out.println("Erro ao excluir a solicitaçao de emprestimo.");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                /************************************************************************************/
                case 14:
                    System.out.println("ID do usuário: ");
                    nomeUsuario = sc.nextInt();
                    sc.nextLine();
                    
                    List<Vertice> verticesUsuario = conectar(args).listVizinhosDoVertice(nomeUsuario);
                    for(Vertice vert : verticesUsuario){
                        if(vert.getCor() == 2){
                            List<Aresta> arestasLivro = conectar(args).listArestasDoVertice(vert.getNome());
                            for(Aresta aresta : arestasLivro){
                                if(aresta.getPeso() == 1){
                                    System.out.println(aresta.getDesc());
                                    System.out.println("Deseja remover o empréstimo?");
                                    System.out.println("1 - Sim");
                                    System.out.println("2 - Não");
                                    System.out.println("--> ");
                                    aux = sc.nextInt();
                                    sc.nextLine();

                                    if(aux == 1){
                                        if(conectar(args).deleteAresta(aresta.vertice1, aresta.vertice2)){
                                            System.out.println("Empréstimo removido com sucesso.");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
            v = null;
            a = null;
        }
    }
    
    public static Handler.Client conectar(String[] servers) throws ArrayIndexOutOfBoundsException, NumberFormatException, TTransportException, TException {

        int counter = 0;
        TTransport transport;
        TProtocol protocol;
        Handler.Client client = null;

        while (client == null && counter < 3) {
            for (int i = 0; i < servers.length; i += 2) {
                try {
                    transport = new TSocket(servers[i], Integer.parseInt(servers[i + 1]));
                    transport.open();
                    protocol = new TBinaryProtocol(transport);
                    client = new Handler.Client(protocol);
                    break;
                } catch (TTransportException ex) {
                }
            }
            counter++;
        }
        if (client == null) {
            throw new TTransportException("MAX_atingido");
        } else {
            return client;
        }
    }
}
