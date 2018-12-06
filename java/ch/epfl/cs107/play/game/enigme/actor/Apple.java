package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Apple extends Collectable implements Interactable {
    private final Sprite APPLE = new Sprite("apple.1",1,1.f,this);

    public Apple(Area area, DiscreteCoordinates position) {
        super(area, position);

    }

    @Override
    public void draw(Canvas canvas) {
        APPLE.draw(canvas);
    }


    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }
    //Todo agir sur isCollected quand on aura mis en place les interractions


    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }

    public void setCollected(boolean collected) {
        setIsCollected(collected);
    }
}
