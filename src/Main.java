import javax.swing.*;

import br.com.unibh.graph.miner.file.FileGraph;
import br.com.unibh.graph.miner.algorithms.AlgorithmGraph;
import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileGraph graphForFile = new FileGraph();
        graphForFile.setFilePathMovie("./resources/movies.csv");
        graphForFile.setFilePathCast("./resources/cast.csv");
        Graph<String, String> graph = graphForFile.readGraphForFile();
        AbstractLayout<String, String> layoutGraphResult = new CircleLayout<String, String>(graph);
        Dimension preferredSize = new Dimension(800, 600);
        BasicVisualizationServer<String,String> visualizationGraphResult = new BasicVisualizationServer<String,String>(layoutGraphResult, preferredSize);
        visualizationGraphResult.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
        visualizationGraphResult.getRenderContext().setEdgeLabelTransformer(new Transformer<String, String>() {
            @Override
            public String transform(String aresta) {
                return aresta;
            }
        });
        Renderer.VertexLabel<String, String> VertexGraphResult = visualizationGraphResult.getRenderer().getVertexLabelRenderer();
        VertexGraphResult.setPosition(Renderer.VertexLabel.Position.CNTR);
        JFrame frame = new JFrame();
        frame.getContentPane().add(visualizationGraphResult);
        frame.pack();
        frame.setTitle("Miner Graph");
        JMenuBar menuBar = new JMenuBar();
        JMenu mnuVisualization = new JMenu("Visualizações");
        JMenuItem mnuCircle = new JMenuItem("Circle Layout");
        JMenuItem mnuISOM    = new JMenuItem("ISOM Layout");
        JMenuItem mnuKK     = new JMenuItem("KKLayout Layout");
        JMenuItem mnuFR     = new JMenuItem("FRLayout Layout");
        JMenuItem mnuSpring = new JMenuItem("SpringLayout Layout");
        JMenuItem mnuExit   = new JMenuItem("Sair");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
