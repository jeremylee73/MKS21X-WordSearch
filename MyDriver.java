public class MyDriver {

  public static void main(String[] args) {
    if (args.length == 4){
      WordSearch WSe2 = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),"words.txt", Integer.parseInt(args[2]), args[3]);

      System.out.println("WordSearch WSe2 = new WordSearch("+args[0]+", "+args[1]+", words.txt, "+args[2]+", "+args[3]+")");
      System.out.println("Seed: "+args[2]);
      for (int i=0; i<100000000; i++){
        WSe2.addAllWords();
      }
      System.out.println(WSe2);
      // hopefully filled WordSearch
    } else if (args.length == 3){
      WordSearch WSe2 = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),"words.txt", Integer.parseInt(args[2]));

      System.out.println("WordSearch WSe2 = new WordSearch("+args[0]+", "+args[1]+", words.txt, "+args[2]+")");
      System.out.println("Seed: "+args[2]);
      for (int i=0; i<100000000; i++){
        WSe2.addAllWords();
      }
      System.out.println(WSe2);
      // hopefully filled WordSearch
    } else if(args.length == 2) {
      WordSearch WSe2 = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),"words.txt");

      System.out.println("WordSearch WSe2 = new WordSearch("+args[0]+", "+args[1]+", words.txt)");
      System.out.println("Seed: "+WSe2.getSeed());
      for (int i=0; i<100000000; i++){
        WSe2.addAllWords();
      }
      System.out.println(WSe2);
      // hopefully filled WordSearch

    } else if(args.length == 1) {
      System.out.println("MISSING INPUT:\nDriver_Test class needs terminal line args:");
      System.out.println("- int rows [FOUND]\n- int cols [NOT FOUND]");

    } else if(args.length == 0) {
      System.out.println("MISSING INPUT:\nDriver_Test class needs terminal line args:");
      System.out.println("- int rows [NOT FOUND]\n- int cols [NOT FOUND]");

    } else {
      System.out.println("INVALID INPUT:\nDriver_Test class only needs 2 int terminal line args");
    }

    /*
      A spectre is haunting Europe — the spectre of communism. All the powers of old Europe have entered into a holy alliance to exorcise this spectre: Pope and Tsar, Metternich and Guizot, French Radicals and German police-spies.
      Where is the party in opposition that has not been decried as communistic by its opponents in power? Where is the opposition that has not hurled back the branding reproach of communism, against the more advanced opposition parties, as well as against its reactionary adversaries?

      Two things result from this fact:
        I. Communism is already acknowledged by all European powers to be itself a power.
        II. It is high time that Communists should openly, in the face of the whole world, publish their views, their aims, their tendencies, and meet this nursery tale of the Spectre of Communism with a manifesto of the party itself.

      To this end, Communists of various nationalities have assembled in London and sketched the following manifesto, to be published in the English, French, German, Italian, Flemish and Danish languages.
    */


  }

}
