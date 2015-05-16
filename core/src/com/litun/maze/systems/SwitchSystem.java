package com.litun.maze.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.litun.maze.Labyrinth;
import com.litun.maze.components.BridgeComponent;
import com.litun.maze.components.PositionComponent;
import com.litun.maze.components.TextureComponent;
import com.litun.maze.switchtasks.SwitchTask;
import com.litun.maze.switchtasks.SwitchTaskGetter;

import java.util.ArrayList;

/**
 * Switch labyrinth bridges
 * Created by user on 22.03.2015.
 */
public class SwitchSystem extends EntitySystem {
    Labyrinth labyrinth;
    boolean inProcess = false;
    private ImmutableArray<Entity> bridges;
    private final Family family = Family.all(TextureComponent.class, PositionComponent.class,
            BridgeComponent.class).get();
    private ArrayList<SwitchTask> tasks = new ArrayList<SwitchTask>(200);

    public SwitchSystem(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    @Override
    public void addedToEngine(Engine engine) {
        //entities = engine.getEntitiesFor(Family.getFor(TileComponent.class));
        //Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener()));
        bridges = engine.getEntitiesFor(family);
    }

    @Override
    public void update(float deltaTime) {
        boolean finished = true;
        for (SwitchTask task : tasks) {
            if (!task.finished) {
                task.update(deltaTime);
                finished = false;
            }
        }
        if (finished) {
            tasks.clear();
        }
    }

    public void start() {
        inProcess = true;
        for (Entity bridge : bridges) {
            BridgeComponent bridgeComponent = bridge.getComponent(BridgeComponent.class);
            boolean nextDimentionExistence = bridgeComponent.direction == BridgeComponent.BridgeDirection.DOWN ?
                    labyrinth.toDownNextDimention(bridgeComponent.i, bridgeComponent.j) :
                    labyrinth.toRightNextDimention(bridgeComponent.i, bridgeComponent.j);
            SwitchTask task = SwitchTaskGetter.getTask(bridge, nextDimentionExistence);
            if (task != null)
                tasks.add(task);
        }
    }

    public boolean goLeft() {
        return true;
    }

    public boolean goRight() {
        return true;
    }

    public boolean goUp() {
        return true;
    }

    public boolean goDown() {
        return true;
    }
}
