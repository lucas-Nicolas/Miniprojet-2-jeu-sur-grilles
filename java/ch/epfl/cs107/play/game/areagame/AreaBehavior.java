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


        protected abstract boolean canEnter(Interactable entity);


        protected abstract boolean canLeave(Interactable entity);

        protected abstract boolean isDoor();

        private void cellInteractionOf(Interactor interactor){
            for(Interactable interactable : entities){
                if(interactable.isCellInteractable()){
                    interactor.interactWith(interactable);
                }
            }
        }

        private void viewInteractionOf(Interactor interactor){
            for(Interactable interactable : entities){
                if(interactable.isCellInteractable()) {
                    interactor.interactWith(interactable);
                }
            }
        }

    }




    public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates){
        for (DiscreteCoordinates coord : coordinates) {
            if (!cells[coord.x][coord.y].canEnter(entity)) {
                return false;
            }
        }
        return true;

    }
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
        List<DiscreteCoordinates> coord = interactor.getCurrentCells();
        for (int i = 0; i <coord.size() ; i++) {
            cells[coord.get(i).x][coord.get(i).y].cellInteractionOf(interactor);
        }
        
    }

    public void viewInteractionOf(Interactor interactor){
        List<DiscreteCoordinates> coord =  interactor.getFieldOfViewCells();
        for (int i = 0; i < coord.size() ; i++) {
            cells[coord.get(i).x][coord.get(i).y].viewInteractionOf(interactor);
        }
    }


}
