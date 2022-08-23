public class Player {
    String name;
    public int score;
    int bodyParts;
    public Player(String name , int score  , int bodyParts){
        this.name = name;
        this.score = score;
        this.bodyParts = bodyParts;
    }

    @Override
    public String toString() {
        return
                name+"\t"+score+"\t"+bodyParts+"\n";
    }
}
