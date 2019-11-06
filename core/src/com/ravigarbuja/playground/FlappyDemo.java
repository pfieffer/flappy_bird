package com.ravigarbuja.playground;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ravigarbuja.playground.states.GameStateManager;
import com.ravigarbuja.playground.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy Bird";

	private GameStateManager gsm;
	private SpriteBatch spriteBatch;

	Texture img;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(spriteBatch);
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		img.dispose();
	}
}
