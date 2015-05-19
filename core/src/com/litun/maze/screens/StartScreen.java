package com.litun.maze.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.litun.maze.MainGame;
import com.litun.maze.Textures;

/**
 * Created by Litun on 18.05.2015.
 */
public class StartScreen extends ScreenAdapter {
    MainGame game;
    Stage stage;

    public StartScreen(final MainGame mainGame) {
        stage = new Stage(mainGame.viewport, mainGame.batch);
        game = mainGame;
        Image image = new Image(Textures.tile);
        image.setX(200);
        image.setY(350);
        image.setHeight(200);
        image.setWidth(200);


        Image image2 = new Image(Textures.character);
//        float l = (float) Math.sqrt(2) * 100;
//        image2.setX(200f + 100f - (float) (l * Math.sin(Math.PI / 12)));
//        image2.setY(350f + 100f - (float) (l * Math.cos(Math.PI / 12)));
        image2.setX(200);
        image2.setY(550);
        image2.setHeight(200);
        image2.setWidth(200);
        image2.rotateBy(270);
        image2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        Gdx.input.setInputProcessor(stage);
        game.placeCamera();
        stage.addActor(image);
        stage.addActor(image2);
    }

    @Override
    public void render(float delta) {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        //stage.getViewport().update(width, height, true);
    }
}
