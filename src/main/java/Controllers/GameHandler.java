package Controllers;

import static Controllers.FXMLControllerGameField2.CANVAS_HEIGHT;
import static Controllers.FXMLControllerGameField2.CANVAS_WIDTH;
import GameObjects.Ball;
import GameObjects.Direction;
import GameObjects.Player;

/**
 *
 * @author rodri
 */
public class GameHandler {
    
    public double ballGravity = 0.1;
    
    public void ballCollision(Ball ball, Player p){
        ball.setAccelerationY(ballGravity);//Here in-game the acceleration from gravity is set to 0.1.
        //We set the window dimension to 800 in width and 470 in height in FXMLControllerGameField2.
        
        //Check if the ball hits the horizontal edges of the game window, taking into account its diameter
        // since the x and y coordinate of the ball are (0, 0) at its top left corner.
        if (ball.getPosX() <= 0){
            ball.setVelX(-1*ball.getVelX());
            ball.setPosX(0);
        }
        
        if (ball.getPosX() >= (CANVAS_WIDTH - ball.getDiameter())){
            ball.setVelX(-1*ball.getVelX());
            ball.setPosX(CANVAS_WIDTH - ball.getDiameter());
        }
        
        //Check for the vertical edges
        if (ball.getPosY() <= 0){
            ball.setVelY(5);
            ball.setPosY(0);
        }
        
        //Check if the ball is over the goals to create a wall over the top of the goals for both of them
        if (ball.getPosX() <= 0 && ball.getPosY() + ball.getDiameter() <= CANVAS_HEIGHT - 160){
            ball.setVelX(3);
        }
        
        if (ball.getPosX() < 50 && ball.getPosY() + ball.getDiameter() <= CANVAS_HEIGHT - 165 
                || ball.getPosX() > CANVAS_WIDTH - 50 && ball.getPosY() + ball.getDiameter() <= CANVAS_HEIGHT - 165){
            ball.setPosY(CANVAS_HEIGHT - 165 - ball.getDiameter());
        }
         
        if(ball.getPosX() + ball.getDiameter() >= CANVAS_WIDTH && ball.getPosY() + ball.getDiameter() <= CANVAS_HEIGHT - 160){
            ball.setVelX(-3);
        }
        
        if (ball.getPosX() > CANVAS_WIDTH - 50 && ball.getPosY() + ball.getDiameter() <= CANVAS_HEIGHT - 160){
            ball.setVelY(-3);
        }
      
        //when the ball hits the ground, we simulate an elastic collision
        if (ball.getPosY() >= (CANVAS_HEIGHT - ball.getDiameter())){
            ball.setVelY(-15);
            ball.setPosY(CANVAS_HEIGHT - ball.getDiameter());
        }
        
        //The formula to apply an acceleration onto the ball is v=u+at where v is final velocity, u is initial velocity, a is acceleration and t is time
        if (ball.getPosY() < (CANVAS_HEIGHT - ball.getDiameter())){ //Check is the ball in the air
            ball.setVelY(ball.getVelY() + (ball.getAccelerationY()) * (2));
        } 
        //Check Ball collision when it hits the player on the head, if so, the ball rebounds 
        if ((((ball.getPosY() + ball.getDiameter())>=p.getPosY())&&(ball.getPosY()<(p.getPosY()+p.getHeight())))&&(((ball.getPosX()+ball.getDiameter())>= p.getPosX())&&(ball.getPosX()<=(p.getPosX()+p.getWidth())))){
            if (ball.getVelY()>=0){
            ball.setVelY(getBallFinalVelY(p, ball)); 
            }
        }

        //Check Ball collision when it hits the player on the left side
        if ((((ball.getPosX() + ball.getDiameter()) >= p.getPosX())&&(ball.getPosX()<=p.getPosX()+p.getWidth())) && (((ball.getPosY()+ball.getDiameter()) >= p.getPosY()) && (ball.getPosY() <= p.getPosY() + p.getHeight()))){
           if (ball.getVelX()>=0){
            ball.setVelX(getBallFinalVelX(p, ball));
           }
        }

        //Check Ball collision when it hits the player on the right side
        if (((ball.getPosX() <= (p.getPosX() + p.getWidth()))&&((ball.getPosX()+ball.getDiameter())>=p.getPosX())) && ((ball.getPosY() >= p.getPosY()) && (ball.getPosY() <= p.getPosY() + p.getHeight()))){
            if (ball.getVelX()<=0){
            ball.setVelX(getBallFinalVelX(p, ball));
            }
        }
    }
    
    public void playerCollision(Player p){
        //Check is the player in the air
        //Here in-game the acceleration from gravity is set to 1.5
        if (p.getPosY() < (CANVAS_HEIGHT - p.getHeight())){
            p.setAccelerationY(0.8);
        }
        
        //The formula to apply an acceleration onto the ball is v=u+at where v is final velocity, u is initial velocity, a is acceleration and t is time
        //In this case, the player is under the gravitaional force
        if (p.getPosY() < (CANVAS_HEIGHT - p.getHeight())){
            p.setVelY(p.getVelY()+(p.getAccelerationY())*(2));
        }
        
        if(p.getPosY() >= (CANVAS_HEIGHT - p.getHeight())){
            p.setAccelerationY(0);
            p.setVelY(0);
            p.setPosY(CANVAS_HEIGHT - p.getHeight());
        }
        
        //To not let the players go beyond the right goal we use 115 because the x position of the player is at its
        //top left corner, therefore to block the whole player of entering inside the goal (115 = goal width (50) + player width(65)).
        if(p.getPosX() >= (CANVAS_WIDTH-115)){
            p.setAccelerationX(0);
            p.setVelX(0);
            p.setPosX(CANVAS_WIDTH-115);
        }
        
        //To not let the players go beyond the left goal we use 50 because it is the goal width.
        if(p.getPosX() <= (50)){
            p.setAccelerationX(0);
            p.setVelX(0);
            p.setPosX(50);
        }
    }
    
    //Ellastic collision methods
    public double getBallFinalVelX(Player p, Ball b){
        return (((b.getMass() - p.getMass()) * b.getVelX() + 2 * p.getMass() * p.getVelX()) / (p.getMass() + b.getMass()));
    }
    public double getBallFinalVelY(Player p, Ball b){
        return (((b.getMass() - p.getMass()) * b.getVelY() + 2 * p.getMass() * p.getVelY()) / (p.getMass() + b.getMass()));
    }
    
    public void isGoal(Ball ball, Player p1, Player p2){
        //If there is a goal, set the ball immobile in Goal 
        //Check if there is a goal at Goal1
        if (ball.getPosX() < 10 && ball.getPosY() > CANVAS_HEIGHT - 160){
            ball.setVelX(0);
            ball.setVelY(0);
            ball.setAccelerationX(0);
            ball.setAccelerationY(0);
            p2.score += 1;
            restartGameObjectsPosition(ball, p1, p2);
        }
        //Check if there is a goal at Goal2
        if (ball.getPosX() > CANVAS_WIDTH - 50 && ball.getPosY() > CANVAS_HEIGHT - 160){
            ball.setVelX(0);
            ball.setVelY(0);
            ball.setAccelerationX(0);
            ball.setAccelerationY(0);
            p1.score += 1;
            restartGameObjectsPosition(ball, p1, p2);
        }
    }
    
    public void isGoal(Ball ball, Player p1){
        //If there is a goal, set the ball immobile in Goal 
        //Check if there is a goal at Goal1
        if (ball.getPosX() < 10 && ball.getPosY() > CANVAS_HEIGHT - 160){
            ball.setVelX(0);
            ball.setVelY(0);
            ball.setAccelerationX(0);
            ball.setAccelerationY(0);
            restartGameObjectsPosition(ball, p1);
        }
        //Check if there is a goal at Goal2
        if (ball.getPosX() > CANVAS_WIDTH - 50 && ball.getPosY() > CANVAS_HEIGHT - 160){
            ball.setVelX(0);
            ball.setVelY(0);
            ball.setAccelerationX(0);
            ball.setAccelerationY(0);
            p1.score += 1;
            restartGameObjectsPosition(ball, p1);
        }
    }
    
    public void restartGameObjectsPosition(Ball ball, Player p1, Player p2){
        ball.setPosX(CANVAS_WIDTH/2-20); ball.setPosY(CANVAS_HEIGHT/2-20);
        p1.setPosX(50); p1.setPosY(CANVAS_HEIGHT/2-32); p1.setDirection(Direction.RIGHT);
        p2.setPosX(CANVAS_WIDTH - 115); p2.setPosY(CANVAS_HEIGHT/2-32); p2.setDirection(Direction.LEFT);
    }
    
    public void restartGameObjectsPosition(Ball ball, Player p1){
        ball.setPosX(CANVAS_WIDTH/2-20); ball.setPosY(CANVAS_HEIGHT/2-20);
        p1.setPosX(50); p1.setPosY(CANVAS_HEIGHT/2-32); p1.setDirection(Direction.RIGHT);
    }
    
    public void restartPlayerScores(Player p1, Player p2){
        p1.setScore(0); p2.setScore(0);
    }
    
    public void restartPlayerScores(Player p1){
        p1.setScore(0);
    }
}
