package com.pucrs.xtree.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node {

    private List<Integer> elements = new ArrayList<>();
    private LinkedList<Node> children = new LinkedList<>();
    private int beginIndex;
    private int endIndex;

    public Node() {}

    public Node(List<Integer> elements) {
        this.elements = elements;
    }

    public Node(List<Integer> elements, LinkedList<Node> children) {
        this.elements = elements;
        this.children = children;
    }

    public List<Integer> getElements() {
        return elements;
    }

    public void setElements(List<Integer> elements) {
        this.elements = elements;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Node> children) {
        this.children = children;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    @Override
    public String toString() {
        return "Node{" +
                "elements=" + elements +
                ", children=" + children +
                ", beginIndex=" + beginIndex +
                ", endIndex=" + endIndex +
                '}';
    }

    public Node getLastChild() {
        int childrenSize = this.getChildren().size();
        return this.getChildren().get(childrenSize - 1);
    }
}
