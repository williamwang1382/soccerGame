/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import GameObjects.entityHandler;
import GameObjects.Player;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import GameObjects.Ball;
import GameObjects.Direction;
import GameObjects.Goal;
import GameObjects.Goal.goalID;
import GameObjects.Stopwatch;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author wwang
 */
public class FXMLControllerGameField2 implements Initializable, Runnable{
    @FXML
	public static Canvas canvas;
    @FXML static Pane pane;
    @FXML private GridPane scoreGrid;
    @FXML private Text scorePlayer1;
    @FXML private Text scorePlayer2;
    @FXML private Text timer;
    @FXML private Button exit;
    @FXML private Text p1Name;
    @FXML private Text p2Name;
    @FXML private Text p1NameInfo;
    @FXML private Text p2NameInfo;
    @FXML private Text p1Position;
    @FXML private Text p1Velocity;
    @FXML private Text p1Acceleration;
    @FXML private Text p2Position;
    @FXML private Text p2Velocity;
    @FXML private Text p2Acceleration;
    @FXML private Text ballPosition;
    @FXML private Text ballVelocity;
    @FXML private Text ballAcceleration;
    @FXML private Text p1Momentum;
    @FXML private Text ballMomentum;
    @FXML private Text p2Momentum;
    
    Stopwatch stopwatch = new Stopwatch("300"); // 5-min games
    
    static Thread thread;
    static boolean running = false;
    private entityHandler handler;
    GraphicsContext gc;
    public static int CANVAS_WIDTH = 800; public static int CANVAS_HEIGHT = 470;
    static int PANE_WIDTH = 800; static int PANE_HEIGHT = 500;
    Image gameFieldImage = new Image(new File("./src/main/Resources/images/soccer_field_background.jpg").toURI().toString());
    
    static Ball ball = new Ball(CANVAS_WIDTH/2-20, CANVAS_HEIGHT/2-20);
    public static Player p1 = new Player(CANVAS_WIDTH/2-100 , CANVAS_HEIGHT/2-32, Direction.RIGHT);
    public static Player p2 = new Player(CANVAS_WIDTH/2+100 , CANVAS_HEIGHT/2-32, Direction.LEFT);
    static Goal goal1 = new Goal(0, CANVAS_HEIGHT-160, goalID.GOAL1);
    static Goal goal2 = new Goal(CANVAS_WIDTH-50, CANVAS_HEIGHT-160, goalID.GOAL2);
    GameHandler collisions = new GameHandler();
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    
    @FXML void onClickExit(ActionEvent event) throws Exception{
        FXMLLoader load1 = new FXMLLoader(new File("./src/main/Resources/fxml/gameOver2.fxml").toURI().toURL());
        Parent root1 = load1.load();
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1);
        s.setTitle("HeadSoccer");
        s.setScene(scene);
        s.show();
        running = false;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
    	 
        collisions.restartGameObjectsPosition(ball, p1, p2);
        collisions.restartPlayerScores(p1, p2);
        p1Name.setText(p1.getName());
        p2Name.setText(p2.getName());
        p1NameInfo.setText(p1.getName() + "'s Information");
        p2NameInfo.setText(p2.getName() + "'s Information");
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setLayoutX(0);
        canvas.setLayoutY(30);

        handler = new entityHandler();
        handler.addEntity(ball);
        handler.addEntity(p1);
        handler.addEntity(p2);
        handler.addEntity(goal1);
        handler.addEntity(goal2);

        running = true;
        thread = new Thread(this);
        thread.start();
        gc = canvas.getGraphicsContext2D();
    }
    
    @Override
    public void run(){
            long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
                if(running){
                    try {
                        render();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLControllerGameField2.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(FXMLControllerGameField2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                frames++;
                if (System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }
        }
        stop();
    }
    
    private void tick(){
        handler.tick();
    }

    private void render() throws IOException, Exception{
        isGameOver();
        collisions.isGoal(ball, p1, p2);
        scorePlayer1.setText(p1.getScore());
        scorePlayer2.setText(p2.getScore());
        timer.setText(stopwatch.getTime());
        p1Position.setText("Position : " + df2.format(p1.getPosX()) + ", " + df2.format(p1.getPosY()));
        p1Velocity.setText("Velocity (m/s): " + df2.format(p1.getVelX()) + ", " + df2.format(p1.getVelY()));
        p1Acceleration.setText("Acceleration (m/s^2): " + df2.format(p1.getAccelerationX()) + ", " + df2.format((-1)*p1.getAccelerationY()));
        p1Momentum.setText("Momentum (kg*m/s) :" + df2.format(p1.getMomentumX()) + ", " + df2.format(p1.getMomentumY()));
        p2Position.setText("Position : " + df2.format(p2.getPosX()) + ", " + df2.format(p2.getPosY()));
        p2Velocity.setText("Velocity (m/s): " + df2.format(p2.getVelX()) + ", " + df2.format(p2.getVelY()));
        p2Acceleration.setText("Acceleration (m/s^2): " + df2.format(p2.getAccelerationX()) + ", " + df2.format((-1)*p2.getAccelerationY()));
        p2Momentum.setText("Momentum (kg*m/s) :" + df2.format(p2.getMomentumX()) + ", " + df2.format(p2.getMomentumY()));
        ballPosition.setText("Position : " + df2.format(ball.getPosX()) + ", " + df2.format(ball.getPosY()));
        ballVelocity.setText("Velocity (m/s): " + df2.format(ball.getVelX()) + ", " + df2.format(ball.getVelY()));
        ballAcceleration.setText("Acceleration (m/s^2): " + df2.format(ball.getAccelerationX()) + ", " + df2.format((-1)*ball.getAccelerationY()));
        ballMomentum.setText("Momentum (kg*m/s) :" + df2.format(ball.getMomentumX()) + ", " + df2.format(ball.getMomentumY()));
        collisions.ballCollision(ball, p1);
        collisions.ballCollision(ball, p2);
        collisions.playerCollision(p1);
        collisions.playerCollision(p2);
        gc.setFill(new ImagePattern(gameFieldImage));
        gc.fillRect(0, 0, CANVAS_WIDTH, PANE_HEIGHT);
        handler.render(gc);        
        gc = canvas.getGraphicsContext2D();
    }
    
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        } catch(InterruptedException e){
        }
    }
    
    private void isGameOver() throws Exception{
        if (stopwatch.getTime().equals("Game Over")){
            running = false;
        }
    }
    
}
