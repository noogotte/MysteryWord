package fr.noogotte.mysteryword.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.noogotte.mysteryword.gui.Button;

public class MenuState extends BasicGameState {

    public static final int ID = 0;
    private Button singlePlayerButton;
    private Button multiPlayerButton;

    @Override
    public void init(final GameContainer gc, final StateBasedGame sbg)
            throws SlickException {
        singlePlayerButton = new Button(gc, 100, 100, new ComponentListener() {
            @Override
            public void componentActivated(AbstractComponent ac) {
            }
        });
        multiPlayerButton = new Button(gc, 100, 200, new ComponentListener() {
            @Override
            public void componentActivated(AbstractComponent ac) {
            }
        });
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        singlePlayerButton.render(gc, g);
        multiPlayerButton.render(gc, g);
        singlePlayerButton.drawName(g, "SinglePlayer");
        multiPlayerButton.drawName(g, "MultiPlayer");
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
