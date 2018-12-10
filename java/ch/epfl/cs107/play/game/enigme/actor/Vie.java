package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

public class Vie extends Entity {
    private int vie;
    Actor parent;
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
        this.vie += vie;
        if(this.vie>100){this.vie =100;}
        vieDisplayer.setText("HP"+this.vie);

    }


    @Override
    public void draw(Canvas canvas) {
        vieDisplayer.draw(canvas);
    }

    public int getVie() {
        return vie;
    }
}
