package com.litun.maze;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by user on 16.04.2015.
 */
public class Tiles {
    private static Vector2[][] storage;

    public static void setPositions(float fromX, float fromY, float toX, float toY, int size) {
        storage = new Vector2[size][size];
        float placeHeight = (toY - fromY) / size,
                placeWidth = (toX - fromX) / size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                storage[i][j] = new Vector2(fromX + placeWidth / 2 + placeHeight * i,
                        fromY + placeHeight / 2 + placeHeight * j);
            }
        }
    }

    public static Vector2 getPosition(int i, int j) {
        return storage[i][j];
    }

    private static Entity[][] tiles;

    public static void setTiles(Entity[][] entities) {
        tiles = entities;
    }

    public static Entity getTile(int i, int j) {
        if (i < 0 || i >= tiles.length || j < 0 || j >= tiles.length)
            return null;
        return tiles[i][j];
    }
}
