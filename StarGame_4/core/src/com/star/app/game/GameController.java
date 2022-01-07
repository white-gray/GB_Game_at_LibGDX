package com.star.app.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.screen.ScreenManager;

public class GameController {
    private Background background;
    private AsteroidController asteroidController;
    private BulletController bulletController;
    private AidKitController aidKitController;
    private GoldController goldController;
    private AmmunitionController ammunitionController;
    private Weapon currentWeapon;
    private ParticleController particleController;
    private Hero hero;
    private Vector2 tempVec;

    public GameController() {
        this.background = new Background(this);
        this.hero = new Hero(this);
        this.asteroidController = new AsteroidController(this);
        this.aidKitController = new AidKitController(this);
        this.ammunitionController = new AmmunitionController(this);
        this.goldController = new GoldController(this);
        this.bulletController = new BulletController(this);
        this.currentWeapon = new Weapon(this);
        this.particleController = new ParticleController();
        this.tempVec = new Vector2();

        for (int i = 0; i < 3; i++) {
            asteroidController.setup(MathUtils.random(0, ScreenManager.SCREEN_WIDTH),
                    MathUtils.random(0, ScreenManager.SCREEN_HEIGHT),
                    MathUtils.random(-200, 200),
                    MathUtils.random(-200, 200), 1.0f);
        }
    }

    public ParticleController getParticleController() {
        return particleController;
    }

    public AsteroidController getAsteroidController() {
        return asteroidController;
    }

    public AidKitController getAidKitController() {
        return aidKitController;
    }
    public AmmunitionController getAmmunitionController() {
        return ammunitionController;
    }
    public GoldController getGoldController() {
        return goldController;
    }

    public Hero getHero() {
        return hero;
    }

    public Background getBackground() {
        return background;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public void update(float dt) {
        background.update(dt);
        hero.update(dt);
        asteroidController.update(dt);
        aidKitController.update(dt);
        ammunitionController.update(dt);
        goldController.update(dt);
        bulletController.update(dt);
        particleController.update(dt);
        checkCollisions();
    }

    private void checkCollisions() {
        for (int i = 0; i < asteroidController.getActiveList().size(); i++) {
            Asteroid a = asteroidController.getActiveList().get(i);
            if (a.getHitArea().overlaps(hero.getHitArea())) {
                float dst = a.getPosition().dst(hero.getPosition());
                float halfOverLen = (a.getHitArea().radius + hero.getHitArea().radius - dst) / 2;
                tempVec.set(hero.getPosition()).sub(a.getPosition()).nor();
                hero.getPosition().mulAdd(tempVec, halfOverLen);
                a.getPosition().mulAdd(tempVec, -halfOverLen);

                float sumScl = hero.getHitArea().radius + a.getHitArea().radius;
                hero.getVelocity().mulAdd(tempVec, a.getHitArea().radius / sumScl * 100);
                a.getVelocity().mulAdd(tempVec, -hero.getHitArea().radius / sumScl * 100);

                if (a.takeDamage(2)) {
                    hero.addScore(a.getHpMax() * 50);
                }
                hero.takeDamage(2);
            }
        }


        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
            for (int j = 0; j < asteroidController.getActiveList().size(); j++) {
                Asteroid a = asteroidController.getActiveList().get(j);
                if (a.getHitArea().contains(b.getPosition())) {

                    particleController.setup(b.getPosition().x + MathUtils.random(-4, 4), b.getPosition().y + MathUtils.random(-4, 4),
                            b.getVelocity().x * -0.3f + MathUtils.random(-30, 30), b.getVelocity().y * -0.3f + MathUtils.random(-30, 30),
                            0.2f, 2.2f, 1.5f,
                            1.0f, 1.0f, 1.0f, 1,
                            0, 0, 1, 0);


                    b.deactivate();
                    a.takeDamage(20);
                    break;
                }

                for (int w = 0; w < aidKitController.getActiveList().size(); w++) {
                    AidKit aidkit = aidKitController.getActiveList().get(w);
                    if (aidkit.getHitArea().contains(b.getPosition())) {
                        particleController.setup(b.getPosition().x + MathUtils.random(-4, 4), b.getPosition().y + MathUtils.random(-4, 4),
                                b.getVelocity().x * -0.3f + MathUtils.random(-30, 30), b.getVelocity().y * -0.3f + MathUtils.random(-30, 30),
                                0.2f, 2.2f, 1.5f,
                                1.0f, 1.0f, 1.0f, 1,
                                0, 0, 1, 0);

                        aidkit.deactivate();
                        b.deactivate();

                        break;
                    }
                }
                for (int w = 0; w < ammunitionController.getActiveList().size(); w++) {
                    Ammunition ammunition = ammunitionController.getActiveList().get(w);
                    if (ammunition.getHitArea().contains(b.getPosition())) {
                        particleController.setup(b.getPosition().x + MathUtils.random(-4, 4), b.getPosition().y + MathUtils.random(-4, 4),
                                b.getVelocity().x * -0.3f + MathUtils.random(-30, 30), b.getVelocity().y * -0.3f + MathUtils.random(-30, 30),
                                0.2f, 2.2f, 1.5f,
                                1.0f, 1.0f, 1.0f, 1,
                                0, 0, 1, 0);

                        ammunition.deactivate();
                        b.deactivate();

                        break;
                    }
                }
                for (int w = 0; w < goldController.getActiveList().size(); w++) {
                    Gold gold = goldController.getActiveList().get(w);
                    if (gold.getHitArea().contains(b.getPosition())) {
                        particleController.setup(b.getPosition().x + MathUtils.random(-4, 4), b.getPosition().y + MathUtils.random(-4, 4),
                                b.getVelocity().x * -0.3f + MathUtils.random(-30, 30), b.getVelocity().y * -0.3f + MathUtils.random(-30, 30),
                                0.2f, 2.2f, 1.5f,
                                1.0f, 1.0f, 1.0f, 1,
                                0, 0, 1, 0);

                        gold.deactivate();
                        b.deactivate();

                        break;
                    }
                }
            }
        }
        for (int i = 0; i < aidKitController.getActiveList().size(); i++) {
            AidKit aidkit = aidKitController.getActiveList().get(i);
            if (aidkit.getHitArea().contains(hero.getPosition())) {
            hero.takeAidkit(20);
            aidkit.deactivate();
        }
        }
        for (int i = 0; i < goldController.getActiveList().size(); i++) {
            Gold gold = goldController.getActiveList().get(i);
            if (gold.getHitArea().contains(hero.getPosition())) {
            hero.setScoreView(20);
            gold.deactivate();
        }
        }
        for (int i = 0; i < ammunitionController.getActiveList().size(); i++) {
            Ammunition ammunition = ammunitionController.getActiveList().get(i);
            if (ammunition.getHitArea().contains(hero.getPosition())) {
                currentWeapon.setCurBullets(20);
            ammunition.deactivate();
        }
        }
    }


}

