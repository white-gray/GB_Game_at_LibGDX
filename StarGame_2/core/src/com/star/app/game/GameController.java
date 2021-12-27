package com.star.app.game;

public class GameController {
    private Background background;
    private BulletController bulletController;
    private AsteroidController asteroidController;
    private Hero hero;
    private Asteroid asteroid;

    public Hero getHero() {
        return hero;
    }

    public Asteroid getAsteroid() {
        return asteroid;
    }

    public Background getBackground() {
        return background;
    }

    public BulletController getBulletController() {
        return bulletController;
    }


    public AsteroidController getAsteroidController() {
        return asteroidController;
    }

    public GameController() {
        this.background = new Background(this);
        this.hero = new Hero(this);
        this.bulletController = new BulletController();
        this.asteroidController = new AsteroidController();
        this.asteroid = new Asteroid();
        hero.addAsteroid(0.018f);
    }

    public void update(float dt) {
        background.update(dt);
        hero.update(dt);
        bulletController.update(dt);
        asteroidController.update(dt);
        checkCollisions();
    }

    private void checkCollisions() {
        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
            for (int q = 0; q < asteroidController.getActiveList().size(); q++) {
                Asteroid a = asteroidController.getActiveList().get(q);
System.out.println("getPositions() = " + a.getPosition()+"\t" + b.getPosition()+"\t" + hero.getPosition());
            if(a.getPosition().dst(b.getPosition()) < 128.0f){
                System.out.println("Астероид погиб!");
                b.deactivate();
                a.deactivate();
            }
            }
        }
            for (int q = 0; q < asteroidController.getActiveList().size(); q++) {
                Asteroid a = asteroidController.getActiveList().get(q);
                if(a.getPosition().dst(hero.getPosition()) < (128+20)){
                    a.deactivate();
            }
        }
    }
}
