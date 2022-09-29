import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Random;

public class GamePanel  extends JPanel implements ActionListener {
    String name = "no name";
    static  int screenWidth = 600;
    static    int screenheight = 600;
    static  final int SQUARE_SIZE = 25 ;
    static  final int GAME_SIZE = (screenheight * screenWidth)/ SQUARE_SIZE;
    static   int delay =  55;
    final int x[] = new int[GAME_SIZE];
    final int y[] = new int[GAME_SIZE];
    int bodyParts = 1;
    int fruitpoints = 0 ;
    int fruitX;
    int fruitY;
    char direction = 's';
    boolean wonszAlive =false;
    Timer timer;
    Random random;
    boolean goldApple = false;




    public GamePanel(String name , int screenHeight ,int screenWidth ,int delay){
        this.name = name;
        this.screenheight = screenHeight;
        this.screenWidth = screenWidth;
        this.delay = delay;

        random = new Random();
        this.setPreferredSize(new Dimension(screenWidth, screenheight));
        this.setBackground(new Color(0x123456));
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
                                @Override
                                public void keyPressed(KeyEvent e) {
                                    switch (e.getKeyChar()){
                                        case 'a'->{
                                            if(direction != 'd')
                                                direction = 'a';
                                        }
                                        case 'd' ->{
                                            if(direction != 'a')
                                                direction = 'd';
                                        }
                                        case 'w' ->{
                                            if(direction != 's')
                                                direction = 'w';
                                        }
                                        case 's'->{
                                            if(direction != 'w')
                                                direction = 's';
                                        }
                                    }

                                }
                            }
        );

        startGame();

    }
    public void startGame(){
        newApple();
        wonszAlive = true ;
        timer = new Timer(delay,this);
        timer.start();
    }
    public  void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g ,goldApple);
    }
    public void draw(Graphics g, boolean goldApple){


        if(wonszAlive) {
            g.setFont(new Font("Helvetica",Font.PLAIN,10));
            g.drawString("score : "+ fruitpoints, screenWidth /12, screenheight /12);
            if (goldApple) {
                g.setColor(new Color(212, 175, 55));
            } else
                g.setColor(new Color(255 - fruitpoints * 2, 0, fruitpoints * 2));

            g.fillOval(fruitX, fruitY, SQUARE_SIZE, SQUARE_SIZE);
            g.setColor(Color.green);
            g.fillRect(fruitX, fruitY, SQUARE_SIZE / 4, SQUARE_SIZE / 4);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(new Color(01, 32, 19));
                    g.fillRect(x[i], y[i], SQUARE_SIZE, SQUARE_SIZE);
                    if (goldApple)
                        g.setColor(new Color(212, 175, 55));
                    else
                        g.setColor(new Color(255 - fruitpoints * 2, 0, fruitpoints * 2));
                    g.fillOval(x[i], y[i], SQUARE_SIZE / 3, SQUARE_SIZE / 3);


                } else {
                    g.setColor(new Color(01, 32 + i * 2, 19));

                    g.fillRect(x[i], y[i], SQUARE_SIZE, SQUARE_SIZE);
                }
            }
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("MV Boli",Font.PLAIN,20));
            g.drawString("score : "+ fruitpoints, screenWidth /22, screenheight /22);
            g.drawString("dlugosc  : "+bodyParts, screenWidth - 6* SQUARE_SIZE, screenheight /22);
        }else
            gameOver(g);
    }
    public void newApple(){
        fruitX = random.nextInt((int)(screenWidth / SQUARE_SIZE))* SQUARE_SIZE;
        fruitY = random.nextInt((int)(screenheight / SQUARE_SIZE))* SQUARE_SIZE;
        goldApple = ((int)(Math.random()*100))%10 == 0 ;

    }
    public void move(){
        for (int i = bodyParts; i >0 ; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction){
            case 'w'->  y[0]= y[0]- SQUARE_SIZE;
            case 's'->  y[0]= y[0]+ SQUARE_SIZE;
            case 'a' -> x[0]= x[0]- SQUARE_SIZE;
            case 'd' -> x[0]= x[0]+ SQUARE_SIZE;

        }

    }
    public void checkApple(){
        if((x[0]== fruitX) && (y[0]== fruitY)){

            if(!goldApple) {
                bodyParts++;
            }else {
                if(bodyParts>1)
                    bodyParts--;
            }

            fruitpoints++;
            newApple();

        }


    }
    public  void  checkColision(){
        for (int i = bodyParts; i >0 ; i--) {
            if((x[0]==x[i]) && (y[0]==y[i])){
                wonszAlive = false;
            }
        }
        if(
                (x[0]<0)|| (x[0]> screenWidth)|| (y[0] <0) || (y[0]> screenheight)
        )
            wonszAlive = false;
        if(!wonszAlive)
            timer.stop();
    }
    public  void gameOver(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("MV Boli",Font.PLAIN,30));

        g.drawString(":(( ,,this is the End\" -"+name, screenWidth /9, screenheight /9);
        g.setFont(new Font(null,Font.PLAIN,25));
        g.drawString("wynik :"+ fruitpoints, screenWidth /7, screenheight /3 );
        g.drawString("dlugosc snejka:"+ bodyParts , screenWidth /7, screenheight /2 );
        score();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(wonszAlive){
            move();
            checkApple();
            checkColision();

        }
        repaint();
    }
    public  void score(){
        File file = new File("score.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(new File("score.txt"),true);
            Formatter formatter = new Formatter(fw);
            formatter.format("%s\r\n",this.name+"\t"+this.fruitpoints+"\t"+this.bodyParts);
            fw.close();
            formatter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
