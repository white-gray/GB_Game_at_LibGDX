# not working

Школьный вопрос: 

		есть
			public class Hero {
				public int hp;
				public Hero() {
					this.hp = 1;
				}
				public int getHp() {
					return hp;
				}
			}

		и есть
			import com.star.app.game.Hero;
			public class GameOverScreen extends AbstractScreen {
				private Hero hero;

				public GameOverScreen(SpriteBatch batch) {
					super(batch);
				}
			.................... 
				@Override
				public void render(float delta) {
			System.out.println("hero.getHp() = "+  hero.getHp());  		почему в этом месте показывается hero [NullPointException].getHp());   Hero: null
											И как получить тут значение hp из класса Hero
			 .................  
				}
			}

