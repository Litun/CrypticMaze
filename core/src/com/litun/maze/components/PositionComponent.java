package com.litun.maze.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by user on 10.03.2015.
 */
public class PositionComponent extends Component {

    public PositionComponent(float x, float y, float z) {
        center = new Vector3(x, y, z);
    }

    public PositionComponent(Vector2 center) {
        this.center = new Vector3(center.x, center.y, 0);
    }

    public PositionComponent(Vector3 center) {
        this.center = center;
    }

    public PositionComponent() {
        center = new Vector3();
    }

    public final Vector3 center;
    public final Vector2 scale = new Vector2(1.0f, 1.0f);
    public float rotation = 0.0f;
}
