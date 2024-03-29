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
    private String wordToFindDesc;
    private TextField wordToFind;
    private int life = 5;
    private Button validate;

    @Override
    public void init(final GameContainer gc, final StateBasedGame sbg)
            throws SlickException {
        wordToFindStr = "";
        wordToFindDesc = "";
        background = Images.getInstance().getImage(Images.background);
        wordToFind = new TextField(gc, gc.getDefaultFont(), gc.getWidth() / 2 - 75, gc.getHeight() / 2 - 10, 150, 20);
        wordToFind.setBorderColor(Color.black);
        wordToFind.setTextColor(Color.black);
        validate = new Button(gc, gc.getWidth() / 2 - (594 / 2), 500, new ComponentListener() {
            @Override
            public void componentActivated(AbstractComponent ac) {
                String trialWord = wordToFind.getText();
                if (trialWord.equals(wordToFindStr)) {
                    sbg.enterState(WinState.ID);
                } else {
                    life--;
                    wordToFind.setText("");
                }
            }
        });
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        updateWordToFind();
        life = getMaxLife();
        wordToFind.setText("");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        background.draw();
        wordToFind.render(gc, g);
        validate.render(gc, g);
        validate.drawName(g, "Valider");
        g.setColor(Color.black);
        g.drawString("Le mot myst�re � trouver est : " +
                wordMix, gc.getWidth() / 2 - (31  + wordMix.length()) * 4, gc.getHeight() / 2 - 50);
        g.drawString("Votre proposition :", gc.getWidth() / 2 - 275, gc.getHeight() / 2 - 10);
        g.drawString("La description du mot est : " +
                wordToFindDesc, gc.getWidth() / 2 - (28  + wordToFindDesc.length()) * 4, gc.getHeight() / 2 + 50);
        g.setColor(Color.red);
        g.drawString("Nombre d'essais restants : " + life, gc.getWidth() / 2 + 100, gc.getHeight() / 2 - 10);
        g.setColor(Color.black);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        Input input = gc.getInput();
        if(input.isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(MenuState.ID);
        }

        if (life == 0) {
            sbg.enterState(LooseState.ID);
        }
        if(input.isKeyPressed(Input.KEY_TAB)) {
            updateWordToFind();
            life--;
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
            Log.error("File not found " + file.getAbsolutePath());
        }

        int line = new Random().nextInt(5) + 1;

        try {
            while (reader.readLine() != null) {
                if (reader.getLineNumber() == line) {
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
        File file = new File("lib/res/mots.txt");
        System.out.println(file.getAbsolutePath());
        String wordInFile = getWordInFile(file);
        String[] words = wordInFile.split(":");
        wordToFindStr = words[0];
        wordToFindDesc = words[1];
        System.out.println(wordToFindStr + " Desc : " + wordToFindDesc);
        wordMix = mixLetter(wordToFindStr);
    }

    public int getLife() {
        return life;
    }

    public int getMaxLife() {
        return 5;
    }

    public String getWordToFindStr() {
        return wordToFindStr;
    }
}
