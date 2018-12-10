package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class HealthPotion extends Collectable {
    private final Sprite potion = new Sprite("potion.3",0.7f,0.7f,this);


    public HealthPotion(Area area, DiscreteCoordinates position) {
        super(area, position);
    }
    public HealthPotion(Area area, DiscreteCoordinates position, Safe key) {
        super(area, position,key);
    }

    @Override
    public void draw(Canvas canvas) {
        if(getKey().isOn()) {
            potion.draw(canvas);
        }
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }



    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);

    }
}
