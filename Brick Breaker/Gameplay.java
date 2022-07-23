//importing the libary 

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Font;

//constructor of the Gameplay that is used in main file

public class Gameplay extends JPanel implements KeyListener , ActionListener{

 // declearining the varibals

  private boolean play = false;
  private int score = 0;
  
  private int totalBricks =21;
  private Timer timer;
  private int delay = 3;

  private int playerX=310;

  private int ballposX =120;
  private int ballposY =350;
  private int ballXdir =-1;
  private int ballYdir =-2;

  //for rectangle obj 
  private MapGenerator map;

  
  public Gameplay(){
   //constructor of MapGenerator
    map = new MapGenerator(3,7);


    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    timer = new Timer(delay ,this);
    timer.start();
  }
//graphic import
  public void paint(Graphics g){
    //background
    g.setColor(Color.CYAN);
    g.fillRect(1,1,692,592);

    //drawing map
    map.draw((Graphics2D)g);;

    // creat the boudes
    g.setColor(Color.WHITE);
    g.fillRect(0,0,3,592);
    g.fillRect(0,0,692,3);
    g.fillRect(691,0,3,592);


    //score
    g.setColor(Color.white);
    g.setFont(new Font("serif" , Font.BOLD ,25));
    g.drawString(""+score,590,30);


    //paddel
    g.setColor(Color.green);
    g.fillRect(playerX,550,100,8);

    //ball
    g.setColor(Color.yellow);
    g.fillOval(ballposX,ballposY,20,20);

    //for game winning
    if(totalBricks <= 0){
      play= false;
      ballXdir = 0;
      ballYdir =0;
      g.setColor(Color.red);
      g.setFont(new Font("serif",Font.CENTER_BASELINE,30));
      g.drawString("You Won , Scores is  :"+score,260,300);

      g.setFont(new Font("serif",Font.CENTER_BASELINE,20));
      g.drawString("Press Enter to Restart",230,350);
  

    }

    //for game over
    if(ballposY > 570){
      play= false;
      ballXdir = 0;
      ballYdir =0;
      g.setColor(Color.red);
      g.setFont(new Font("serif",Font.CENTER_BASELINE,30));
      g.drawString("Game Over , Scores is  :"+score,190,300);

      g.setFont(new Font("serif",Font.CENTER_BASELINE,20));
      g.drawString("Press Enter to Restart",230,350);
      
    }

//dispose(); causes the JFrame window to be destroyed and cleaned up by the operating system
    g.dispose();



  }



  @Override
  public void actionPerformed(ActionEvent e){
    timer.start();
    if(play){
      //for rectangle plate 

      if(new Rectangle(ballposX, ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
        ballYdir=-ballYdir;
      }
      //mam.map is 1st map is -> map.draw((Graphics2D)g);; and 2nd ones is public int map[][];
      A: for(int i=0;i< map.map.length;i++){
        for(int j=0;j<map.map[0].length;j++ ){
          if(map.map[i][j] > 0){
            int brickX = j*map.brickWidth +80;
            int brickY = i*map.brickHeight +50;
            int brickWidth =map.brickWidth;
            int brickHeight=map.brickHeight;

            Rectangle rect = new Rectangle(brickX , brickY,brickWidth,brickHeight);
            Rectangle ballRect =new Rectangle(ballposX,ballposY,20,20);
            Rectangle brickRect=rect;

            if(ballRect.intersects(brickRect)){
              map.setBrickValue(0,i, j);
              totalBricks--;
              score+=5;

              if(ballposX + 19 <= brickRect.x || ballposX +1 >= brickRect.x + brickRect.width){
                ballXdir = -ballXdir;

              }
              else {
                ballYdir = -ballYdir;
              }

              break A;


            }

          }

        }
      }


      //ball movement
      ballposX +=ballXdir;
      ballposY += ballYdir;

      if(ballposX < 0){

        ballXdir=- ballXdir;
      }

      if(ballposY < 0){

        ballYdir=- ballYdir;
      }

      if(ballposX >670){

        ballXdir=- ballXdir;
      }
      
      
      


    
    
    }



    repaint();

  }

  @Override
  public void keyTyped(KeyEvent e){

  }

  @Override
  public void keyReleased(KeyEvent e){

  }


  @Override
  public void keyPressed(KeyEvent e){

    if(e.getKeyCode()== KeyEvent.VK_RIGHT){
      if(playerX >= 600){
        playerX=600;
      } 
      else {
        moveRight();
      }
    }

    if(e.getKeyCode()== KeyEvent.VK_LEFT){
      if(playerX <= 10){
        playerX=10;
      } 
      else {
        moveLeft();
      }
    }

    if(e.getKeyCode()== KeyEvent.VK_ENTER){
      if(!play){
        play=true;
        ballposX=120;
        ballposY=350;
        ballXdir =-1;
        ballYdir = -2;
        playerX = 310;
        score=0;
        totalBricks =21;
        map=new MapGenerator(3, 7);
        repaint();


      }

    }


  }

  public void moveRight(){
    play = true;
    playerX+=20;
  }

  public void moveLeft(){
    play = true;
    playerX-=20;
  }





}