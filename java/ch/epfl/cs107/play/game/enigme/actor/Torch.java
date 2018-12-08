package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Torch extends ViewSwitch {
    private final Sprite litTorch = new Sprite("torch.ground.on.1", 1,1.f,this);
    private final Sprite offTorch = new Sprite("torch.ground.off.1", 1,1.f,this);

    public Torch(Area area, DiscreteCoordinates position, boolean isLit) {
        super(area, position,isLit);

    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(litTorch,offTorch,canvas);
    }
}
