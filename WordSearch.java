import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class WordSearch {
    private char[][]data;
    //the random seed used to produce this WordSearch
    private int seed;

    private char[][]answer;

    private boolean check = false;

    //a random Object to unify your random calls
    private Random randgen;

    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd;

    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded;

    public int getSeed(){
      return seed;
    }

    public void getWords(String filename){
      try {
        File f = new File(filename);
        Scanner in = new Scanner(f);
        wordsToAdd = new ArrayList<>();
        wordsAdded = new ArrayList<>();
        while (in.hasNext()) {
          wordsToAdd.add(in.nextLine().toUpperCase());
        }
      } catch (FileNotFoundException e) {
        System.out.println("File Not Found");
      }
    }

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
     public WordSearch(int rows, int cols, String fileName) {
       randgen = new Random();
       seed = randgen.nextInt() % 10000;
       randgen = new Random(seed);
       data = new char[rows][cols];
       answer = new char[rows][cols];
       clear();
       getWords(fileName);
       addAllWords();
       addLetters();
     }

     public WordSearch(int rows, int cols, String fileName, int randSeed) {
       seed = randSeed;
       randgen = new Random(seed);
       data = new char[rows][cols];
       answer = new char[rows][cols];
       clear();
       getWords(fileName);
       addAllWords();
       addLetters();
     }

     public WordSearch(int rows, int cols, String filename, int randSeed, String key){
       seed = randSeed;
       randgen = new Random(seed);
       data = new char[rows][cols];
       answer = new char[rows][cols];

       getWords(filename);
       if (!(key.equals("key"))){
         addLetters();
       } else{
         check = true;
       }
       clear();
       addAllWords();
     }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for (int i=0; i<data.length; i++){
        for (int j=0; j<data[i].length; j++){
          data[i][j] = '_';
          if (check){
            answer[i][j] = '_';
          }
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
     public String toString(){
      if (!check){
        String ans = "";
        for (int i = 0; i < data.length; i++) {
          ans += "|";
          for (int j = 0; j < data[i].length; j++) {
            ans += data[i][j];
            if (j != data[i].length - 1) {
              ans += " ";
            }
          }
          ans += "|\n";
        }
        return ans;
      } else {
        String ans = "";
        for (int i = 0; i < answer.length; i++) {
          ans += "|";
          for (int j = 0; j < answer[i].length; j++) {
            ans += answer[i][j];
            if (j != answer[i].length - 1) {
              ans += " ";
            }
          }
          ans += "|\n";
        }
        return ans;
      }
    }

    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added in the direction rowIncrement,colIncrement
     *Words must have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
     *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction
     *@return true when: the word is added successfully.
     *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
     *        OR there are overlapping letters that do not match
     */
     public boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
       if (rowIncrement == 0 && colIncrement == 0) {
          return false;
        }
        int colI = col;
        int rowI = row;
        for (int i=0; i<word.length(); i++){
          if (rowI < 0 || rowI >= data.length || colI < 0 || colI >= data[rowI].length) {
            return false;
          } else if (data[rowI][colI] != '_' && data[rowI][colI] != word.charAt(i)) {
            return false;
          }
          colI += colIncrement;
          rowI += rowIncrement;
        }

        colI = col;
        rowI = row;
        for (int i=0; i<word.length(); i++){
          data[rowI][colI] = word.charAt(i);
          answer[rowI][colI] = word.charAt(i);
          rowI += rowIncrement;
          colI += colIncrement;
        }
        wordsToAdd.remove(word);
        wordsAdded.add(word);
        return true;
     }

     /*[rowIncrement,colIncrement] examples:
      *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
      *[ 1,0] would add downwards because (row+1), with no col change
      *[ 0,-1] would add towards the left because (col - 1), with no row change
      */

      public void addAllWords(){
        for (int i=0; i<wordsToAdd.size(); i++){
          for (int j=0; j<1000; j++){
            int colI = randgen.nextInt(3) - 1;
            int rowI = randgen.nextInt(3) - 1;
            int row = randgen.nextInt(data.length);
            int col = randgen.nextInt(data[0].length);
            if (addWord(wordsToAdd.get(i), row, col, rowI, colI)){
              i++;
              j = 1000;
            }
          }
        }
      }

      public void addLetters(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i=0; i<data.length; i++){
          for (int j=0; j<data[i].length; j++){
            if (data[i][j] == '_'){
              data[i][j] = alphabet.charAt(Math.abs(randgen.nextInt())%26);
            }
          }
        }
      }

      public static void main(String[]args){
        try {
          if(args.length < 3) {
            System.out.println("You must enter three arguments. \n" +
                                "1. Number of rows \n" +
                                "2. Number of columns \n" +
                                "3. Filename of file containing words \n" +
                                "4. (optional) The seed of a puzzle \n" +
                                "5. (optional) The word 'key' if you would like to see the answer");
          } else if (Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[1]) <= 0){
            System.out.println("The numbers of rows and columns must be greater than zero");
          } else if (args.length == 3){
            WordSearch WSe2 = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2]);

            System.out.println("WordSearch WSe2 = new WordSearch("+args[0]+", "+args[1]+", "+args[2]+")");
            System.out.println("Seed: "+WSe2.getSeed());
            System.out.println(WSe2);
            // hopefully filled WordSearch
          } else if (args.length == 4){
            if ((Integer.parseInt(args[3]) > 10000)|| Integer.parseInt(args[3]) < 0) {
              System.out.println("Seed must be between 0 and 10000");
            } else {
              WordSearch WSe2 = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2], Integer.parseInt(args[3]));

              System.out.println("WordSearch WSe2 = new WordSearch("+args[0]+", "+args[1]+", "+args[2]+", "+args[3]+")");
              System.out.println("Seed: "+args[3]);
              System.out.println(WSe2);
              // hopefully filled WordSearch
            }
          } else if (args.length == 5){
            if ((Integer.parseInt(args[3]) > 10000)|| Integer.parseInt(args[3]) < 0) {
              System.out.println("Seed must be between 0 and 10000");
            } else {
              WordSearch WSe2 = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), args[4]);

              System.out.println("WordSearch WSe2 = new WordSearch("+args[0]+", "+args[1]+", "+args[2]+", "+args[3]+", "+args[4]+")");
              System.out.println("Seed: "+args[3]);
              System.out.println(WSe2);
              // hopefully filled WordSearch
            }
          }
        } catch (IllegalArgumentException f){
          System.out.println("You must enter three arguments. \n" +
                              "1. Number of rows \n" +
                              "2. Number of columns \n" +
                              "3. Filename of file containing words \n" +
                              "4. (optional) The seed of a puzzle \n" +
                              "5. (optional) The word 'key' if you would like to see the answer");
        }

      }
}
