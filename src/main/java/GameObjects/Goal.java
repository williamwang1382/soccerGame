package GameObjects;


import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author rodri
 */
public final class Goal extends Entity {
    private goalID goalID;
    //50 is the width and 160 is the height of the goal
    static final int GHEIGHT = 160;
    static final int GWIDTH = 50;
    
    public enum goalID { //GOAL1: RIGHT, GOAL2: LEFT
        GOAL1, GOAL2
    }
    
    Image goal1 = new Image(new File("C:\\Users\\wwang\\OneDrive\\Desktop\\CS PROJECTS\\soccer game champlain\\soccerGameProject\\src\\main\\Resources\\images\\goal1.png").toURI().toString());
    Image goal2 = new Image(new File("C:\\Users\\wwang\\OneDrive\\Desktop\\CS PROJECTS\\soccer game champlain\\soccerGameProject\\src\\main\\Resources\\images\\goal2.png").toURI().toString());
    
    public Goal(int newPosX, int newPosY, goalID ID) {
        super(newPosX, newPosY);
        this.goalID = ID;
        if (this.goalID == goalID.GOAL1){
            this.currentDir = Direction.RIGHT;
        }
        if (this.goalID == goalID.GOAL2){
            this.currentDir = Direction.LEFT;
        }
    }
    
    public Image checkGoalID(){
        if (this.goalID == goalID.GOAL1){
            return goal1;
        }
        if (this.goalID == goalID.GOAL2){
            return goal2;
        }
        return null;
    }
    
    public goalID getGoalID(){
        return this.goalID;
    }
    public void setGoalID(goalID newGoalID){
        this.goalID = newGoalID;
    }
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(new ImagePattern(checkGoalID()));
        gc.fillRect(posX, posY, GWIDTH, GHEIGHT); 
    }
    
}
