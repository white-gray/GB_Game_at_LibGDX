package com.star.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    private Texture asteroidImg;
    private Vector2 position;
    private float angle;
//    private Vector2 lastDisplacement;

    public Asteroid() {
        this.asteroidImg = new Texture("asteroid.png");
        this.position = new Vector2(startPosition()[0], startPosition()[1]);
        this.angle = angleStart();
//        this.lastDisplacement = new Vector2(0, 0);
    }

//    public Vector2 getLastDisplacement() {
//        return lastDisplacement;
//    }

    private float angleStart() {
        return MathUtils.random(0, 360);
    }

    private int[] startPosition() {
        if (MathUtils.randomBoolean()) {
            if (MathUtils.randomBoolean()) {
                return new int[]{MathUtils.random(0, ScreenManager.SCREEN_WIDTH), -128};
            } else {
                return new int[]{MathUtils.random(0, ScreenManager.SCREEN_WIDTH), ScreenManager.SCREEN_HEIGHT + 128};
            }
        } else {
            if (MathUtils.randomBoolean()) {
                return new int[]{-128, MathUtils.random(0, ScreenManager.SCREEN_HEIGHT)};
            } else {
                return new int[]{ScreenManager.SCREEN_WIDTH + 128, MathUtils.random(0, ScreenManager.SCREEN_HEIGHT)};
            }
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(asteroidImg, position.x - 128, position.y - 128, 128, 128,
                256, 256, 1, 1,
                angle, 0, 0, 256, 256, false, false);
    }

    public void update(float dt) {
        position.x += MathUtils.cosDeg(angle) * 30.0f * dt;
        position.y += MathUtils.sinDeg(angle) * 30.0f * dt;
//        lastDisplacement.set(MathUtils.cosDeg(angle) * 240.0f * dt,
//                MathUtils.sinDeg(angle) * 240.0f * dt);


        if (position.x < -128 || position.x > ScreenManager.SCREEN_WIDTH + 128
                || position.y < -128 || position.y > ScreenManager.SCREEN_HEIGHT + 128) {
            position.x = startPosition()[0];
            position.y = startPosition()[1];
            angle = angleStart();
        }
    }
}
