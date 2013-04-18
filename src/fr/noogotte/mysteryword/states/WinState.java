package fr.noogotte.mysteryword.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.noogotte.mysteryword.gui.Button;
import fr.noogotte.mysteryword.resources.Images;

public class WinState extends BasicGameState{

    public static final int ID = 3;
    private Image background;
    private Button goToMenu;
    private SinglePlayerState state;
    private Music victory;

    @Override
    public void init(final GameContainer gc, final StateBasedGame sbg)
            throws SlickException {
        state = (SinglePlayerState) sbg.getState(SinglePlayerState.ID);
        background = Images.getInstance().getImage(Images.background);
        goToMenu = new Button(gc, gc.getWidth() / 2 - (594 / 2), 500, new ComponentListener() {
            @Override
            public void componentActivated(AbstractComponent arg0) {
                sbg.enterState(MenuState.ID);
            }
        });
        victory = new Music("lib/res/musics/victory.ogg");
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        victory.loop(1f, 0.2f);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) {
        victory.fade(500, 0.0f, true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        background.draw();
        goToMenu.render(gc, g);
        goToMenu.drawName(g, "Retour au menu");
        if (state.getTrialNumber() == 1) {
            g.drawString("Bravo, vous avez trouvé le mot mystère en " + state.getTrialNumber() + " coup. Bravo !", gc.getWidth() / 2 - 57 * 4, gc.getWidth() / 2);
        } else {
            g.drawString("Bravo, vous avez trouvé le mot mystère en " + state.getTrialNumber() + " coups. Bravo !", gc.getWidth() / 2 - 57 * 4, gc.getWidth() / 2);
        }
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
