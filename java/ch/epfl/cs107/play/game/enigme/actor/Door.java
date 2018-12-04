package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import javafx.scene.shape.Ellipse;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Door extends AreaEntity {
    private Area areaGoingTo;
    private DiscreteCoordinates arrivalPosition;
    private List<DiscreteCoordinates> positionsAroundMainCell;

    public Door(Area areaLeaving,Area areaGoingTo, Orientation orientation, DiscreteCoordinates arrivalPosition, DiscreteCoordinates currentMainCellCoordinates ,  DiscreteCoordinates... positionsAroundMainCell) {
        super(areaLeaving, orientation, currentMainCellCoordinates);

        this.areaGoingTo = areaGoingTo;
        this.arrivalPosition = arrivalPosition;
        this.positionsAroundMainCell = new LinkedList<>();
        this.positionsAroundMainCell.addAll(Arrays.asList(positionsAroundMainCell));

    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        List<DiscreteCoordinates> current = new LinkedList<>();
        current.add(getCurrentMainCellCoordinates());
        current.addAll(positionsAroundMainCell);

        return current;
    }

    //todo implémenter les fonctions pour pouvoir faire fonctionner la méthode
}
