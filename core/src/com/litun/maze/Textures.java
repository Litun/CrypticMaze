package com.litun.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by user on 22.03.2015.
 */
public class Textures {
    static final private Texture items = new Texture(Gdx.files.internal("maze/items.png"));
    static final public TextureRegion tile =new TextureRegion(items, 0, 0, 100, 100);
    static final public TextureRegion character= new TextureRegion(items,100,0,100,100);
}
