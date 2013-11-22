package br.com.unibh.graph.miner.algorithms;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

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
            HashMap<String, Integer> mapDirector = new HashMap<String, Integer>();
            for(String diretor : graph.getNeighbors("Director")) {
                mapDirector.put(diretor, graph.getNeighborCount(diretor) - 1);
            }
            List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(mapDirector.entrySet());
            Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            for(Iterator<Entry<String, Integer>> iterator = entryList.iterator(); iterator.hasNext();) {
                Entry<String, Integer> entry = iterator.next();
                directors.add(entry.getKey() + " - " + entry.getValue());
            }
		}
		return directors;
		
	}
	public List<String> getRankingActorsforQtdMovie() {
		List<String> actors = new ArrayList<String>();
		if (graph.getNeighborCount("Cast") > 0) {
            HashMap<String, Integer> mapActor = new HashMap<String, Integer>();
			for(String actor : graph.getNeighbors("Cast")) {
				mapActor.put(actor, graph.getNeighborCount(actor) - 1);
			}
            List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(mapActor.entrySet());
            Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            for(Iterator<Entry<String, Integer>> iterator = entryList.iterator(); iterator.hasNext();) {
                Entry<String, Integer> entry = iterator.next();
                actors.add(entry.getKey() + " - " + entry.getValue());
            }
		}
		return actors;
	}

    public List<String> getRankingDirectorsforPrice() {
        List<String> directors = new ArrayList<String>();
        if (graph.getNeighborCount("Director") > 0) {
            TreeMap<BigDecimal, String> mapDirector = new TreeMap<BigDecimal, String>();
            for(String diretor : graph.getNeighbors("Director")) {
                List<String> edgePonderate = new ArrayList<String>(graph.getIncidentEdges(diretor));
                edgePonderate.remove(diretor);
                for(String nameEdge : edgePonderate) {
                    String[] nameEdgeSeparator = nameEdge.split("-");
                    mapDirector.put(new BigDecimal(nameEdgeSeparator[0].trim()), nameEdgeSeparator[1]);
                }
            }
            NavigableMap<BigDecimal, String> nMap = mapDirector.descendingMap();
            for (Iterator<Entry<BigDecimal, String>> iterator = nMap.entrySet().iterator(); iterator.hasNext();) {
                Entry<BigDecimal, String> entry = iterator.next();
                directors.add(entry.getValue() + " - " + entry.getKey().toString());
            }

        }
        return directors;
    }

    public List<String> getRankingActorsforPrice() {
        List<String> actors = new ArrayList<String>();
        if (graph.getNeighborCount("Cast") > 0) {
            TreeMap<BigDecimal, String> mapActor = new TreeMap<BigDecimal, String>();
            for(String actor : graph.getNeighbors("Cast")) {
                List<String> edgePonderate = new ArrayList<String>(graph.getIncidentEdges(actor));
                edgePonderate.remove(actor);
                for(String nameEdge : edgePonderate) {
                    String[] nameEdgeSeparator = nameEdge.split("-");
                    mapActor.put(new BigDecimal(nameEdgeSeparator[0].trim()), nameEdgeSeparator[1]);
                }
            }
            NavigableMap<BigDecimal, String> nMap = mapActor.descendingMap();
            for (Iterator<Entry<BigDecimal, String>> iterator = nMap.entrySet().iterator(); iterator.hasNext();) {
                Entry<BigDecimal, String> entry = iterator.next();
                actors.add(entry.getValue() + " - " + entry.getKey().toString());
            }

        }
        return actors;
    }

}
