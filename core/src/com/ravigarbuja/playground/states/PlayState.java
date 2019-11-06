package com.ravigarbuja.playground.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ravigarbuja.playground.FlappyDemo;
import com.ravigarbuja.playground.sprites.Bird;

public class PlayState extends State {

    private Bird bird;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 100);
        //view port width and height adjustment using the cam
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float deltaTime) {
        handleInput();
        bird.update(deltaTime);

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);

        spriteBatch.begin();
        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}