package com.pucrs.xtree.algorithm;

import com.pucrs.xtree.models.Node;

import java.util.List;

public class Statistics {

    public static long getNodesLength(Node node) {
        return computeLength(node.getChildren(), 1);
    }

    private static long computeLength(List<Node> children, long sum) {
        for (Node child : children)
            sum = computeLength(child.getChildren(), ++sum);

        return sum;
    }


    //todo: to implement
    public static long getHeight(Node node) {
        return 0L;
    }

    //todo: to implement
    public static long getSum(Node node) {
        return 0L;
    }

}
