package com.ravigarbuja.playground.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ravigarbuja.playground.FlappyDemo;

public class MenuState extends State{

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    protected void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        spriteBatch.draw(playButton, (FlappyDemo.WIDTH / 2) - (playButton.getWidth() / 2), (FlappyDemo.HEIGHT / 2));
        spriteBatch.end();
    }
}
