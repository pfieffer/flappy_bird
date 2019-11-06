package com.ravigarbuja.playground.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    /*
    Pop and push at the same time
     */
    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float deltaTime){
        states.peek().update(deltaTime);
    }

    public void render(SpriteBatch spriteBatch){
        states.peek().render(spriteBatch);
    }


}
