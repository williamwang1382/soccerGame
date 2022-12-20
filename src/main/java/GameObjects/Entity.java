/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Utilisateur
 */
public abstract class Entity extends Node{
    public final double GRAVITY = -9.8;
    protected String name;
    protected double posX;
    protected double posY;
    protected double velX;
    protected double velY;
    protected Direction currentDir;
    protected Hitbox hitbox;
    protected double accelerationX;
    protected double accelerationY;
    protected double mass;
    
    public Entity (){
        this.name = "Entity";
    }
    public Entity(String newName){
        this.name = newName;
    }
    public Entity(Hitbox hitbox){
        this.hitbox = hitbox;
    }
    public Entity(int newPosX, int newPosY){
        this.posX = newPosX;
        this.posY=newPosY;
    }
    public Entity(int newPosX, int newPosY, Hitbox hitbox){
        this.posX = newPosX; this.posY = newPosY; this.hitbox = hitbox;
    }
    public Entity(String newName, double newPosX, double newPosY){
        this.name = newName; this.posX = newPosX; this.posY = newPosY;
    }
    public Entity(String newName, double newPosX, double newPosY, 
            double newVelX, double newVelY, double accelerationX, 
            double accelerationY, Hitbox hitbox, Direction newDir){
        this.name = newName; 
        this.posX = newPosX; 
        this.posY = newPosY; 
        this.velX = newVelX; 
        this.velY = newVelY; 
        this.accelerationX=accelerationX;
        this.accelerationY=accelerationY;
        this.hitbox = hitbox; 
        this.currentDir = newDir; 
    }
    public String getName(){
        return this.name;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public double getPosX(){
        return this.posX;
    }
    public void setPosX(double newPosX){
        this.posX = newPosX;
    }
    public double getPosY(){
        return this.posY;
    }
    public void setPosY(double newPosY){
        this.posY = newPosY;
    }
    public double getVelX(){
        return this.velX;
    }
    public void setVelX(double newVelX){
        this.velX = newVelX;
    }
    public double getVelY(){
        return this.velY;
    }
    public void setVelY(double newVelY){
        this.velY = newVelY;
    }
    public double getGravity(){
        return GRAVITY;
    }
    public Direction getDirection(){
        return this.currentDir;
    }
    public void setDirection(Direction newDir){
        this.currentDir = newDir;
    }
    public double getAccelerationX(){
        return accelerationX;
    }
    public void setAccelerationX(double accelerationX){
        this.accelerationX=accelerationX;
    }
    public double getAccelerationY(){
        return accelerationY;
    }
    public void setAccelerationY(double accelerationY){
        this.accelerationY=accelerationY;
    }
    public void setMass(int mass){
        this.mass = mass;
    }
    public double getMass(){
        return this.mass;
    }
    
    public abstract void tick();
    public abstract void render(GraphicsContext gc);
}