package br.com.pi6;

import java.io.*;

public class ProblemaDesignacao {
    public static int MaxLin = 4; //Numero maximo de linhas
    public static int MaxCol = 4; //Numero maximo de colunas
    public static int i, j, aux; //Variaveis auxiliares
    public static int[][] TabelaCusto = new int[MaxLin][MaxCol]; //Matriz com os custos de transporte
    public static int[][] TabelaCustoReduzida = new int[MaxLin][MaxCol]; //Matriz com os custos de transporte reduzidos
    public static int[][] TabelaDesignacao = new int[MaxLin][MaxCol]; //Matriz com as designacoes de transporte
    public static int[] MarcaLinha = new int[MaxLin]; //Vetor auxiliar para marcar se a linha correspondente da tabela de designacao ja foi usada
    public static int[] MarcaColuna = new int[MaxCol]; //Vetor auxiliar para marcar se a coluna correspondente da tabela de designacao ja foi usada
    public static int[] ValorLinha = new int[MaxLin]; //Vetor com o menor valor de cada linha
    public static int[] ValorColuna = new int[MaxCol]; //Vetor com o menor valor de cada coluna

    //Funcao para carregar os valores do arquivo input.txt na matriz com os custos de transporte
    public void CarregaMatriz() {
        try {
            FileReader Arquivo = new FileReader("/home/robson/Projects/pi6/src/main/resources/input.txt");
            BufferedReader Dados = new BufferedReader(Arquivo);
            for (i = 0; i < MaxLin; i++) {
                for (j = 0; j < MaxCol; j++) {
                    TabelaCusto[i][j] = Integer.parseInt(Dados.readLine());
                }
            }
            Dados.close(); //Fecha E/S Dados para economia de recursos da maquina
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //Funcao para copiar os dados da tabela de custos de transporte para a tabela de custos de transporte reduzidos
    public void CopiaTabela() {
        for (i = 0; i < MaxLin; i++) {
            for (j = 0; j < MaxCol; j++) {
                TabelaCustoReduzida[i][j] = TabelaCusto[i][j];
            }
        }
    }

    //Funcao para mostrar a matriz com os custos de transporte
    public void MostraTabelaCusto() {
        for (i = 0; i < MaxLin; i++) {
            for (j = 0; j < MaxCol; j++) {
                System.out.format("%5d", TabelaCusto[i][j]);
            }
            System.out.println();
        }
    }

    //Funcao para mostrar a matriz com os custos de transporte reduzidos
    public void MostraTabelaCustoReduzida() {
        for (i = 0; i < MaxLin; i++) {
            for (j = 0; j < MaxCol; j++) {
                System.out.format("%5d", TabelaCustoReduzida[i][j]);
            }
            System.out.println();
        }
    }

    //Funcao para mostrar a matriz com a designacao de transporte
    public void MostraDesignacao() {
        for (i = 0; i < MaxLin; i++) {
            for (j = 0; j < MaxCol; j++) {
                System.out.format("%5d", TabelaDesignacao[i][j]);
            }
            System.out.println();
        }
    }

    //Funcao para calcular o menor valor de cada linha da matriz de custos de transporte
    public void CalculaMinValorLinha() {
        for (i = 0; i < MaxLin; i++) {
            aux = TabelaCustoReduzida[i][0];
            for (j = 0; j < MaxCol; j++) {
                if (TabelaCustoReduzida[i][j] < aux) {
                    aux = TabelaCustoReduzida[i][j];
                }
            }
            ValorLinha[i] = aux;
        }
    }

    //Funcao para calcular o menor valor de cada coluna da matriz de custos de transporte
    public void CalculaMinValorColuna() {
        for (j = 0; j < MaxCol; j++) {
            aux = TabelaCustoReduzida[0][j];
            for (i = 0; i < MaxLin; i++) {
                if (TabelaCustoReduzida[i][j] < aux) {
                    aux = TabelaCustoReduzida[i][j];
                }
            }
            ValorColuna[j] = aux;
        }
    }

    //Funcao para calcular a nova matriz de custos de transporte
    public void CalculaNovaTabela() {
        CopiaTabela();
        CalculaMinValorLinha();
        for (i = 0; i < MaxLin; i++) {
            for (j = 0; j < MaxCol; j++) {
                TabelaCustoReduzida[i][j] = TabelaCustoReduzida[i][j] - ValorLinha[i];
            }
        }
        CalculaMinValorColuna();
        for (j = 0; j < MaxCol; j++) {
            for (i = 0; i < MaxLin; i++) {
                TabelaCustoReduzida[i][j] = TabelaCustoReduzida[i][j] - ValorColuna[j];
            }
        }
    }

    //Funcao para calcular a designacao de transporte
    public void CalculaDesignacao() {
        for (i = 0; i < MaxLin; i++) {
            for (j = 0; j < MaxCol; j++) {
                if (TabelaCustoReduzida[i][j] == 0 && MarcaLinha[i] == 0 && MarcaColuna[j] == 0) {
                    TabelaDesignacao[i][j] = 1;
                    MarcaLinha[i] = 1;
                    MarcaColuna[j] = 1;
                }
            }
        }
    }

}
