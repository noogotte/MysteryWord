package fr.noogotte.mysteryword.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WinState extends BasicGameState {

    public static final int ID = 3;

    @Override
    public void init(final GameContainer gc, final StateBasedGame sbg)
            throws SlickException {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
    }

    @Override
    public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
            throws SlickException { 
    }

    @Override
    public int getID() {
        return ID;
    }
}
