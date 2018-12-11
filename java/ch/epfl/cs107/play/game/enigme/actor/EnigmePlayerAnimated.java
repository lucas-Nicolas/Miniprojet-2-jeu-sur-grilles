package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class EnigmePlayerAnimated extends EnigmePlayer {
    private final int NUMBER_OF_SPRITES = 4;
    private final Sprite[] spritesDOWW;
    private final Sprite[] spritesLEFT;
    private final Sprite[] spritesRIGHT;
    private final Sprite[] spritesUP;
    private int spriteIndex;

    public EnigmePlayerAnimated(Area area, DiscreteCoordinates position, String SPRITE,Suiveur suiveur) {
        super(area, position, SPRITE, suiveur);
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

    @Override
    public void draw(Canvas canvas) {
        if(isMoving()){
            spriteIndex = (++spriteIndex) % NUMBER_OF_SPRITES;
        }else{
            spriteIndex = 0 ; //si le joueur ne bouge pas on lui affecte la première image.
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
        //si le joueur court, on l'anime une fois de plus
        if(isRunning()){
            spriteIndex = (++spriteIndex) % NUMBER_OF_SPRITES;;
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
        getVie().draw(canvas);
    }
}
