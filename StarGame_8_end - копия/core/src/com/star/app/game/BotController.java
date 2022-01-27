package com.star.app.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.star.app.game.helpers.ObjectPool;

public class BotController extends ObjectPool<Bot> {
    private GameController gc;
    private int numberOfBoat;

    @Override
    protected Bot newObject() {
        return new Bot(gc);
    }

    public BotController(GameController gc) {
        this.gc = gc;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Bot b = activeList.get(i);
            b.render(batch);
        }
    }

    public int getNumberOfBot() {
        return activeList.size();
    }

    public void setup(float x, float y) {
        getActiveElement().activate(x, y);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}
