package com.litun.maze.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.litun.maze.Labyrinth;

/**
 * Switch labyrinth
 * Created by user on 22.03.2015.
 */
public class SwitchSystem extends EntitySystem {
    Labyrinth labyrinth;
    boolean inProcess = false;
    private ImmutableArray<Entity> entities;

    public SwitchSystem(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    @Override
    public void addedToEngine(Engine engine) {
        //entities = engine.getEntitiesFor(Family.getFor(TileIndexComponent.class));
        //Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener()));
    }

    @Override
    public void update(float deltaTime) {

        super.update(deltaTime);
    }

    public void start() {
        inProcess = true;
    }

    public void setNewCenter(int i, int j){

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

