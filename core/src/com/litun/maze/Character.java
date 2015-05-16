package com.litun.maze;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.litun.maze.components.MainTriangleComponent;
import com.litun.maze.components.PositionComponent;
import com.litun.maze.components.TextureComponent;

/**
 * Created by user on 14.04.2015.
 */
public class Character {
    private Character() {
    }

    private static Entity mainCharacter;

    public static Entity getCharacter() {
        if (mainCharacter == null) {
            Entity character = new Entity();
            MainTriangleComponent mainTriangleComponent=new MainTriangleComponent();
            character.add(mainTriangleComponent);

            Vector2 pos = Tiles.getPosition(mainTriangleComponent.i, mainTriangleComponent.j);
            PositionComponent position = new PositionComponent(pos.x, pos.y, 1);
            position.scale.set(0.6f * 0.8f, 0.6f * 0.8f);
            character.add(position);

            TextureComponent characterTexture = new TextureComponent();
            characterTexture.region = Textures.character;

            character.add(characterTexture);

            mainCharacter = character;
            return character;
        } else return mainCharacter;
    }

}
