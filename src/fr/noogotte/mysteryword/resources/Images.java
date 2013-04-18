package fr.noogotte.mysteryword.resources;

import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

public class Images {

    public static final String FOLDER = "lib/res/images/";

    public static final String background = "background";
    public static final String button = "button";
    public static final String button_pressed = "button_pressed";
    public static String background_win = "win";

    private HashMap<String, Image> images = new HashMap<String, Image>();

    public static Images instance;

    public static Images getInstance() {
        if (instance == null) {
            instance = new Images();
        }
        return instance;
    }

    public Image getImage(String name) {
        Image image = images.get(name);

        if (image == null) {
            String path = FOLDER + name + ".png";
            try {
               image = new Image(path);
               Log.info(path);
            } catch (SlickException e) {
                Log.error("Error, unable to load images \"" + name + "\" Chemin : " + path);
            } catch (RuntimeException e) {
                Log.error("Error, unable to load images \"" + name + "\" Chemin : " + path);
            }
            images.put(name, image);
        }
        return image;
    }
}
