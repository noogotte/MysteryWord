package fr.noogotte.mysteryword;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainClass {

    public static void main(String[] args) throws SlickException {
        AppGameContainer mystery =
                new AppGameContainer(new MysteryWord(), 800, 600, false);
        mystery.setShowFPS(false);
        mystery.start();
    }
}
