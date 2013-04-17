package fr.noogotte.mysteryword.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.noogotte.mysteryword.gui.Button;
import fr.noogotte.mysteryword.resources.Images;

public class MenuState extends BasicGameState {

    public static final int ID = 0;
    private Image background;
    private Button singlePlayerButton;
    private Button multiPlayerButton;

    @Override
    public void init(final GameContainer gc, final StateBasedGame sbg)
            throws SlickException {
        background = Images.getInstance().getImage(Images.background);
        singlePlayerButton = new Button(gc, 100, 100, new ComponentListener() {
            @Override
            public void componentActivated(AbstractComponent ac) {
                sbg.enterState(SinglePlayerState.ID);
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
        background.draw();
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
