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

    public Graph<String, String> readGraphForFile() throws IOException {
        File fileMovie = new File(this.filePathMovie);
        BufferedReader fileBufferMovie = new BufferedReader(new FileReader(fileMovie));
        File fileCast = new File(this.filePathCast);
        BufferedReader fileBufferCast = new BufferedReader(new FileReader(fileCast));
        Graph<String, String> graphFile = new SparseMultigraph<String, String>();
        graphFile.addVertex("Director");
        graphFile.addVertex("Cast");
        while (fileBufferMovie.ready()) {
            String lineFileMovie = fileBufferMovie.readLine();
            Boolean isDirector =  Boolean.TRUE;
            if(!lineFileMovie.isEmpty()) {
                String [] movie = lineFileMovie.split(";");
                String lineFileCast = fileBufferCast.readLine();
                graphFile.addVertex(movie[1].trim());
                for(String nameCast : lineFileCast.split(";")) {
                    if (!graphFile.getVertices().contains(nameCast.trim())) {
                        graphFile.addVertex(nameCast.trim());
                    }
                    if (isDirector) {
                        if(!graphFile.getOutEdges("Director").contains(nameCast.trim())) {
                            graphFile.addEdge(nameCast.trim(), "Director", nameCast.trim(), EdgeType.UNDIRECTED);
                        }
                        isDirector = Boolean.FALSE;
                    } else {
                        if (!graphFile.getOutEdges("Cast").contains(nameCast.trim())) {
                            graphFile.addEdge(nameCast.trim(), "Cast", nameCast.trim(), EdgeType.UNDIRECTED);
                        }
                    }
                    graphFile.addEdge(movie[2] + " - " + nameCast.trim(), movie[1], nameCast.trim(), EdgeType.UNDIRECTED);
                }
            }
        }
        fileBufferMovie.close();
        fileBufferCast.close();
        return graphFile;
    }

}
