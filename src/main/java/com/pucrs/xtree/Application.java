package com.pucrs.xtree;

import com.pucrs.xtree.algorithm.Statistics;
import com.pucrs.xtree.algorithm.Xtree;

import java.nio.file.Files;
import java.nio.file.Path;

public class Application {

    public static void main(String[] args) throws Exception {
        var fileName = "casom13.txt";
        var path = Path.of(Application.class.getClassLoader().getResource(fileName).toURI());

        var expression = Files.readAllLines(path)
                .stream()
                .reduce(String::concat)
                .map(String::trim)
                .get();

        var node = new Xtree().execute(expression);

        var nodesLength = Statistics.getNodesLength(node);
        System.out.println(String.format("Número de nodos: %d", nodesLength));
    }

}
