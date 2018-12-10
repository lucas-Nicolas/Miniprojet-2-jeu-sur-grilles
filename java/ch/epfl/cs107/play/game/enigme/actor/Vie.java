package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

public class Vie extends Entity {
    private int vie;
    private Actor parent;
    private TextGraphics vieDisplayer;

    public Vie(int vie, Actor actor) {
        super(actor.getPosition());
        this.vie=vie;
        this.parent = actor;
        this.vieDisplayer = new TextGraphics("HP "+ vie, 0.275f, Color.BLACK);
        vieDisplayer.setParent(actor);
        vieDisplayer.setAnchor(new Vector(0,0));
    }


    public void setDeltaVie(int vie) {
        this.vie -= vie;
        vieDisplayer = new TextGraphics("Vie : "+this.vie, 0.4f, Color.BLACK);
    }


    @Override
    public void draw(Canvas canvas) {
        vieDisplayer.draw(canvas);
    }
}
