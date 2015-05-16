package com.litun.maze.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.litun.maze.Mappers;
import com.litun.maze.components.PositionComponent;
import com.litun.maze.components.TextureComponent;

import java.util.Comparator;

/**
 * Created by user on 13.03.2015.
 */
public class RenderingSystem extends EntitySystem implements EntityListener {

    //static final float PIXELS_TO_METRES = 1f / 4f; //= 1.0f / 32.0f;

    //private final OrthographicCamera camera;
    //private Viewport viewport;
    private SpriteBatch batch;
    private Array<Entity> entities;
    private final Family family = Family.all(TextureComponent.class, PositionComponent.class).get();

    public RenderingSystem(SpriteBatch batch) {
        //super();
        this.batch = batch;
        entities = new Array<Entity>(false, 17);
        //camera = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        //camera.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
        //viewport = new FitViewport(FRUSTUM_WIDTH, FRUSTUM_HEIGHT, camera);
    }

    @Override
    public void addedToEngine(Engine engine) {
        ImmutableArray<Entity> firstEntities = engine.getEntitiesFor(family);
        entities.clear();
        entities.addAll(firstEntities.toArray());
        entities.sort(comparator);
    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        for (Entity entity : entities) {
            TextureComponent texture = Mappers.texture.get(entity);
            if (texture.region == null) {
                continue;
            }

            PositionComponent position = Mappers.position.get(entity);

            float width = texture.region.getRegionWidth();
            float height = texture.region.getRegionHeight();
            float originX = width * 0.5f;
            float originY = height * 0.5f;

            batch.draw(texture.region,
                    position.center.x - originX, position.center.y - originY,
                    originX, originY,
                    width, height,
                    position.scale.x, position.scale.y,
                    position.rotation);
                    //MathUtils.radiansToDegrees * position.rotation);

        }
        batch.end();
    }

    @Override
    public void entityAdded(Entity entity) {
        if (family.matches(entity)) {
            entities.add(entity);
            entities.sort(comparator);
        }
    }

    @Override
    public void entityRemoved(Entity entity) {
        entities.removeValue(entity, true);
    }

    private Comparator<Entity> comparator = new Comparator<Entity>() {
        @Override
        public int compare(Entity entity1, Entity entity2) {
            return (int) Math.signum(Mappers.position.get(entity1).center.z -
                    Mappers.position.get(entity2).center.z);
        }
    };
}

