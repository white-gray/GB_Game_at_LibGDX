package com.star.app.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.star.app.game.helpers.ObjectPool;

public class GoldController extends ObjectPool<Gold> {
    private GameController gc;

    @Override
    protected Gold newObject() {
        return new Gold(gc);
    }

    public GoldController(GameController gc) {
        this.gc = gc;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Gold aidKit = activeList.get(i);
            aidKit.render(batch);
        }
    }

    public void setup(float x, float y, float vx, float vy, float scale){
        getActiveElement().activate(x, y, vx, vy, scale);
    }

    public void update(float dt){
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}
