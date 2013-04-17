package fr.noogotte.mysteryword.states;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import fr.noogotte.mysteryword.gui.Button;
import fr.noogotte.mysteryword.resources.Images;

public class SinglePlayerState extends BasicGameState {

    public static final int ID = 1;
    private Image background;
    private String wordToFindStr;
    private String wordMix;
    private TextField wordToFind;
    private int trialNumber = 0;
    private Button validate;

    @Override
    public void init(final GameContainer gc, final StateBasedGame sbg)
            throws SlickException {
        updateWordToFind();
        background = Images.getInstance().getImage(Images.background);
        wordToFind = new TextField(gc, gc.getDefaultFont(), gc.getWidth() / 2 - 75, gc.getHeight() / 2 - 10, 150, 20);
        wordToFind.setBorderColor(Color.black);
        wordToFind.setTextColor(Color.black);
        validate = new Button(gc, gc.getWidth() / 2 - (594 / 2), 500, new ComponentListener() {
            @Override
            public void componentActivated(AbstractComponent ac) {
                String trialWord = wordToFind.getText();
                if (!trialWord.equals(wordToFindStr)) {
                    trialNumber++;
                } else {
                    Log.info("Win");
                }
            }
        });
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        background.draw();
        wordToFind.render(gc, g);
        validate.render(gc, g);
        validate.drawName(g, "Valider");
        g.setColor(Color.black);
        g.drawString("Le mot mystère à trouver est : " +
                wordMix, gc.getWidth() / 2 - (31  + wordToFindStr.length()) * 4, gc.getHeight() / 2 - 50);
        g.drawString("Votre proposition :", gc.getWidth() / 2 - 275, gc.getHeight() / 2 - 10);
        g.setColor(Color.red);
        g.drawString("Nombre d'essais : " + trialNumber, gc.getWidth() / 2 + 100, gc.getHeight() / 2 - 10);
        g.setColor(Color.black);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        Input input = gc.getInput();
        if(input.isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(MenuState.ID);
        }
        if(input.isKeyPressed(Input.KEY_R)) {
            updateWordToFind();
            trialNumber++;
        }
    }

    @Override
    public int getID() {
        return ID;
    }

    private String mixLetter(String word) {
        List<Character> letters = new ArrayList<Character>();
        for (int i = 0; i < word.length(); i++) {
            letters.add(word.charAt(i));
        }
        Collections.shuffle(letters);
        StringBuilder mix = new StringBuilder();
        for (char a : letters) {
            mix.append(a);
        }
        letters.clear();
        return mix.toString();
    }

    private String getWordInFile(File file) {
        LineNumberReader reader = null;
        try {
            reader = new LineNumberReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int line = new Random().nextInt(22740) + 1;

        try {
            while (reader.readLine() != null){
                if (reader.getLineNumber() == line && reader.readLine().length() > 3) {
                    return reader.readLine();
                }
            }
            reader.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "default";
    }

    public void updateWordToFind() {
        wordToFindStr = getWordInFile(new File("lib/res/mots.txt"));
        System.out.println(wordToFindStr);
        wordMix = mixLetter(wordToFindStr);
    }
}
