package com.ravigarbuja.playground.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ravigarbuja.playground.FlappyDemo;
import com.ravigarbuja.playground.sprites.Bird;
import com.ravigarbuja.playground.sprites.Tube;

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;

    private Array<Tube> tubes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(40, 200);
        //view port width and height adjustment using the cam
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);

        background = new Texture("bg.png");

        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<>();

        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    protected void update(float deltaTime) {
        handleInput();
        updateGround();
        bird.update(deltaTime);

        cam.position.x = bird.getPosition().x + 80; //80 offset

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);

            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            /*
            fixme: This is not how collision logic is handled on production level games where we may
              have more than 4 tubes (obstacles)
             */
            if (tube.collides(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
            }

        }

        //kill the bird
        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new PlayState(gsm));

        cam.update();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);

        spriteBatch.begin();
        spriteBatch.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for (Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }

        spriteBatch.draw(ground, groundPosition1.x, groundPosition1.y);
        spriteBatch.draw(ground, groundPosition2.x, groundPosition2.y);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        //dispose
        background.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("Play State Disposed");
    }

    private void updateGround() {
        if ((cam.position.x - cam.viewportWidth / 2) > groundPosition1.x + ground.getWidth())
            groundPosition1.add(ground.getWidth() * 2, 0);

        if ((cam.position.x - cam.viewportWidth / 2) > groundPosition2.x + ground.getWidth())
            groundPosition2.add(ground.getWidth() * 2, 0);
    }
}
