package com.litun.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.litun.maze.MainGame;
import com.litun.maze.Textures;

/**
 * Created by Litun on 19.05.2015.
 */
public class FinishScreen extends ScreenAdapter {
    private MainGame game;
    private Stage stage;
    private Skin skin;

    public FinishScreen(final MainGame mainGame, boolean win) {
        stage = new Stage(mainGame.viewport, mainGame.batch);
        game = mainGame;

        game.placeCamera();
        Gdx.input.setInputProcessor(stage);
        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
//        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
//        pixmap.setColor(Color.WHITE);
//        pixmap.fill();
//        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        //skin.add("default", new BitmapFont());

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
//        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
//        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
//        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
//        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
//        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
//        textButtonStyle.font = skin.getFont("default");
//        skin.add("default", textButtonStyle);

        //BitmapFont bitmapFont = new BitmapFont(new FileHandle("maze/deng.fnt"));

        Label.LabelStyle labelStyle = new Label.LabelStyle(Textures.font, Color.WHITE);

        skin.add("default", labelStyle);

        // Create a table that fills the screen. Everything else will go inside this table.
        //VerticalGroup table = new VerticalGroup();
        //table.setFillParent(true);
        //table.align(Align.center);
        //stage.addActor(table);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        //final TextButton button = new TextButton("Click me!", skin);
        //table.add(button);

        // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
        // revert the checked state.
//        button.addListener(new ChangeListener() {
//            public void changed(ChangeEvent event, Actor actor) {
//                System.out.println("Clicked! Is checked: " + button.isChecked());
//                button.setText("Good job!");
//            }
//        });

        //skin = new Skin(Gdx.files.internal("maze/skin.json"));
        //Label label = new Label("I am label", skin);

        Label label = new Label(win ? "YOU WIN!" : "You lose", skin);
        label.setX(200);
        label.setY(550);

        Image image = new Image(Textures.tile);
        image.setX(250);
        image.setY(400);
        image.setHeight(100);
        image.setWidth(100);


        Image image2 = new Image(Textures.character);
        image2.setX(250);
        image2.setY(500);
        image2.setHeight(100);
        image2.setWidth(100);
        image2.rotateBy(270);
        image2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        // Add an image actor. Have to set the size, else it would be the size of the drawable (which is the 1x1 texture).
        stage.addActor(label);
        stage.addActor(image);
        stage.addActor(image2);
    }

    @Override
    public void render(float delta) {
//        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
