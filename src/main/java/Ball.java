/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


/**
 *
 * @author wwang
 */
public class Ball extends Entity{
    private double radius = 20;
    private int diameter = 40;
    Image ball = new Image(new File("./src/main/Resources/images/Ball.png").toURI().toString());
    
    public Ball(){
        this.mass = 0.45;
    }
   public Ball(String name, int posX, int posY){
       super(name, posX, posY);
       this.mass=0.45;
   }
   public Ball(int posX, int posY){
       super(posX, posY);
       this.mass=0.45;
   }
   
    public Ball(String newName, int newPosX, int newPosY, double newVelX, double newVelY, double accelerationX, double accelerationY, Hitbox hitbox, Direction dir){
       super(newName, newPosX, newPosY, newVelX, newVelY, accelerationX, accelerationY, hitbox, dir);
       this.mass=0.45;
   }
    
    public void setRadius(int radius){
        this.radius = radius;
    }
    
    public double getRadius(){
        return this.radius;
    }
    public int getDiameter(){
        return this.diameter;
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
        gc.setFill(new ImagePattern(ball));
        gc.fillOval(posX, posY, diameter, diameter);
    }
    
}