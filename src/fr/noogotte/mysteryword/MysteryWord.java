package fr.noogotte.mysteryword;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.noogotte.mysteryword.states.MenuState;

public class MysteryWord extends StateBasedGame {

    public MysteryWord() {
        super("MysteryWord");
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new MenuState());
    }
}
