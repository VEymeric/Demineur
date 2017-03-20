/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *  qu'est ce que ca fait :  Sprit 
 * @author Gaetane et Simon :3
 */
public class Entity { 
    private Animation sprite;
    private int x, y;
    
    void Entity() throws SlickException{
        Image[] imgs = new Image[1];
        imgs[0] = new Image("resource/justDance.png");
        int[] durations = new int[1];
        durations[0] = 100;
        this.sprite = new Animation(imgs, durations) ;
        this.x = 10;
        this.y = 50;
    }

    public Animation getSprite() {
        return sprite;
    }

    public void setSprite(Animation sprite) {
        this.sprite = sprite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
