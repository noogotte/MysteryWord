package fr.noogotte.mysteryword;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.noogotte.mysteryword.states.MenuState;
import fr.noogotte.mysteryword.states.MultiPlayerState;
import fr.noogotte.mysteryword.states.SinglePlayerState;
import fr.noogotte.mysteryword.states.WinState;

public class MysteryWord extends StateBasedGame {

    public MysteryWord() {
        super("MysteryWord");
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new MenuState());
        addState(new SinglePlayerState());
        addState(new MultiPlayerState());
        addState(new WinState());
    }
}
