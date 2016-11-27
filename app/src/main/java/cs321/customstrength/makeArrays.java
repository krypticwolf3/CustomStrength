package cs321.customstrength;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class makeArrays {
  static Exercise[] presetExercises=new Exercise[1067];
  static String[][] strings=new String[1068][8];
  static void arrayMaker() {
    File file=new File("ExerciseDataFinal.txt");
    try {
      Scanner sc=new Scanner(file).useDelimiter("\t|\n");
      int i=0;
      while (sc.hasNext()) {
        for (int j=0; j<8; j++) {
          strings[i][j]=sc.next();
        }
        i++;
      }
    }
    catch (FileNotFoundException e) {
      System.out.println("I messed Up");
    }
  }
  public static void main(String[] args) {
    makeArrays.arrayMaker();
  }
}
    
    
  