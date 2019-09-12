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
        var expression = Arrays.stream(expressionParam.split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        var n1 = Instant.now().getNano();
        var m1 = Instant.now().toEpochMilli();

        var node = compute(expression, 0);

        var nanoTime = Instant.now().getNano() - n1;
        var milliTime = Instant.now().toEpochMilli() - m1;

        System.out.println(String.format("Tempo de execução: %dus", nanoTime));
        System.out.println(String.format("Tempo de execução: %dms", milliTime));

        return node;
    }

    private Node compute(List<Integer> expression, int beginIndex) {
        var childrenSize = expression.get(beginIndex);
        var node = new Node();
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
            var child = compute(expression, beginIndex);
            beginIndex = child.getEndIndex() + 1;
            node.getChildren().add(child);
        }
    }

    private void computeElements(Node node, List<Integer> expression, int beginIndex) {
        var elementsSize = expression.get(node.getBeginIndex() + 1);
        var elements = new ArrayList<Integer>();
        var elementPosition = beginIndex;

        for (int i = 0; i < elementsSize; i++, elementPosition++) {
            elements.add(expression.get(elementPosition));
        }

        node.setElements(elements);
        node.setEndIndex(elementPosition - 1);
    }
}
