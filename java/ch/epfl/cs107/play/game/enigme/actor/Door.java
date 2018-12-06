package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Door extends AreaEntity {
    private String areaGoingTo;
    private DiscreteCoordinates arrivalPosition;
    private List<DiscreteCoordinates> positionsAroundMainCell;

    public Door(Area areaLeaving, String areaGoingTo, DiscreteCoordinates arrivalPosition, Orientation orientation, DiscreteCoordinates currentMainCellCoordinates, DiscreteCoordinates... positionsAroundMainCell) {
        super(areaLeaving, orientation, currentMainCellCoordinates);

        this.areaGoingTo = areaGoingTo;
        this.arrivalPosition = arrivalPosition;
        this.positionsAroundMainCell = new LinkedList<>();
        this.positionsAroundMainCell.addAll(Arrays.asList(positionsAroundMainCell));

    }

    @Override
    public boolean takeCellSpace() {
        return true;
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
    //j'avais pas compris du coup j'ai fait le con mais ça peut servir peut etre
    //je pense pas que ce que j'ai fait marche non plus mais bon ça à l'air pas si mal je regarde demain
    public boolean isInDoor(MovableAreaEntity a) {
        for (DiscreteCoordinates door : getCurrentCells()) {
            if (door.equals(new DiscreteCoordinates((int)a.getPosition().x , (int)a.getPosition().y))) {
                return true;
            }
        }
        return false;
    }

    public DiscreteCoordinates getArrivalPosition() {
        return arrivalPosition;
    }

    public String getAreaGoingTo() {
        return areaGoingTo;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
    }
}