import javax.swing.JFrame;

public class Main{

    public static void main (String[] args){
        JFrame obj=new JFrame();
        //obj of Gameplay
        Gameplay gamePlay = new Gameplay();

        obj.setBounds(10,10,700,600);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //adding the object in gameplay
        obj.add(gamePlay);



    }
}

