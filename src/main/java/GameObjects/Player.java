/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

/**
 *
 * @author wwang
 */

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


public class Player extends Entity{
    public int score;
    public boolean jumping;
    //65 is the width and 104 is height
    private int height = 104;
    private int width = 65;
    
    public enum playerID{ //types of players;
        PLAYER1, PLAYER2, PLAYER3
    }
    private playerID playerID;
    
    Image player1_1 = new Image(new File("C:\\Users\\wwang\\OneDrive\\Desktop\\CS PROJECTS\\soccer game champlain\\soccerGameProject\\src\\main\\Resources\\images\\player1_1.png").toURI().toString());
    Image player1_2 = new Image(new File("C:\\Users\\wwang\\OneDrive\\Desktop\\CS PROJECTS\\soccer game champlain\\soccerGameProject\\src\\main\\Resources\\images\\player1_2.png").toURI().toString());
    Image player2_1 = new Image(new File("C:\\Users\\wwang\\OneDrive\\Desktop\\CS PROJECTS\\soccer game champlain\\soccerGameProject\\src\\main\\Resources\\images\\player2_1.png").toURI().toString());
    Image player2_2 = new Image(new File("C:\\Users\\wwang\\OneDrive\\Desktop\\CS PROJECTS\\soccer game champlain\\soccerGameProject\\src\\main\\Resources\\images\\player2_2.png").toURI().toString());
    Image player3_1 = new Image(new File("C:\\Users\\wwang\\OneDrive\\Desktop\\CS PROJECTS\\soccer game champlain\\soccerGameProject\\src\\main\\Resources\\images\\player3_1.png").toURI().toString());
    Image player3_2 = new Image(new File("C:\\Users\\wwang\\OneDrive\\Desktop\\CS PROJECTS\\soccer game champlain\\soccerGameProject\\src\\main\\Resources\\images\\player3_2.png").toURI().toString());
    
    public Player (String name, int posX, int posY, playerID newPlayerID, Direction dir){
        super(name, posX, posY);
        this.playerID = newPlayerID;
        this.currentDir = dir;
        this.mass = 1;
    }
    public Player (int posX, int posY, playerID newPlayerID, Direction dir){
        super(posX, posY);
        this.playerID = newPlayerID;
        this.currentDir = dir;
        this.mass = 1;
    }
    public Player (int posX, int posY, Direction dir){
        super(posX, posY);
        this.currentDir = dir;  
        this.mass = 1;
    }
    public Player(){
        
    }
    
    public Image checkPlayerID(){
        if (this.playerID == playerID.PLAYER1){
            if (this.currentDir == Direction.LEFT){
                return player1_1;
            }
            else{
                return player1_2;
            }
        }
        if (this.playerID == playerID.PLAYER2){
            if (this.currentDir == Direction.LEFT){
                return player2_1;
            }
            else{
                return player2_2;
            }
        }
        if (this.playerID == playerID.PLAYER3){
            if (this.currentDir == Direction.LEFT){
                return player3_1;
            }
            else{
                return player3_2;
            }
        }
        return null;
    }
    
    public Player(String name, int posX, int posY){
        super(name, posX, posY);
        this.mass = 1;
    }
    public Player(String newName, double newPosX, double newPosY, 
            double newVelX, double newVelY, double accelerationX, 
            double accelerationY, Hitbox hitbox, Direction newDir, int newScore){
         super(newName, newPosX, newPosY, newVelX, newVelY, accelerationX, 
                 accelerationY, hitbox, newDir);
         this.score = newScore;
         this.mass = 1;
    }
    public void setScore(int newScore){
        this.score = newScore;
    }
    public String getScore(){
        return String.valueOf(this.score);
    }
    public playerID getPlayerID(){
        return this.playerID;
    }
    public void setPlayerID(playerID newPlayerID){
        this.playerID = newPlayerID;
    }
    public int getHeight(){
        return this.height;
    }
    public int getWidth(){
        return this.width;
    }
    public double getMomentumX(){
        return (mass*this.velX);
    }
    public double getMomentumY(){
        return (mass*this.velY);
    }
    
    @Override
    public void tick() {
        posX += velX;
        posY += velY;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(new ImagePattern(checkPlayerID()));
        gc.fillRoundRect(posX, posY, width , height, 60, 60);
    }
}
