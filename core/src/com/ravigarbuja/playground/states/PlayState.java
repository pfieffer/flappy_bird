package com.ravigarbuja.playground.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ravigarbuja.playground.FlappyDemo;

import javax.xml.soap.Text;

public class PlayState extends State {

    private Texture bird;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Texture("bird.png");
        //view port width and height adjustment using the cam
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);

        spriteBatch.begin();
        spriteBatch.draw(bird, 50, 50);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
