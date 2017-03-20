package yoshimaker;

import org.newdawn.slick.*;

public class WindowGame extends BasicGame {
    private GameContainer container;
    private Entity test;
    public WindowGame() {
        super("Lesson 1 :: WindowGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        test = new Entity();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException { 
        g.drawAnimation(test.getSprite(), test.getX(), test.getY());
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
}
