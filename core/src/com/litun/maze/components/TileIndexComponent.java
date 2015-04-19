package com.litun.maze.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by user on 22.03.2015.
 */
public class TileIndexComponent extends Component {
    public TileIndexComponent(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int i, j;
}
