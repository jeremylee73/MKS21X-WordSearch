public class WordSearch {
    private char[][]data;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for (int i=0; i<data.length; i++){
        for (int j=0; j<data[i].length; j++){
          data[i][j] = '_';
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String ans = "";
      for (int i=0; i<data.length; i++){
        for (int j=0; j<data[i].length; j++){
          if (j != data[i].length-1){
            ans += data[i][j] + " ";
          } else {
            ans += data[i][j];
          }
        }
        ans += "/n";
      }
      return ans;
    }


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
    public boolean addWordHorizontal(String word,int row, int col){
      for (int i=0; i<word.length(); i++){
        if ((col + i) >= data[row].length || data[row][col+i] != '_'){
          return false;
        }
      }

      for (int i=0; i<word.length(); i++){
        data[row][col+i] = word.charAt(i);
      }
      return true;
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col){
      for (int i=0; i<word.length(); i++){
        if ((row + i) >= data.length || data[row+i][col] != '_'){
          return false;
        }
      }

      for (int i=0; i<word.length(); i++){
        data[row+i][col] = word.charAt(i);
      }
      return true;
    }
}