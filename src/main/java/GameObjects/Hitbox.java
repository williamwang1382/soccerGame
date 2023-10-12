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
import javafx.scene.shape.Shape;

public class Hitbox {
    private Shape hitbox;
    public void Hitbox(Shape shape){
        this.hitbox = shape;
    }
    public Shape getHitbox(){
        return this.hitbox;
    }
    public void setHitbox(Shape newHitbox){
        this.hitbox = newHitbox;
    }
}