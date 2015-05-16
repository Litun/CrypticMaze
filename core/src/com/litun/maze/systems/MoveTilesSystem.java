package com.litun.maze.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.litun.maze.Character;
import com.litun.maze.Labyrinth;
import com.litun.maze.Tiles;
import com.litun.maze.components.MainTriangleComponent;
import com.litun.maze.components.PositionComponent;
import com.litun.maze.components.TextureComponent;
import com.litun.maze.components.TileComponent;

import java.util.LinkedList;

/**
 * Move tiles
 * Created by user on 17.04.2015.
 */
public class MoveTilesSystem extends EntitySystem {
    Labyrinth labyrinth;
    Entity character;
    private final Family family = Family.all(TileComponent.class, PositionComponent.class, TextureComponent.class).get();
    boolean inProcess = false;
    ImmutableArray<Entity> tiles;
    LinkedList<MoveTask> tasks = new LinkedList<MoveTask>();

    public MoveTilesSystem(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    @Override
    public void addedToEngine(Engine engine) {
        character = Character.getCharacter();
        tiles = engine.getEntitiesFor(family);
    }

    public void start() {
        inProcess = true;
        MainTriangleComponent component = character.getComponent(MainTriangleComponent.class);
        int i = component.i,
                j = component.j;

        MoveTask task = new MoveTask(Tiles.getTile(i, j));
        task.moveDefault();
        tasks.add(task);

        Entity tile = Tiles.getTile(i - 1, j);
        if (tile != null) {
            task = new MoveTask(tile);
            if (labyrinth.toLeft(i, j))
                task.moveRigth();
            else task.moveDefault();
            tasks.add(task);
        }

        tile = Tiles.getTile(i + 1, j);
        if (tile != null) {
            task = new MoveTask(tile);
            if (labyrinth.toRight(i, j))
                task.moveLeft();
            else task.moveDefault();
            tasks.add(task);
        }

        tile = Tiles.getTile(i, j - 1);
        if (tile != null) {
            task = new MoveTask(tile);
            if (labyrinth.toDown(i, j))
                task.moveUp();
            else task.moveDefault();
            tasks.add(task);
        }

        tile = Tiles.getTile(i, j + 1);
        if (tile != null) {
            task = new MoveTask(tile);
            if (labyrinth.toUp(i, j))
                task.moveDown();
            else task.moveDefault();
            tasks.add(task);
        }

        setDefaults(i, j);
    }

    private final int[][] wave =
            {{0, 2}, {0, -2}, {2, 0}, {-2, 0},
                    {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    void setDefaults(int i, int j) {
        MoveTask task;
        Entity tile;
        for (int[] delta : wave) {
            tile = Tiles.getTile(i + delta[0], j + delta[1]);
            if (tile != null) {
                task = new MoveTask(tile);
                task.moveDefault();
                tasks.add(task);
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        boolean finished = true;
        for (MoveTask task : tasks) {
            if (!task.isFinished()) {
                task.update(deltaTime);
                finished = false;
            }
        }
        if (finished) {
            tasks.clear();
        }
    }
}

class MoveTask {
    Entity tile;
    PositionComponent position;
    Vector2 targetPosition, startPosition;
    boolean finished = true;

    MoveTask(Entity tile) {
        this.tile = tile;
        position = tile.getComponent(PositionComponent.class);
        startPosition = new Vector2(position.center.x, position.center.y);
        TileComponent tileIndex = tile.getComponent(TileComponent.class);
        targetPosition = new Vector2();
        targetPosition.add(Tiles.getPosition(tileIndex.i, tileIndex.j));
    }

    //private float delta= MainGame.FILL_FACTOR;

    void moveLeft() {
        targetPosition.sub(12, 0);
        startMoving();
    }

    void moveRigth() {
        targetPosition.add(12, 0);
        startMoving();
    }

    void moveUp() {
        targetPosition.add(0, 12);
        startMoving();
    }

    void moveDown() {
        targetPosition.sub(0, 12);
        startMoving();
    }

    void moveDefault() {
        startMoving();
    }

    Vector2 direction;
    float distance, speed;

    void startMoving() {
        finished = false;
        distance = startPosition.dst2(targetPosition);

        //hardcode
        if (distance < 0.1) {
            finished = true;
            return;
        }

        direction = new Vector2(targetPosition.x - startPosition.x, targetPosition.y - startPosition.y).nor();
        speed = distance / 2;
    }

    public void update(float deltaTime) {
        if (!finished) {
            if (position.center.dst2(startPosition.x, startPosition.y, 0) > distance) {
                position.center.set(targetPosition.x, targetPosition.y, position.center.z);
                finished = true;
            } else
                position.center.add(direction.x * speed * deltaTime, direction.y * speed * deltaTime, 0);
        }
    }

    public boolean isFinished() {
        return finished;
    }
}
