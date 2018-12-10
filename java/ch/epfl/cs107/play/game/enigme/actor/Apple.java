package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Apple extends Collectable {
    private final Sprite APPLE = new Sprite("apple.1",1,1.f,this);

    public Apple(Area area, DiscreteCoordinates position) {
        super(area, position);

    }
    public Apple(Area area, DiscreteCoordinates position, Safe key) {
        super(area, position,key);

    }

    @Override
    public void draw(Canvas canvas) {
        if(getKey().isOn()){
        APPLE.draw(canvas);
        }
    }
    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }
}
