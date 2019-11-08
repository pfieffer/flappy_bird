package com.ravigarbuja.playground.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;

    private Rectangle bounds;

    private Animation birdAnimation;
    Texture birdTexture;
    private Sound flapSound;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        birdTexture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
        bounds = new Rectangle(x, y, birdTexture.getWidth() / 3, birdTexture.getHeight());
        flapSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float deltaTime){
        birdAnimation.update(deltaTime);
        if (position.y > 0){
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(deltaTime);
        position.add(MOVEMENT * deltaTime, velocity.y, 0);

        if (position.y < 0){
            position.y = 0;
        }

        velocity.scl(1/deltaTime);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBirdTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        //as long as jump() method is called, the velocity in y axis stays positive, fighting the gravity
        velocity.y = 250;
        flapSound.play(0.5f);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose() {
       birdTexture.dispose();
       flapSound.dispose();
    }
}
