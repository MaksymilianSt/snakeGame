import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    JButton newGame , score , exit;
    JPanel panel;


    public BaseFrame(){


        panel = new JPanel();


        newGame = new JButton();
        newGame.setText("New Game");
        newGame.setFont(new Font("MV Boli",Font.BOLD,15));
        newGame.setFocusable(false);
        newGame.setBackground(Color.CYAN);
        newGame.setFont(new Font("MV Boli",Font.BOLD,20));


        newGame.addActionListener( e-> new OptionsFrame() );


        score = new JButton();
        score.setText("High Scores");
        score.setFont(new Font("MV Boli",Font.BOLD,15));
        score.setFocusable(false);
        score.setBackground(Color.magenta);
        score.setFont(new Font("MV Boli",Font.BOLD,20));
        score.addActionListener(e -> new ScoreFrame() );

        exit = new JButton();
        exit.setFont(new Font("MV Boli",Font.BOLD,15));
        exit = new JButton();
        exit.setText("Exit");
        exit.addActionListener(e -> this.dispose());
        exit.setBackground(new Color(106,13,173));
        exit.setFocusable(false);
        exit.setFont(new Font("MV Boli",Font.BOLD,20));

        panel.setBackground(new Color(0x123456));
        panel.add(newGame);
        panel.add(score);
        panel.add(exit);
        panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        panel.setLayout(new GridLayout(3,1,30,30));
        this.setLayout(new GridLayout(1,1,5,5));
        this.setForeground(Color.BLACK);
        this.getContentPane().setBackground(new Color(0x123456));
        this.setTitle("SNAKE GAME");
        this.add(panel);
        this.pack();


        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}