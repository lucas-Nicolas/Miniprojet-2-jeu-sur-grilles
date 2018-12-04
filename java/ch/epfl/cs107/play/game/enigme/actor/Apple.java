package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.LinkedList;
import java.util.List;

public class Apple extends AreaEntity implements Interactable {
    private final Sprite APPLE = new Sprite("apple.1",1,1.f,this);
    private boolean isCollected;

    public Apple(Area area, DiscreteCoordinates position) {
        super(area, position);
        isCollected = false;
    }

    @Override
    public void draw(Canvas canvas) {
        APPLE.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        List<DiscreteCoordinates> currentCells = new LinkedList<>();
        currentCells.add(getCurrentMainCellCoordinates());
        return currentCells;
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
}
