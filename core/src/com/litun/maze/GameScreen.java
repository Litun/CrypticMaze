package com.litun.maze;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.litun.maze.components.PositionComponent;
import com.litun.maze.components.TextureComponent;
import com.litun.maze.components.TileIndexComponent;
import com.litun.maze.systems.*;

/**
 * Created by user on 10.03.2015.
 */
public class GameScreen extends ScreenAdapter {
    final Engine engine;
    Game game;

    //540x960

    GameScreen(MainGame mainGame) {
        game = mainGame;
        engine = new Engine();
        Labyrinth labyrinth = new Labyrinth(10, 3);
        Tiles.setPositions(0, 150, MainGame.VIRTUAL_WIDTH, 150 + MainGame.VIRTUAL_WIDTH, labyrinth.getSize());
        generateTiles(labyrinth.getSize());

        Entity character = Character.getCharacter();
        engine.addEntity(character);

        RenderingSystem renderingSystem = new RenderingSystem(mainGame.batch);
        engine.addEntityListener(renderingSystem);
        engine.addSystem(renderingSystem);

        engine.addSystem(new InputSystem());
        engine.addSystem(new CharacterSystem(labyrinth));
        engine.addSystem(new SwitchSystem(labyrinth));
        MoveTilesSystem moveTilesSystem=new MoveTilesSystem(labyrinth);
        engine.addSystem(moveTilesSystem);
        moveTilesSystem.start();

        Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener(engine)));
        //Gdx.input.in
    }

    void generateTiles(int size) {
        Entity[][] tiles = new Entity[size][size];

        TextureComponent tileTexture = new TextureComponent();
        tileTexture.region = Textures.tile;

        float tilePlace = MainGame.VIRTUAL_WIDTH / size;


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Entity tile = new Entity();

                PositionComponent tilePosition = new PositionComponent(Tiles.getPosition(i, j));
                float scale = tilePlace * 0.8f / tileTexture.region.getRegionWidth();
                tilePosition.scale.set(scale, scale);
                TileIndexComponent tileIndex = new TileIndexComponent(i, j);

                tile.add(tileTexture);
                tile.add(tilePosition);
                tile.add(tileIndex);

                engine.addEntity(tile);
                tiles[i][j] = tile;
            }
        }
        Tiles.setTiles(tiles);
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
    }
}
