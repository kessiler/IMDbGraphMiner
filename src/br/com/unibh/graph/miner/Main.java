package br.com.unibh.graph.miner;
import java.awt.EventQueue;
import java.io.IOException;

import br.com.unibh.graph.miner.algorithms.AlgorithmGraph;
import br.com.unibh.graph.miner.view.MainWindow;

public class Main {
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.readFileForGraph();
					frame.showGraphView();
					frame.pack();
					frame.setVisible(true);
					AlgorithmGraph algGraph = new AlgorithmGraph();
					algGraph.setGraph(frame.getGraph());
					System.out.println("Qtd de atores: " + algGraph.getCountActors());
					System.out.println("Qtd de diretores: " + algGraph.getCountDirectors());
					System.out.println("Diretor de um filme especifico: " + algGraph.getDirectorsforMovie("Avatar (2009)"));
					System.out.println("Atores de um filme especifico: " + algGraph.getActorsforMovie("Avatar (2009)"));
					System.out.println("Ranking diretores x filmes: " + algGraph.getRankingDirectorsforQtdMovie());
					System.out.println("Ranking atores x filmes: " + algGraph.getRankingActorsforQtdMovie());
                    System.out.println("Ranking diretores x bilheteria: " + algGraph.getRankingDirectorsforPrice());
                    System.out.println("Ranking atores x bilheteria: " + algGraph.getRankingActorsforPrice());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
