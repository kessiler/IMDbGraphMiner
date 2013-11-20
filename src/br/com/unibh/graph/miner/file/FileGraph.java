package br.com.unibh.graph.miner.file;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileGraph {
    private String filePathMovie;
    private String filePathCast;

    public void FileGraph() {}

    public String getFilePathCast() {
        return filePathCast;
    }

    public void setFilePathCast(String filePathCast) {
        this.filePathCast = filePathCast;
    }

    public String getFilePathMovie() {
        return filePathMovie;
    }

    public void setFilePathMovie(String filePathMovie) {
        this.filePathMovie = filePathMovie;
    }

    public Graph readGraphForFile() throws IOException {
        File fileMovie = new File(this.filePathMovie);
        BufferedReader fileBufferMovie = new BufferedReader(new FileReader(fileMovie));
        File fileCast = new File(this.filePathCast);
        BufferedReader fileBufferCast = new BufferedReader(new FileReader(fileCast));
        List<String> namesCast = new ArrayList<String>();
        Map<String, List<String>> listGraph = new HashMap<String, List<String>>();
        while (fileBufferMovie.ready()) {
            String lineFileMovie = fileBufferMovie.readLine();
            if(!lineFileMovie.isEmpty()) {
                String [] movie = lineFileMovie.split(";");
                String lineFileCast = fileBufferCast.readLine();
                for(String nameCast : lineFileCast.split(";")) {
                    if(!nameCast.isEmpty()) {
                        namesCast.add(nameCast);
                    }
                }
                listGraph.put(movie[1], namesCast);
            }
        }
        Graph graphFile = new SparseMultigraph<String, String>();
        Iterator<Map.Entry<String, List<String>>> hashGraph = listGraph.entrySet().iterator();
        while (hashGraph.hasNext()) {
            Map.Entry<String, List<String>> graphNodes = hashGraph.next();
            graphFile.addVertex(graphNodes.getKey().toString());
            for(String cast : graphNodes.getValue()) {
                graphFile.addVertex(cast);
                graphFile.addEdge("", graphNodes.getKey().toString(), cast, EdgeType.UNDIRECTED);
            }
        }
        return graphFile;
    }

}
