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
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Utilisateur
 */
public class entityHandler {
    
    LinkedList<Entity> entity = new LinkedList<>();
    
    public void tick(){
        for (int i = entity.size() - 1; i >= 0 ; i--){
            Entity temp = entity.get(i);
            temp.tick();
        }
    }
    public void render(Entity e){
        e.tick();
    }
    public void render(GraphicsContext gc){
        for (int i = entity.size() - 1; i >= 0; i--){
            Entity temp = entity.get(i);
            
            temp.render(gc);
        }
    }
    
    public void addEntity(Entity entity){
        this.entity.add(entity);
    }
    public void removeEntity(Entity entity){
        this.entity.remove(entity);
    }
    
}
