package br.com.unibh.graph.miner;
import javax.swing.JFrame;

import br.com.unibh.graph.miner.file.FileGraph;
import br.com.unibh.graph.miner.algorithms.AlgorithmGraph;
import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileGraph graphForFile = new FileGraph();
        graphForFile.setFilePathMovie("./resources/movies.csv");
        graphForFile.setFilePathCast("./resources/cast.csv");
        Graph<String, String> graph = graphForFile.readGraphForFile();
        AbstractLayout<String, String> layoutGraphResult = new CircleLayout<String, String>(graph);
        BasicVisualizationServer<String,String> visualizationGraphResult = new BasicVisualizationServer<String,String>(layoutGraphResult);
        visualizationGraphResult.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
        Renderer.VertexLabel<String, String> VertexGraphResult = visualizationGraphResult.getRenderer().getVertexLabelRenderer();
        VertexGraphResult.setPosition(Renderer.VertexLabel.Position.CNTR);
        JFrame frame = new JFrame();
        frame.getContentPane().add(visualizationGraphResult);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Quantidade de arestas: " + graph.getEdgeCount());
		System.out.println("Quantidade de vértices " + graph.getVertexCount());
		System.out.println("Arestas: " + graph.getEdges());
		System.out.println("Vértices " + graph.getVertices());		
    }
}
