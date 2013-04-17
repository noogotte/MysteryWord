package fr.noogotte.mysteryword.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

import fr.noogotte.mysteryword.resources.Images;

public class Button extends MouseOverArea {

    public Button(GUIContext gc, int x, int y,
            ComponentListener listener) {
        super(gc, Images.getInstance().getImage("button"), x, y, listener);
        this.setMouseOverImage(
                Images.getInstance().getImage(Images.button_pressed));
    }

    public void drawName(Graphics g, String name) {
        g.drawString(name, getX() + 250, getY() + 10);
    }
}
