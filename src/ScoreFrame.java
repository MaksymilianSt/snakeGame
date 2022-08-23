import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreFrame extends JFrame {
    File file = new File("score.txt");
    JTextArea textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);
    ArrayList<Player> scores = new ArrayList<>();


    public  ScoreFrame(){
        textArea.setBackground(new Color(117,21,223));
        textArea.setFont(new Font("MV Boli",Font.BOLD,20));
        textArea.setCaretColor(Color.white);
        addStats();
        this.add(scrollPane);
        this.setSize(700,700);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);

    }
    public void addStats() {
        textArea.setText("MIEJSCE:\tIMIE:\tWYNIK:\tDLUGOSC:\n");
        try {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNext()){


                String name = fileScanner.next();
                int score= Integer.parseInt(fileScanner.next());
                int bodyP=Integer.parseInt(fileScanner.next());

                scores.add(new Player(name,score,bodyP));

            }


            fileScanner.close();
            int p = 1;
            while (!scores.isEmpty()) {
                Player maxScore =  scores.get(0);
                int delete= 0 ;
                for (int i = 0; i < scores.size(); i++) {

                    if(scores.get(i).score > maxScore.score) {
                        maxScore = scores.get(i);
                        delete=i;
                    }
                }
                textArea.append(p+"\t"+maxScore.toString());
                p++;
                scores.remove(delete);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}