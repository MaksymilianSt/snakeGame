import javax.swing.*;
import java.awt.*;

public class OptionsFrame extends JFrame {
    int screenWidth =500, screenHeight=500;
    int delay =75;
    String name ;

    JRadioButton gameSizeSmall ,gameSizeMid , gameSizeBig;
    JRadioButton ez , mid ,hard;
    JTextField sizeText ,lvlText, nameText;
    JPanel sizePanel , lvlPanel ;
    JButton playGame ;
    public OptionsFrame(){

        sizeText = new JTextField("Game Size :");
        sizeText.setFont(new Font("MV Boli",Font.BOLD,15));
        sizeText.setEditable(false);

        sizePanel = new JPanel();
        sizePanel.setBackground(new Color(0x123456));
        sizePanel.add(sizeText);


        ButtonGroup buttonGroup = new ButtonGroup();

        gameSizeSmall = new JRadioButton("600x600");
        gameSizeSmall.addActionListener( e ->{
            screenHeight =600;
            screenWidth = 600;
        });
        gameSizeMid = new JRadioButton("800x800");
        gameSizeMid.addActionListener( e ->{
            screenHeight =800;
            screenWidth = 800;
        });
        gameSizeBig = new JRadioButton("1000x800");
        gameSizeBig.addActionListener( e ->{
            screenHeight =800;
            screenWidth = 1000;
        });

        buttonGroup.add(gameSizeSmall);
        buttonGroup.add(gameSizeMid);
        buttonGroup.add(gameSizeBig);


        sizePanel.add(gameSizeSmall);
        sizePanel.add(gameSizeMid);
        sizePanel.add(gameSizeBig);





        lvlPanel = new JPanel();
        lvlPanel.setBackground(new Color(0x123456));
        lvlText = new JTextField("wybierz poziom : ");
        lvlText.setFont(new Font("MV Boli",Font.BOLD,15));
        lvlText.setEditable(false);

        ez= new JRadioButton("EASY");
        ez.addActionListener(e -> delay =90   - screenHeight/100 -screenWidth/100);

        mid = new JRadioButton("MID");
        mid.addActionListener(e -> delay = 75 - screenHeight/100 -screenWidth/100);

        hard = new JRadioButton("HARD");
        hard.addActionListener(e -> delay = 55 - screenHeight/100 -screenWidth/100);


        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(ez);
        buttonGroup1.add(mid);
        buttonGroup1.add(hard);

        lvlPanel.add(lvlText);
        lvlPanel.add(ez);
        lvlPanel.add(mid);
        lvlPanel.add(hard);


        JPanel panelImie = new JPanel();
        panelImie.setBackground(new Color(0x123456));

        JTextField labelImie = new JTextField("PODAJ IMIE :");
        labelImie.setEditable(false);
        labelImie.setFont(new Font("MV Boli",Font.BOLD,15));

        nameText = new JTextField("anonim           ");
        nameText.setFont(new Font("MV Boli",Font.BOLD,15));

        panelImie.add(labelImie);
        panelImie.add(nameText);


        playGame = new JButton("PLAY GAME");
        playGame.addActionListener(e-> {
                    new GameFrame(nameText.getText(), screenHeight, screenWidth, delay);
                    this.dispose();
                }
        );


        this.add(sizePanel);
        this.add(lvlPanel);
        this.add(panelImie);
        this.add(playGame);
        this.setForeground(Color.BLACK);
        this.getContentPane().setBackground(new Color(0x123456));

        this.pack();

        this.setLayout(new GridLayout(4,1));
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);
    }
}
