package com.litun.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainGame extends Game {
    public SpriteBatch batch;

    // aspect ratio 16:9 = 1.(7)
    // iphone 6 1.778(6)
    // iphone 4 1.5

    public static final int VIRTUAL_WIDTH = 600;
    public static final int VIRTUAL_HEIGHT = 900;
    //public static final float FILL_FACTOR = 0.8f;
    Viewport viewport;
    OrthographicCamera cam;

    ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        cam = new OrthographicCamera();
        viewport = new ExtendViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, cam);
        cam.position.add(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2, 0);
        setScreen(new GameScreen(this));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        batch.setProjectionMatrix(cam.combined);
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0, 150, 600, 600);
        shapeRenderer.end();

        super.render();
    }
}
