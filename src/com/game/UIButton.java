package com.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIButton extends  UI{
     private BufferedImage[] images;
     private ClickListener clicker;

    public UIButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images=images;
        this.clicker=clicker;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(hovering){
            g.drawImage(images[1],(int)x,(int)y,null);
        }else
            g.drawImage(images[0],(int)x,(int)y,null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
