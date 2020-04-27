package br.com.pi6;

public class Principal {

    public static void main(String args[]) {

        ProblemaDesignacao problemaDesignacao = new ProblemaDesignacao();

        problemaDesignacao.CarregaMatriz();

        System.out.println("Tabela de Custos de Transporte");
        problemaDesignacao.MostraTabelaCusto();

        System.out.println();
        problemaDesignacao.CalculaNovaTabela();

        System.out.println("Tabela de Custos Reduzida");
        problemaDesignacao.MostraTabelaCustoReduzida();

        System.out.println();
        problemaDesignacao.CalculaDesignacao();

        System.out.println("Designacao de Transporte");
        problemaDesignacao.MostraDesignacao();

        System.out.println();
    }
}
