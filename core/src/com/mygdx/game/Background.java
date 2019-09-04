package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {

    private Texture bg;
    private Vector2 position;
    private Vector2 camOffset;
    private Vector2 movement;
    private boolean left = true;
    private boolean down = true;
    private int updatecalls;

    public Background() {
        bg = new Texture("testbg.png");
        position = new Vector2(0,0);
        camOffset = new Vector2( 0,0);
        movement = new Vector2( -4, -4);
        updatecalls = 0;
    }

    public void update(float dt, float x) {
        updatecalls++;
        camOffset.x = x;

        if (updatecalls %3 == 0) {
            position.x += movement.x;

        }
        if (updatecalls %6 == 0 ) {
            position.y += movement.y;
        }
//        if (updatecalls %(7*7*4) == 7*7*4-1) {
//            movement.x = -movement.x;
//        }
//
//        if(updatecalls %(7*12*4) == 7*12*4-1) {
//            movement.y = -movement.y;
//            updatecalls = 0;
//        }


        if (position.x > bg.getWidth()) position.x -= bg.getWidth();
        else if (position.x < 0) position.x += bg.getWidth();
        if (position.y > bg.getHeight()) position.y -= bg.getHeight();
        else if (position.y < 0) position.y += bg.getHeight();

    }

    public void render(SpriteBatch sb) {
        sb.draw(bg,camOffset.x + position.x, position.y);
        //sb.draw(bg,camOffset.x + position.x - bg.getWidth(), position.y);
        //sb.draw(bg,camOffset.x + position.x, position.y - bg.getHeight());
        //sb.draw(bg,camOffset.x + position.x - bg.getWidth(), position.y - bg.getHeight());
    }

    public void dispose() {
        bg.dispose();
    }
}
