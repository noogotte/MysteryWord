package fr.noogotte.mysteryword.states;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.noogotte.mysteryword.gui.Button;
import fr.noogotte.mysteryword.resources.Images;

public class LooseState extends BasicGameState{

    public static final int ID = 4;
    private Image background;
    private Button goToMenu;
    private SinglePlayerState state;
    private Music loose;
    private TrueTypeFont slickFont;

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
        loose = new Music("lib/res/musics/loose.ogg");
        Font font = new Font("Arial Black", Font.BOLD, 20);
        slickFont = new TrueTypeFont(font, true);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        loose.loop(1f, 0.2f);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) {
        loose.fade(500, 0.0f, true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        background.draw();
        goToMenu.render(gc, g);
        goToMenu.drawName(g, "Retour au menu");
        String sentence = "Mince, vous n'avez pas trouvé le mot mystère qui était : ";
        g.setFont(slickFont);
        g.setColor(Color.green);
        g.drawString(sentence, gc.getWidth() / 2 - sentence.length() * 6, gc.getHeight() / 2);
        sentence = state.getWordToFindStr();
        g.drawString(sentence, gc.getWidth() / 2 - sentence.length() * 6, gc.getHeight() / 2 + 20);
        g.setFont(gc.getDefaultFont());
        g.setColor(Color.black);
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
