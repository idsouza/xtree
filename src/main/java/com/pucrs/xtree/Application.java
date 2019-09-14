package com.pucrs.xtree;

import com.pucrs.xtree.algorithm.Statistics;
import com.pucrs.xtree.algorithm.Xtree;
import com.pucrs.xtree.models.Node;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {

    public static void main(String[] args) throws Exception {
        String fileName = "casom13.txt";
        Path path = Paths.get(Application.class.getClassLoader().getResource(fileName).toURI());

        String expression = Files.readAllLines(path)
                .stream()
                .reduce(String::concat)
                .map(String::trim)
                .get();

        Node node = new Xtree().execute(expression);

        long nodesLength = Statistics.getNodesLength(node);
        System.out.println(String.format("Número de nodos: %d", nodesLength));
    }

}
