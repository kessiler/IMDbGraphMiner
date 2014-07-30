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
					System.out.println("Quantity of actors: " + algGraph.getCountActors());
					System.out.println("Quantity of directors: " + algGraph.getCountDirectors());
					System.out.println("Director of specific movie: " + algGraph.getDirectorsforMovie("Avatar (2009)"));
					System.out.println("Actors of a specific movie: " + algGraph.getActorsforMovie("Avatar (2009)"));
					System.out.println("Ranking directors x movies: " + algGraph.getRankingDirectorsforQtdMovie());
					System.out.println("Ranking actors x movies: " + algGraph.getRankingActorsforQtdMovie());
                    System.out.println("Ranking directors x box office: " + algGraph.getRankingDirectorsforPrice());
                    System.out.println("Ranking actors x box office: " + algGraph.getRankingActorsforPrice());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
