package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Background;
import com.mygdx.game.CNSTNT;
import com.mygdx.game.sprites.Laser;
import com.mygdx.game.sprites.Player;


public class PlayState extends State {

    private static final int LASER_SPACING = 150;
    private static final int LASER_COUNT = 6;

    private Background background;
    private Player player;
    private Array<Laser> lasers;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        player = new Player(50,100);
        cam.setToOrtho(false, 1280, 720);
        cam.translate(-320, -180);
        background = new Background();
        lasers = new Array<Laser>();

        for (int i = 1; i <= LASER_COUNT; i++) {
            lasers.add(new Laser(i* LASER_SPACING + 128));
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            player.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        player.update(dt);

        cam.position.x = player.getPosition().x + 160;
        background.update(dt, cam.position.x - cam.viewportWidth/2);

        for (int i = 0; i < lasers.size; i++) {
            Laser l = lasers.get(i);

            if (cam.position.x - (cam.viewportWidth/2) > l.getPosTopLaser().x + Laser.LASER_WIDTH) {
                l.reposition(l.getPosTopLaser().x + LASER_SPACING * LASER_COUNT);
            }

            if (l.collides(player.getBounds())) {
                System.out.println("Oops");
                gsm.set(new PlayState(gsm));

            }
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        background.render(sb);
        player.render(sb);
        for (Laser l : lasers) {
            l.render(sb);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
        for (Laser l : lasers) {
            l.dispose();
        }
        System.out.println("Play State Disposed");
    }
}
