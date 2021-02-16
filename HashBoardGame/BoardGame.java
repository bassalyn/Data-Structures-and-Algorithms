public class BoardGame {

    public int board_size;
    public int empty_positions;
    public int max_levels;
    public char[][] gameBoard;      // initialize the instance variables 


    public BoardGame (int board_size, int empty_positions, int max_levels){
        this.board_size = board_size;
        this.empty_positions = empty_positions;
        this.max_levels = max_levels;
        this.gameBoard = new char[board_size][board_size];      //constructor
        for (int i=0;i<board_size;i++){
            for (int j=0;j<board_size;j++){
                gameBoard[i][j] = 'g';                      //loops through and sets all entries to 'g'
            }
        }
    }

    public HashDictionary makeDictionary(){                 //makes a new Hashdictionary of chosen size
        int size = 9967;
        HashDictionary dictionary = new HashDictionary(size);
        return dictionary;
    }

    public int isRepeatedConfig(HashDictionary dict){        //determines if dictionary has a repeated config
        String content = "";
        for (int i=0;i<board_size;i++){
            for (int j=0;j<board_size;j++){
                content += Character.toString(gameBoard[j][i]);      //converts to a string representation
            }
        }
        return dict.getScore(content);                          //-1 if empty returned or score if there
    }

    public void putConfig(HashDictionary dict, int score){      //put config into the hashdictionary
        String content ="";
        for (int i=0;i<board_size;i++){
            for (int j=0;j<board_size;j++){
                content += Character.toString(gameBoard[j][i]);         //converts to a string representation
            }
        }
        Configuration config = new Configuration(content,score);
        try {
            dict.put(config);
        } catch (DictionaryException e) {               //catches a dictionary exception from the put of the config
        }
    }

    public void savePlay(int row, int col, char symbol){
        gameBoard[row][col]=symbol;                     //saves a given play from a player in the gameboard
    }

    public boolean positionIsEmpty (int row, int col){      //checks if a position is empty
        if (gameBoard[row][col]=='g'){
            return true;
        }else {
            return false;
        }
    }

    public boolean tileOfComputer (int row, int col){           //checks if a tile is a computer symbol
        if (gameBoard[row][col]=='o'){
            return true;
        }else {
            return false;
        }
    }

    public boolean tileOfHuman (int row, int col){             //checks if a tile is a human symbol
        if (gameBoard[row][col]=='b'){
            return true;
        }else {
            return false;
        }
    }

    public boolean wins (char symbol){                          //checks if a player has won based on input symbol
        for (int i=0;i<board_size;i++){
            int humanTile = 0;
            int computerTile = 0;
            for (int j=0;j<board_size;j++){         //checks rows for number of consecutive tiles
                if (tileOfHuman(i,j)){
                    humanTile++;
                }
                if (tileOfComputer(i,j)){
                    computerTile++;
                }
            }
            if ((humanTile == board_size && symbol == 'b')|| (computerTile == board_size && symbol == 'o')){
                return true;                        //if boardsize symbol consecutive return true
            }
        }
        for (int i=0;i<board_size;i++){                 //checks columns for number of consecutive tiles
            int humanTile = 0;
            int computerTile = 0;
            for (int j=0;j<board_size;j++){
                if (tileOfHuman(j,i)){
                    humanTile++;
                }
                if (tileOfComputer(j,i)){
                    computerTile++;
                }
            }
            if ((humanTile == board_size && symbol == 'b')|| (computerTile == board_size && symbol == 'o')){
                return true;                       //if boardsize symbol consecutive return true
            }
        }
        int humanTile = 0;
        int computerTile = 0;

        for (int i=0;i<board_size;i++){             //checks diagonal for number of consecutive tiles
            if (i==0){
                humanTile = 0;
                computerTile = 0;
            }
            if (tileOfHuman(i,i)){
                humanTile++;
            }
            if (tileOfComputer(i,i)){
                computerTile++;
                }
            if ((humanTile == board_size && symbol == 'b')|| (computerTile == board_size && symbol == 'o')){
                return true;                       //if boardsize symbol consecutive return true
            }
        }
        humanTile=0;
        computerTile=0;
        for (int i=0;i<board_size;i++){             //checks other diagonal for consecutive tiles
            if (i == 0) {
                humanTile = 0;
                computerTile = 0;
            }
            if (tileOfHuman(i,board_size-(i+1))){
                humanTile++;
            }
            if (tileOfComputer(i,board_size-(i+1))){
                computerTile++;
            }
            if ((humanTile == board_size && symbol == 'b')|| (computerTile == board_size && symbol == 'o')){
                return true;                       //if boardsize symbol consecutive return true
            }
        }
        return false;
    }

    private int emptyTiles() {                                  //helper method counts empty tiles and returns int
        int count = 0;
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                if (positionIsEmpty(i, j)) {
                    count++;                                    //loops through and counts if empty
                }
            }
        }
        return count;                                           //returns count
    }


    private boolean canSlide(char symbol) {                 //checks if a tile can be slid to an adjacent tile
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {         //checks all of the tiles around empty tiles
                if (gameBoard[i][j] == 'g') {
                    if (i > 0) {
                        if (gameBoard[i - 1][j] == symbol) {
                            return true;           //checks if player with turn can move to the empty tile
                        }
                    }
                    if (j > 0) {
                        if (gameBoard[i][j - 1] == symbol) {
                            return true;
                        }
                    }
                    if (i < board_size - 1) {
                        if (gameBoard[i + 1][j] == symbol) {
                            return true;               //checks if player with turn can move to the empty tile
                        }
                    }
                    if (j < board_size - 1) {
                        if (gameBoard[i][j + 1] == symbol) {
                            return true;
                        }
                    }
                    if (i > 0 && j > 0) {
                        if (gameBoard[i - 1][j - 1] == symbol) {
                            return true;               //checks if player with turn can move to the empty tile
                        }
                    }
                    if (i > 0 && j < board_size - 1) {
                        if (gameBoard[i - 1][j + 1] == symbol) {
                            return true;
                        }
                    }
                    if (j > 0 && i < board_size - 1) {
                        if (gameBoard[i + 1][j - 1] == symbol) {
                            return true;
                        }
                    }
                    if (i < board_size - 1 && j < board_size - 1) {
                        if (gameBoard[i + 1][j + 1] == symbol) {
                            return true;
                        }
                    }
                }
            }

        }
        return false;
    }

    public boolean isDraw (char symbol, int empty_positions) {  //checks if there is a draw, based on symbol/emptypos
        if (wins('o') || wins('b')) {
            return false;                               //if someone won return false
        }
        if (emptyTiles()>empty_positions) {
            return false;
        }
        if (emptyTiles()<=empty_positions && (!canSlide(symbol))){        //if no playable tiles and cant slide draw=T
            return true;
        }
        return false;                                                       //otherwise return false
    }


    public int evalBoard(char symbol, int empty_positions){     //returns a score for a board
        if (wins('o')){
            return 3;                                   //returns 3 if computer won
        }
        else if (wins('b')){
            return 0;                                       //returns 0 if human won
        }
        else if (isDraw(symbol,empty_positions)) {
            return 2;                                       //if draw returns 2
        }else {
            return 1;                                       //otherwise returns 1
        }
    }

}
