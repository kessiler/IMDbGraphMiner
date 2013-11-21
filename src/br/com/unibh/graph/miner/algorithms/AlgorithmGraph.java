package br.com.unibh.graph.miner.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import edu.uci.ics.jung.graph.Graph;

public class AlgorithmGraph {

	private Graph<String, String> graph;
	
	public AlgorithmGraph() {}
		
	public void setGraph(Graph<String, String> graph) {
		this.graph = graph;
	}
	
	public int getCountActors() {		
		return graph.getNeighborCount("Cast");
	}
	
	public int getCountDirectors() {		
		return graph.getNeighborCount("Director");
	}
	
	public List<String> getDirectorsforMovie(String nameMovie) {
		List<String> directors = new ArrayList<String>();
		if (graph.getNeighborCount(nameMovie) > 0) {
			List<String> vertexAdjacents =  new ArrayList<String>();
			vertexAdjacents.addAll(graph.getNeighbors(nameMovie));
			 for(String names : vertexAdjacents) {
				 if(graph.getIncidentCount(names) > 0) {
					 List<String> incideVertices = new ArrayList<String>(graph.getIncidentVertices((names)));
					 if (incideVertices.contains("Director")) {
						 directors.add(names);
					 }
				 }
			 }
		}
		return directors;
	}
	
	public List<String> getActorsforMovie(String nameMovie) {
		List<String> actors = new ArrayList<String>();
		if (graph.getNeighborCount(nameMovie) > 0) {
			List<String> vertexAdjacents =  new ArrayList<String>();
			vertexAdjacents.addAll(graph.getNeighbors(nameMovie));
			 for(String names : vertexAdjacents) {
				 if(graph.getIncidentCount(names) > 0) {
					 List<String> incideVertices = new ArrayList<String>(graph.getIncidentVertices((names)));
					 if (incideVertices.contains("Cast")) {
						 actors.add(names);
					 }
				 }
			 }
		}
		return actors;
	}
	
	public List<String> getRankingDirectorsforQtdMovie() {
		List<String> directors = new ArrayList<String>();
		if (graph.getNeighborCount("Director") > 0) {
			HashMap<Integer, String> mapDirector = new HashMap<Integer, String>();
			for(String diretor : graph.getNeighbors("Director")) {
				mapDirector.put(graph.getNeighborCount(diretor), diretor);
			}
			TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>(mapDirector);
			for (Iterator<Entry<Integer, String>> iterator = treeMap.entrySet().iterator(); iterator.hasNext();) {
				Entry<Integer, String> entry = iterator.next();
				directors.add((String)entry.getValue());
			}
		}
		return directors;
		
	}
	public List<String> getRankingActorsforQtdMovie() {
		List<String> actors = new ArrayList<String>();
		if (graph.getNeighborCount("Cast") > 0) {
			HashMap<Integer, String> mapDirector = new HashMap<Integer, String>();
			for(String diretor : graph.getNeighbors("Cast")) {
				mapDirector.put(graph.getNeighborCount(diretor), diretor);
			}
			TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>(mapDirector);
			for (Iterator<Entry<Integer, String>> iterator = treeMap.entrySet().iterator(); iterator.hasNext();) {
				Entry<Integer, String> entry = iterator.next();				
				actors.add((String)entry.getValue());
			}
		}
		return actors;
		
	}
}
