package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntityAnimated;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public abstract class Meneur extends MovableAreaEntity {
    private Suiveur suiveur;

    public Meneur(Area area, Orientation orientation, DiscreteCoordinates position, Suiveur suiveur) {
        super(area,orientation, position);
        this.suiveur= suiveur;
    }
    public Meneur(Area area, DiscreteCoordinates position, Suiveur suiveur){
        super(area,position);
        this.suiveur = suiveur;
    }

    @Override
    public void update(float deltaTime) {
        suiveur.setOrientation(getOrientation());
        super.update(deltaTime);
        suiveur.move();
        suiveur.update(deltaTime);

    }

   protected Suiveur getSuiveur(){
        return suiveur;
    }


    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }


}
