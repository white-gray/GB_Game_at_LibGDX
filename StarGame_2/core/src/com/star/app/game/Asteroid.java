package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;

public class Asteroid implements Poolable {
    private GameController gc;
    private Vector2 position;
    private Vector2 velocity;
    private boolean active;
    private float angle;


    @Override
    public boolean isActive() {
        return active;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Asteroid() {
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.active = false;
    }


    public void deactivate() {        active = false;    }

    public void update(float dt) {
        position.x += MathUtils.cosDeg(angle) * 30.0f * dt;
        position.y += MathUtils.sinDeg(angle) * 30.0f * dt;
        if (position.x < -128 || position.x > ScreenManager.SCREEN_WIDTH + 128
                || position.y < -128 || position.y > ScreenManager.SCREEN_HEIGHT + 128) {
            deactivate();
//            new Asteroid();
//            position.x = startPosition()[0];
//            position.y = startPosition()[1];
//            angle = angleStart();
//System.out.println("update startPosition()[0] = " + position.x);
//System.out.println("update startPosition()[1] = " + position.y);
//            gc.getAsteroidController().setup(position.x, position.y,
//                    MathUtils.cosDeg(angle) * 30.0f* dt,
//                    MathUtils.sinDeg(angle) * 30.0f* dt);

        }
    }



    public void activate(float x, float y, float vx, float vy) {
System.out.println("activate x = " + x);
System.out.println("activate y = " + y);
System.out.println("activate vx = " + vx);
System.out.println("activate vy = " + vy);
        position.set(x,  y);
        velocity.set(vx, vy);
        active = true;
    }

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

}
