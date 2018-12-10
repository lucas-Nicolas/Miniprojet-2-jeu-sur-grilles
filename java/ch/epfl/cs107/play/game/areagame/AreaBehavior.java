package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;
import javafx.scene.control.Cell;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior {

    /// The behavior is an Image of size height x width

    private final Image behaviorMap;
    private final int width, height;
    private final Cell[][] cells;


    /**
     * Default AreaBehavior Constructor
     *
     * @param window   (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public AreaBehavior(Window window, String fileName) {

        behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
        width = behaviorMap.getWidth();
        height = behaviorMap.getHeight();
        cells = new Cell[width][height];

    }

    public Cell[][] getCells() {
        return cells;
    }


    /**
     * Each game will have its own Cell extension.
     */
    public abstract class Cell implements Interactable {
        private DiscreteCoordinates coordonnee;
        private Set<Interactable> entities;

        public Cell(int x, int y) {
            coordonnee = new DiscreteCoordinates(x, y);
            entities = new HashSet<>();

        }

        public List<DiscreteCoordinates> getCurrentCells() {
            List<DiscreteCoordinates> coor = new LinkedList<>();
            coor.add(coordonnee);
            return coor;
        }

        private void enter(Interactable entity) {
            this.entities.add(entity);
        }

        private void leave(Interactable entity) {
            this.entities.remove(entity);
        }


        /**
         * @param entity
         *
         * @return if a cell can be entered by the entity
         */
        protected abstract boolean canEnter(Interactable entity);

        /**
         * @param entity
         * @return if a cell can be left by the entity or not
         */
        protected abstract boolean canLeave(Interactable entity);

        /**
         * Determines if the cell is a door or not
         * @return
         */
        protected abstract boolean isDoor();

        /**
         * Does all the cell interactions related to a particular interactor
         * @param interactor
         */
        private void cellInteractionOf(Interactor interactor){
            for(Interactable interactable : entities){
                if(interactable.isCellInteractable()){
                    interactor.interactWith(interactable);
                }
            }
        }

        /**
         * Does all the view interactions related to a particular interactor
         * @param interactor
         */
        private void viewInteractionOf(Interactor interactor){
            for(Interactable interactable : entities){
                if(interactable.isViewInteractable()) {
                    interactor.interactWith(interactable);
                }
            }
        }

    }


    /**
     * Checks for each cell in the coordinates given if the entity can enter the cell
     *
     * ADDED Pour chaque cellule on vérifie si l'espace n'est pas déjà occupé avant même d'appeler la fonction canEnter
     * @param entity
     * @param coordinates
     * @return boolean
     */
    public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates){
        for (DiscreteCoordinates coord : coordinates) {
            for (Interactable interactableOnCell : cells[coord.x][coord.y].entities) {
                if(interactableOnCell.takeCellSpace() && !interactableOnCell.equals(entity)){
                    return false;
                }
            }
            if (!cells[coord.x][coord.y].canEnter(entity)) {
                return false;
            }
        }
        return true;

    }
    /**
     * Checks for each cell in the coordinates given if the entity can leave the cell
     * @param entity
     * @param coordinates
     * @return boolean
     */

    public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates){
        for (DiscreteCoordinates coord : coordinates) {
            if (!cells[coord.x][coord.y].canLeave(entity)) {
                return false;
            }
        }
        return true;

    }
    public boolean isDoor(List<DiscreteCoordinates> coordinates){
        for (DiscreteCoordinates coord : coordinates) {
            if (cells[coord.x][coord.y].isDoor()) {
                return true;
            }
        }
        return false;
    }

    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates){
        for (DiscreteCoordinates coord : coordinates) {
            cells[coord.x][coord.y].leave(entity);
        }
    }

    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates){
        for (DiscreteCoordinates coord : coordinates) {
            cells[coord.x][coord.y].enter(entity);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getBehaviorMap() {
        return behaviorMap;
    }

    public void cellInteractionOf(Interactor interactor){
        List<DiscreteCoordinates> coordinatesList = interactor.getCurrentCells();
        for (DiscreteCoordinates coordinates : coordinatesList) {
            cells[coordinates.x][coordinates.y].cellInteractionOf(interactor);
        }
        
    }

    public void viewInteractionOf(Interactor interactor){
        List<DiscreteCoordinates> coordinatesList =  interactor.getFieldOfViewCells();
        for (DiscreteCoordinates coordinates : coordinatesList) {
            cells[coordinates.x][coordinates.y].viewInteractionOf(interactor);
        }
    }


}
