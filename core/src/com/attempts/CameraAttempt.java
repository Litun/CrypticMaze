package com.attempts;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by user on 15.04.2015.
 */
public class CameraAttempt extends Game {
    public SpriteBatch batch;
    //540x960

    // aspect ratio 16:9 = 1.(7)
    // iphone 6 1.778(6)
    // iphone 4 1.5

    public static final int VIRTUAL_WIDTH = 600;
    public static final int VIRTUAL_HEIGHT = 900;
    Viewport viewport;
    ShapeRenderer shapeRenderer;
    OrthographicCamera cam;




    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        cam = new OrthographicCamera();
        //viewport = new ExtendViewport(200,300, 400,600,cam);
        viewport = new ExtendViewport(VIRTUAL_WIDTH,VIRTUAL_HEIGHT,cam);
        //setScreen(new GameScreen(this));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        batch.setProjectionMatrix(cam.combined);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 1f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        shapeRenderer.setProjectionMatrix(cam.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(-5000, -450, 10000, 950);
        shapeRenderer.rect(-300, -5000, 600, 10000);

        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(-300, -450, 600, 900);

        shapeRenderer.setColor(1, 1, 0, 1);
        shapeRenderer.rect(100, 100, 100, 100);

        //shapeRenderer.circle(200,200,100);


        shapeRenderer.end();

//        shapeRenderer.begin(ShapeType.Filled);
//        shapeRenderer.setColor(0, 1, 0, 1);
//        shapeRenderer.rect(x, y, width, height);
//        shapeRenderer.circle(x, y, radius);
//        shapeRenderer.end();


        super.render();
    }
}

