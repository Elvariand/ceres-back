package com.jlgdev.ceres.nodeFilters;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

public class SpoonIngredientMenuFilter implements NodeFilter {

    @Override
    public short acceptNode(Node n) {
        if (n.getNodeName().contentEquals("spoonacular-ingredient-menu")) {
            return FILTER_ACCEPT;
        }
        return FILTER_SKIP;
    }

}
