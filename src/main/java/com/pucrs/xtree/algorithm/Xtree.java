package com.pucrs.xtree.algorithm;

import com.pucrs.xtree.models.Node;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Xtree {

    /**
     * Algorithm responsible for compute a xtree and convert
     * the expression in a tree structure.
     *
     * Exemple;
     *     xtree: 2 1 0 2 50 10 1 1 0 3 39 51 87 79 32
     *     tree:
     *           - (32)
     *              - (50, 10)
     *              - (79)
     *                  - (39, 51, 87)
     *
     * @param expressionParam xtree
     * @return tree structure
     * @author Igor Duarte - igorduarte.s@gmail.com
     */

    public Node execute(String expressionParam) {
        List<Integer> expression = Arrays.stream(expressionParam.split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        long n1 = Instant.now().getNano();
        long m1 = Instant.now().toEpochMilli();

        Node node = compute(expression, 0);

        long nanoTime = Instant.now().getNano() - n1;
        long milliTime = Instant.now().toEpochMilli() - m1;

        System.out.println(String.format("Tempo de execução: %dus", nanoTime));
        System.out.println(String.format("Tempo de execução: %dms", milliTime));

        return node;
    }

    private Node compute(List<Integer> expression, int beginIndex) {
        int childrenSize = expression.get(beginIndex);
        Node node = new Node();
        node.setBeginIndex(beginIndex);

        if (childrenSize > 0) {
            computeChildren(node, expression, beginIndex + 2, childrenSize);
            computeElements(node, expression, node.getLastChild().getEndIndex() + 1);
        } else {
            computeElements(node, expression, beginIndex + 2);
        }

        return node;
    }

    private void computeChildren(Node node, List<Integer> expression, int beginIndex, int childrenSize) {
        for (int i = 0; i < childrenSize; i++) {
            Node child = compute(expression, beginIndex);
            beginIndex = child.getEndIndex() + 1;
            node.getChildren().add(child);
        }
    }

    private void computeElements(Node node, List<Integer> expression, int beginIndex) {
        int elementsSize = expression.get(node.getBeginIndex() + 1);
        List<Integer> elements = new ArrayList<Integer>();
        int elementPosition = beginIndex;

        for (int i = 0; i < elementsSize; i++, elementPosition++) {
            elements.add(expression.get(elementPosition));
        }

        node.setElements(elements);
        node.setEndIndex(elementPosition - 1);
    }
}
