package com.litun.maze.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.litun.maze.Labyrinth;
import com.litun.maze.MainGame;
import com.litun.maze.Tiles;
import com.litun.maze.components.MainTriangleComponent;
import com.litun.maze.components.PositionComponent;
import com.litun.maze.screens.FinishScreen;

/**
 * Move character
 * Created by user on 10.04.2015.
 */
public class CharacterSystem extends EntitySystem {
    Labyrinth labyrinth;
    private final Game game;
    boolean inProcess = false;
    Entity character;


    public CharacterSystem(Labyrinth labyrinth, Game game) {
        this.labyrinth = labyrinth;
        this.game = game;
        //mainComponent = character.getComponent(MainTriangleComponent.class);
    }

    Vector2 startPosition, fihishPosition;
    float speed = 1;
    //Vector3 currentPosition;
    Vector3 characterPosition;
    MainTriangleComponent mainComponent;
    Vector2 direction;
    float distance;

    void updateCharacter() {
        character = com.litun.maze.Character.getCharacter();
        characterPosition = character.getComponent(PositionComponent.class).center;
        mainComponent = character.getComponent(MainTriangleComponent.class);
    }

    void startMoving() {
        startPosition = new Vector2(characterPosition.x, characterPosition.y);
        distance = startPosition.dst2(fihishPosition);
        direction = new Vector2(fihishPosition.x - startPosition.x, fihishPosition.y - startPosition.y).nor();
        speed = distance / 10;
        inProcess = true;
        labyrinth.switchDimension();
    }

    public boolean goLeft() {
        updateCharacter();
        if (!inProcess && labyrinth.toLeft(mainComponent.i, mainComponent.j)) {
            fihishPosition = Tiles.getPosition(mainComponent.i - 1, mainComponent.j);
            mainComponent.i -= 1;
            startMoving();
            return true;
        }
        return false;
    }

    public boolean goRight() {
        updateCharacter();
        if (!inProcess && labyrinth.toRight(mainComponent.i, mainComponent.j)) {
            fihishPosition = Tiles.getPosition(mainComponent.i + 1, mainComponent.j);
            mainComponent.i += 1;
            startMoving();
            return true;
        }
        return false;
    }

    public boolean goUp() {
        updateCharacter();
        if (!inProcess && labyrinth.toUp(mainComponent.i, mainComponent.j)) {
            fihishPosition = Tiles.getPosition(mainComponent.i, mainComponent.j + 1);
            mainComponent.j += 1;
            startMoving();
            return true;
        }
        return false;
    }

    public boolean goDown() {
        updateCharacter();
        if (!inProcess && labyrinth.toDown(mainComponent.i, mainComponent.j)) {
            fihishPosition = Tiles.getPosition(mainComponent.i, mainComponent.j - 1);
            mainComponent.j -= 1;
            startMoving();
            return true;
        }
        return false;
    }


    @Override
    public void update(float deltaTime) {
        if (inProcess) {
            if (characterPosition.dst2(startPosition.x, startPosition.y, 0) > distance) {
                characterPosition.set(fihishPosition.x, fihishPosition.y, characterPosition.z);
                inProcess = false;
            } else
                characterPosition.add(direction.x * speed * deltaTime, direction.y * speed * deltaTime, 0);

        } else if (mainComponent != null) {
            if (mainComponent.i == 9 && mainComponent.j == 9) {
                game.setScreen(new FinishScreen((MainGame) game, true));
            } else if (!labyrinth.toLeft(mainComponent.i, mainComponent.j) &&
                    !labyrinth.toDown(mainComponent.i, mainComponent.j) &&
                    !labyrinth.toUp(mainComponent.i, mainComponent.j) &&
                    !labyrinth.toRight(mainComponent.i, mainComponent.j)) {
                game.setScreen(new FinishScreen((MainGame) game, false));
            }
        }

    }
}
