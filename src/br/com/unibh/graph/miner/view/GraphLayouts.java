package br.com.unibh.graph.miner.view;

import java.awt.Container;
import java.awt.Dimension;

import br.com.unibh.graph.miner.struct.LayoutTypeGraph;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;

public class GraphLayouts {
	public static void showGraphLayout(Container content, Graph<String, String> graph, LayoutTypeGraph type) {
		AbstractLayout<String, String> layoutGraphView = null;
		switch(type) {
			case LAYOUT_FR:
				layoutGraphView = new FRLayout<String, String>(graph);
				break;
			case LAYOUT_ISOM:
				layoutGraphView = new ISOMLayout<String, String>(graph);
				break;
			case LAYOUT_KK:
				layoutGraphView = new KKLayout<String, String>(graph);
				break;
			default:
				layoutGraphView = new CircleLayout<String, String>(graph);
				break;
		}
        Dimension preferredSize = new Dimension(800, 600);
        BasicVisualizationServer<String,String> visualizationGraphResult = new BasicVisualizationServer<String,String>(layoutGraphView, preferredSize);
        visualizationGraphResult.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
        visualizationGraphResult.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());
        Renderer.VertexLabel<String, String> VertexGraphResult = visualizationGraphResult.getRenderer().getVertexLabelRenderer();
        VertexGraphResult.setPosition(Renderer.VertexLabel.Position.CNTR);
        content.removeAll();
        content.add(visualizationGraphResult);
        content.revalidate();
	}
}
