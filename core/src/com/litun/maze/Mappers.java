package com.litun.maze;

import com.badlogic.ashley.core.ComponentMapper;
import com.litun.maze.components.PositionComponent;
import com.litun.maze.components.TextureComponent;
import com.litun.maze.components.TileIndexComponent;

/**
 * Created by user on 13.03.2015.
 */
public class Mappers {
    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<TileIndexComponent> tiles = ComponentMapper.getFor(TileIndexComponent.class);

}