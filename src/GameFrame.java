import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public  GameFrame(String name , int screenHeight ,int screenWidth ,int delay){


        this.add(new GamePanel(name,screenHeight,screenWidth,delay));
        this.setTitle("SnakeGame");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}
