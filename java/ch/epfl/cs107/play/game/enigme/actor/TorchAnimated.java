package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class TorchAnimated extends Torch {
   // private final Sprite on1 = new Sprite("torch.ground.on.1",1,1,this);
    private final Sprite on2 = new Sprite("torch.ground.on.2",1,1,this);
    private int spriteIndex;

    public TorchAnimated(Area area, DiscreteCoordinates position, boolean isLit) {
        super(area, position, isLit);
    }

    @Override
    public void draw(Canvas canvas) {
        spriteIndex = ++spriteIndex %2;
        if (!isActivated() || spriteIndex == 1){
        super.draw(canvas);
        }else {
            on2.draw(canvas);
        }
    }
}
