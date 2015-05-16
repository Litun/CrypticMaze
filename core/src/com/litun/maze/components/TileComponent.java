package com.litun.maze.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by user on 22.03.2015.
 */
public class TileComponent extends Component {
    public TileComponent(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int i, j;
}
