package com.star.app.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.star.app.game.helpers.ObjectPool;

public class AsteroidController extends ObjectPool<Asteroid> {
    private Texture asteroidTexture;
    private GameController gc;
    private float fireTimer;

    @Override
    protected Asteroid newObject() {
        return new Asteroid();
    }

    public AsteroidController() {
        this.asteroidTexture = new Texture("asteroid.png");
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Asteroid a = activeList.get(i);
            batch.draw(asteroidTexture, a.getPosition().x - 128, a.getPosition().y - 128);
        }
    }


    public void setup(float x, float y, float vx, float vy){
System.out.println("setup_x = " + x);
System.out.println("setup y = " + y);
System.out.println("setup vx = " + vx);
System.out.println("setup vy = " + vy);
        getActiveElement().activate(x, y, vx, vy);
    }

    public void update(float dt){
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}
