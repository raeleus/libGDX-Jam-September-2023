package com.ray3k.template.entities;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.ray3k.template.OgmoReader.*;

public class GraphNode {
    public Array<EntityNode> connectionsTemp;
    public GridPoint2 coord;
    public GraphNode(int x, int y) {
        coord = new GridPoint2(x, y);
    }
}
