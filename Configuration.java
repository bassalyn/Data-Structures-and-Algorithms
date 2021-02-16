public class Configuration {

    private String config;
    private int score;                  //initialize instance variables

    public Configuration(String config, int score){     //constructor for configuration
        this.config = config;
        this.score = score;
    }

    public String getStringConfiguration(){
        return this.config;
    }   //getters for attributes

    public int getScore(){
        return this.score;
    }
}
