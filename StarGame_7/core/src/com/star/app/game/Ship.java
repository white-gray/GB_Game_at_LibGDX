package com.star.app.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.star.app.screen.ScreenManager;

public interface Ship {
//     GameController gc = null;
//     TextureRegion texture = new TextureRegion();
//     Vector2 position = null;
//     Vector2 velocity = new Vector2();
//     float angle = 0;
//     float enginePower = 0;
//     float fireTimer = 0;
//     int hpMax = 0;
//     int hp;
//     Circle hitArea;
//     Weapon currentWeapon;
//     Weapon[] weapons;
//     int weaponNum;

    Weapon getCurrentWeapon();

    public float getAngle();

    public Circle getHitArea();

    public Vector2 getVelocity();

    public Vector2 getPosition();

    public void tryToFire();

    public void update(float dt);

    public void takeDamage(int amount);

//    public void render(SpriteBatch batch);

    public boolean isAlive();
}
