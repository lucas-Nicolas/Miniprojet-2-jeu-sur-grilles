package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Torch extends Switch {
    private final Sprite litTorch = new Sprite("torch.ground.on.1", 1,1.f,this);
    private final Sprite offTorch = new Sprite("torch.ground.off.1", 1,1.f,this);

    public Torch(Area area, DiscreteCoordinates position, boolean isLit) {
        super(area, position,isLit);

    }

    @Override
    public void draw(Canvas canvas) {
        if(isActivated){
            litTorch.draw(canvas);
        }else{
            offTorch.draw(canvas);
        }
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }
}
