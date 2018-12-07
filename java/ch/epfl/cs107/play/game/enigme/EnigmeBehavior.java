package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedList;
import java.util.List;

public class EnigmeBehavior extends AreaBehavior {
    public enum EnigmeCellType{
        NULL(0),
        WALL(-16777216),
        DOOR(-65536),
        WATER(-16776961),
        INDOOR_WALKABLE(-1),
        OUTDOOR_WALKABLE(-14112955);

        final int type;

        EnigmeCellType(int type){
            this.type=type;
        }
        static EnigmeBehavior.EnigmeCellType toType(int type){
            switch (type){
                case 0:
                    return NULL;
                case -16777216:
                    return WALL;
                case-65536:
                    return DOOR;
                case -16776961:
                    return WATER;
                case -1:
                    return INDOOR_WALKABLE;
                case -14112955:
                    return OUTDOOR_WALKABLE;
                default:
                    return null;
            }
        }


    }

    public EnigmeBehavior(Window window, String fileName) {
        super(window, fileName);
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                EnigmeBehavior.EnigmeCellType cellType = EnigmeBehavior.EnigmeCellType.toType(getBehaviorMap().getRGB(getBehaviorMap().getHeight()-1-y,x));
                //on oublie pas d'initialiser les cellules
                getCells()[x][y] = new EnigmeBehavior.EnigmeCell(x,y,cellType);
            }
        }
    }

    public class EnigmeCell extends Cell{
        private EnigmeBehavior.EnigmeCellType type;

        private EnigmeCell(int x, int y, EnigmeBehavior.EnigmeCellType type){
            super(x, y);
            this.type = type;

        }

        public EnigmeBehavior.EnigmeCellType getType() {
            return type;
        }


        @Override
        protected boolean canEnter(Interactable entity) {
            //les portes doivent pouvoir être libremernt placées
            if (entity instanceof Door) {
                return true;
            }
            return !(type == EnigmeBehavior.EnigmeCellType.WALL || type == EnigmeBehavior.EnigmeCellType.NULL);
            //retourne faux si l'entité veut entrer dans un mur et vrai sinon

        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;//toute cellule peut être quittée
        }

        @Override
        public boolean takeCellSpace() {
            return false;//les entités sont traversables
        }

        @Override
        public boolean isViewInteractable() {
            return false;//n'accepte pas les interactions à distance
        }

        @Override
        public boolean isCellInteractable() {
            return true;//interactions au contact
        }
        @Override
        public boolean isDoor(){
            return false;//méthode ne correspondant pas aux attentes de la 3ème partie mais étant requise étant donné que la classe hérite de areaBehavior
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v) {
            ((EnigmeInteractionVisitor)v).interactWith(this);

        }
    }

    @Override
    public boolean isDoor(List<DiscreteCoordinates> coordinates) {
        return  false;
    }
}
