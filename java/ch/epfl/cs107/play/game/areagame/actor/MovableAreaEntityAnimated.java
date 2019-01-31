package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public abstract class MovableAreaEntityAnimated extends MovableAreaEntity {
    private final int NUMBER_OF_SPRITES;
    private final Sprite SPRITE;
    private final Sprite[] spritesDOWW;
    private final Sprite[] spritesLEFT;
    private final Sprite[] spritesRIGHT;
    private final Sprite[] spritesUP;
    private int spriteIndex;

    public MovableAreaEntityAnimated(Area area, DiscreteCoordinates position, String SPRITE, int numberOfSprites) {
        super(area, position);
        this.SPRITE = new Sprite(SPRITE,1,1,this);
        this.NUMBER_OF_SPRITES = numberOfSprites;

        spritesDOWW = new Sprite[NUMBER_OF_SPRITES];
        spritesUP = new Sprite[NUMBER_OF_SPRITES];
        spritesLEFT = new Sprite[NUMBER_OF_SPRITES];
        spritesRIGHT = new Sprite[NUMBER_OF_SPRITES];

        Vector anchor = new Vector(0.25f, 0.32f);

        for(int i = 0; i<NUMBER_OF_SPRITES; ++i){
            spritesDOWW[i] = new Sprite(SPRITE, 0.5f, 0.65625f, this, new RegionOfInterest(0, i * 21, 16, 21), anchor);
            spritesLEFT[i] = new Sprite(SPRITE, 0.5f, 0.65625f, this, new RegionOfInterest(16, i * 21, 16, 21), anchor);
            spritesUP[i] = new Sprite(SPRITE, 0.5f, 0.65625f, this, new RegionOfInterest(32, i * 21, 16, 21), anchor);
            spritesRIGHT[i] = new Sprite(SPRITE, 0.5f, 0.65625f, this, new RegionOfInterest(48, i * 21, 16, 21), anchor);
        }
    }
    public MovableAreaEntityAnimated(Area area, DiscreteCoordinates position, String SPRITE) {
        super(area, position);
        this.SPRITE = new Sprite(SPRITE,1,1,this);
        this.NUMBER_OF_SPRITES = 4;

        spritesDOWW = new Sprite[NUMBER_OF_SPRITES];
        spritesUP = new Sprite[NUMBER_OF_SPRITES];
        spritesLEFT = new Sprite[NUMBER_OF_SPRITES];
        spritesRIGHT = new Sprite[NUMBER_OF_SPRITES];

        Vector anchor = new Vector(0.25f, 0.32f);

        for(int i = 0; i<NUMBER_OF_SPRITES; ++i){
            spritesDOWW[i] = new Sprite(SPRITE, 0.5f, 0.65625f, this, new RegionOfInterest(0, i * 21, 16, 21), anchor);
            spritesLEFT[i] = new Sprite(SPRITE, 0.5f, 0.65625f, this, new RegionOfInterest(16, i * 21, 16, 21), anchor);
            spritesUP[i] = new Sprite(SPRITE, 0.5f, 0.65625f, this, new RegionOfInterest(32, i * 21, 16, 21), anchor);
            spritesRIGHT[i] = new Sprite(SPRITE, 0.5f, 0.65625f, this, new RegionOfInterest(48, i * 21, 16, 21), anchor);
        }
    }

    /**
     * dessine le sprite adapté selon l'orientation de l'entité concernée et alterne les sprites de sorte à animer l'entité.`
     * @param canvas target, not null
     */
    @Override
    public void draw(Canvas canvas) {
        if(isMoving()){
            spriteIndex = (++spriteIndex) % NUMBER_OF_SPRITES;
        }else {
            spriteIndex = 0;
        }
        switch (getOrientation()){
            case UP:
                spritesUP[spriteIndex].draw(canvas);
                break;
            case DOWN:
                spritesDOWW[spriteIndex].draw(canvas);
                break;
            case LEFT:
                spritesLEFT[spriteIndex].draw(canvas);
                break;
            case RIGHT:
                spritesRIGHT[spriteIndex].draw(canvas);
                break;
        }
    }

    protected boolean move(int framesForMove) {
        return super.move(framesForMove);
    }
}
