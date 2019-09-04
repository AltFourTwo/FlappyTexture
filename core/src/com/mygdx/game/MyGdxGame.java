package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class MyGdxGame extends ApplicationAdapter {

	public static final String TITLE = "GAME";

	private GameStateManager gsm;
	private SpriteBatch batch;
	//Texture img;

	private Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();

		gsm = new GameStateManager();

		music  = Gdx.audio.newMusic(Gdx.files.internal("testmusic.mp3"));
		music.setLooping(true);
		music.setVolume(0.25f);
		music.play();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		music.dispose();
	}
}
