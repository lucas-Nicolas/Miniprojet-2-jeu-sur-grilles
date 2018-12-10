package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Safe extends AreaEntity {
    private Logic key;
    private boolean open;
    private AreaEntity contenu;
    private final Sprite coffre = new Sprite("Safe",1,1,this);

    //take the same place as the contenu.
    public Safe(Area area,Logic key,AreaEntity contenu) {
        super(area, new DiscreteCoordinates((int)contenu.getPosition().x,(int)contenu.getPosition().y));
        this.key=key;
        this.contenu=contenu;
    }

    @Override
    public void update(float deltaTime) {
        if(open){
            getOwnerArea().unregisterActor(this);
            getOwnerArea().registerActor(contenu);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        coffre.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    public void open() {
        open=true;
    }

    @Override
    public boolean takeCellSpace() {
        return !open;
    }

    @Override
    public boolean isViewInteractable() {
        return key.isOn();
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }
}
