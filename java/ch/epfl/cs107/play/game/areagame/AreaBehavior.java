package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
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
    // TODO implements me #PROJECT #TUTO
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
        // TODO implements me #PROJECT #TUTO
        behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
        width = behaviorMap.getWidth();
        height = behaviorMap.getHeight();
        cells = new Cell[width][height];

    }

    public Cell[][] getCells() {
        return cells;
    }
    // TODO implements me #PROJECT #TUTO

    /**
     * Each game will have its own Cell extension.
     */
    public abstract class Cell implements Interactable {
        private DiscreteCoordinates coordonnee;
        private Set<Interactable> contenu;

        public Cell(int x, int y) {
            coordonnee = new DiscreteCoordinates(x, y);
            contenu = new HashSet<>();

        }

        public List<DiscreteCoordinates> getCurrentCells() {
            List<DiscreteCoordinates> coor = new LinkedList<DiscreteCoordinates>();
            coor.add(coordonnee);
            return coor;
        }

        private void enter(Interactable entity) {
            this.contenu.add(entity);
        }

        private void leave(Interactable entity) {
            this.contenu.remove(entity);
        }


        protected abstract boolean canEnter(Interactable entity);


        protected abstract boolean canLeave(Interactable entity);
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

}
