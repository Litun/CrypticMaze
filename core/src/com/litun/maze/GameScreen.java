package com.litun.maze;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.litun.maze.components.BridgeComponent;
import com.litun.maze.components.PositionComponent;
import com.litun.maze.components.TextureComponent;
import com.litun.maze.components.TileComponent;
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
        generateBridges(labyrinth.getSize());

        Entity character = Character.getCharacter();
        engine.addEntity(character);

        RenderingSystem renderingSystem = new RenderingSystem(mainGame.batch);
        engine.addEntityListener(renderingSystem);
        engine.addSystem(renderingSystem);

        engine.addSystem(new InputSystem());
        engine.addSystem(new CharacterSystem(labyrinth));
        engine.addSystem(new SwitchSystem(labyrinth));
        MoveTilesSystem moveTilesSystem = new MoveTilesSystem(labyrinth);
        engine.addSystem(moveTilesSystem);
        moveTilesSystem.start();

        Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener(engine)));
        //Gdx.input.getInputProcessor().
    }

    private void generateBridges(int size) {


        float tilePlace = MainGame.VIRTUAL_WIDTH / size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                TextureComponent bridgeTexture = new TextureComponent();
                bridgeTexture.region = Textures.tile;

                //bridge down
                if (j > 0) {
                    Entity bridgeDown = new Entity();

                    BridgeComponent bridgeComponent = new BridgeComponent(i, j, BridgeComponent.BridgeDirection.DOWN);
                    bridgeComponent.state = BridgeComponent.BridgeState.STAY;

                    PositionComponent bridgePosition = new PositionComponent(Tiles.getPosition(i, j));
                    bridgePosition.center.sub(0, tilePlace / 2, 1);
                    float scaleX = tilePlace / bridgeTexture.region.getRegionWidth(),
                            scaleY = tilePlace * 0.4f / bridgeTexture.region.getRegionWidth();
                    bridgePosition.scale.set(scaleX, scaleY);
                    bridgePosition.rotation = 90f;

                    bridgeDown.add(bridgeTexture);
                    bridgeDown.add(bridgeComponent);
                    bridgeDown.add(bridgePosition);

                    engine.addEntity(bridgeDown);
                }

                bridgeTexture = new TextureComponent();
                bridgeTexture.region = Textures.tile;

                //bridge right
                if (i < 9) {
                    Entity bridgeRight = new Entity();

                    BridgeComponent bridgeComponent = new BridgeComponent(i, j, BridgeComponent.BridgeDirection.RIGHT);
                    bridgeComponent.state = BridgeComponent.BridgeState.STAY;

                    PositionComponent bridgePosition = new PositionComponent(Tiles.getPosition(i, j));
                    bridgePosition.center.add(tilePlace / 2, 0, -1);
                    float scaleX = tilePlace / bridgeTexture.region.getRegionWidth(),
                            scaleY = tilePlace * 0.4f / bridgeTexture.region.getRegionWidth();
                    bridgePosition.scale.set(scaleX, scaleY);

                    bridgeRight.add(bridgeTexture);
                    bridgeRight.add(bridgeComponent);
                    bridgeRight.add(bridgePosition);

                    engine.addEntity(bridgeRight);
                }
            }
        }
    }


    void generateTiles(int size) {
        Entity[][] tiles = new Entity[size][size];

        TextureComponent tileTexture = new TextureComponent();
        tileTexture.region = Textures.tile;

        TextureComponent backTileTexture = new TextureComponent();
        backTileTexture.region = Textures.backTile;

        float tilePlace = MainGame.VIRTUAL_WIDTH / size;


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Entity tile = new Entity();
                Entity backTile = new Entity();

                PositionComponent tilePosition = new PositionComponent(Tiles.getPosition(i, j));
                float scale = tilePlace * 0.8f / tileTexture.region.getRegionWidth();
                tilePosition.scale.set(scale, scale);
                TileComponent tileIndex = new TileComponent(i, j);

                tile.add(tileTexture);
                tile.add(tilePosition);
                tile.add(tileIndex);

                PositionComponent backTilePosition = new PositionComponent(tilePosition.center);
                scale = tilePlace * 0.9f / tileTexture.region.getRegionWidth();
                backTilePosition.scale.set(scale, scale);

                backTile.add(backTilePosition);
                backTile.add(backTileTexture);

                engine.addEntity(tile);
                engine.addEntity(backTile);
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
