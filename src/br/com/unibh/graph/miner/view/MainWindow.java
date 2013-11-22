package br.com.unibh.graph.miner.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.unibh.graph.miner.struct.FileGraph;
import br.com.unibh.graph.miner.struct.LayoutTypeGraph;
import edu.uci.ics.jung.graph.Graph;

public class MainWindow extends JFrame {
	private JPanel contentPane;
	private JMenuBar mnuBar;
	private JMenu mnuVisualization;
	private JMenuItem mnuCircle;
	private JMenuItem mnuISOM;
	private JMenuItem mnuKK;
	private JMenuItem mnuFR;	
	private JMenuItem mnuExit;
	private JMenuItem mnuUpdateGraph;
	private FileGraph graphForFile;
	private Graph<String, String> graph;

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Miner Graph");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		createJMenuBar();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createJMenuBar() {
        mnuBar = new JMenuBar();
        mnuVisualization = new JMenu("Visualizações");
        mnuCircle = new JMenuItem("Layout circular");
        mnuISOM   = new JMenuItem("Layout self-map (Meyer's)");
        mnuKK     = new JMenuItem("Layout com distâncias específicas (Kamada-Kawai)");
        mnuFR     = new JMenuItem("Layout FR - Fruchterman-Reingold");
        mnuUpdateGraph  = new JMenuItem("Recriar grafo");
        mnuExit   = new JMenuItem("Sair");
        initEvents();
        mnuVisualization.add(mnuCircle);
        mnuVisualization.add(mnuISOM);
        mnuVisualization.add(mnuKK);
        mnuVisualization.add(mnuFR);                
        mnuVisualization.add(mnuUpdateGraph);
        mnuVisualization.addSeparator();
        mnuVisualization.add(mnuExit);
        mnuBar.add(mnuVisualization);
        setJMenuBar(mnuBar);	
	}
	
	public Graph<String, String> getGraph() {
		return graph;
	}
	
	private void initEvents() {
		mnuExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnuCircle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showGraphView();
			}
		});
		mnuISOM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showGraphView(LayoutTypeGraph.LAYOUT_ISOM);
			}
		});
		mnuKK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showGraphView(LayoutTypeGraph.LAYOUT_KK);
			}
		});
		mnuFR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showGraphView(LayoutTypeGraph.LAYOUT_FR);
			}
		});		
		mnuUpdateGraph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					readFileForGraph();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getStackTrace().toString());
				}
				showGraphView();
			}
		});
	}
	
	public void readFileForGraph() throws IOException {
        graphForFile = new FileGraph();
        graphForFile.setFilePathMovie("./resources/movies.csv");
        graphForFile.setFilePathCast("./resources/cast.csv");
        graph = graphForFile.readGraphForFile();
	}
	
	public void showGraphView(LayoutTypeGraph type) {
		GraphLayouts.showGraphLayout(getContentPane(), graph, type);
	}
	public void showGraphView() {
		showGraphView(LayoutTypeGraph.LAYOUT_CIRCLE);
	}
}
