package cs321.customstrength;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
class LoadExerciseData {
  protected static final HashMap<String,ExerciseData> PRELOADED_EXERCISES = LoadExerciseData.loadData();
  static HashMap<String,ExerciseData> loadData() {
    File file;
    Scanner sc;
    HashMap<String,ExerciseData> preloadedExercises = new HashMap<String,ExerciseData>();
    try {
      file=new File("ExerciseDataFinal2.txt");
      sc=new Scanner(file); // separated this line because it leads to a memory leak
      sc.useDelimiter("\t|\n"); // since you can't properly close everything if it's one line
      sc.nextLine(); // this gets rid of the first line
      while (sc.hasNext()) {
        String name = sc.next(); // adds the exercise name as upper case only because of search
        name = name.toUpperCase(); // I don't know how to make search not case sensitive
        String type = sc.next();
        String primaryMuscle = sc.next();
        String secondaryMuscles = sc.next();
        String equipmentUsed = sc.next();
        String mechanics = sc.next();
        String level = sc.next();
        String force = sc.next();
        ExerciseData ed = new ExerciseData(name, type, primaryMuscle, 
                       secondaryMuscles, equipmentUsed, mechanics, level, force);
          preloadedExercises.put(name, ed); // add to HashMap, key = name, data = value
      }
      if(sc != null) // added sc.close() to get rid of memory leak
        sc.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("Could not find ExerciseData file");
    }
   return preloadedExercises; 
  }
  
  // this will be used to search through Custom Exercises and preloaded
  static ArrayList<String> searchExercises(String s, HashMap<String,ExerciseData> hm){
    s = s.toUpperCase();
    ArrayList<String> exerciseLoad = new ArrayList<String>(); // could use a different data structure here
    Set<String> exerciseSet = hm.keySet();
    String exerciseArray[] = exerciseSet.toArray(new String[0]);
    // searches for name of each exercise
    for(int i = 0; i < exerciseArray.length; i++){
      if(exerciseArray[i].contains(s)){
        exerciseLoad.add(exerciseArray[i]);
      }
    }
    return exerciseLoad;
  }
  
  // this method belongs to a different class
  // need to talk about the constructors for each exercise here, probably best to keep the
  // constructors for each type the same (Strength only has one constructor); (Stretch only has one constructor) etc
  // and use if statements and set methods inside the constructor
  static void addPreloadedExercise(String s, HashMap<String,ExerciseData> hm, boolean fixedVolume, boolean fixedIntensity) throws IllegalArgumentException{
    s = s.toUpperCase(); // make everything uppercase so it searches, still need to figure out a way to make this work
    ExerciseData ed = hm.get(s);
    Exercise e;
    if(ed.getType().equals("Cardio")){
      e = new Cardio(ed.getName(), ed.getPrimaryMuscles(), 
                             ed.getSecondaryMuscles(), ed.getEquipment(), 
                             ed.getLevel(), fixedVolume, fixedIntensity, 
                             0, Intensity.LOW); // USER INPUTS, NEED TO FIGURE THIS OUT time and intensity
    }
    if(ed.getType().equals("Stretching")){
      e = new Stretch(ed.getName(), ed.getPrimaryMuscles(), 
                             ed.getSecondaryMuscles(), ed.getEquipment(), 
                             ed.getLevel(), fixedVolume, fixedIntensity,
                               false, 0); // USER INPUTS, NEED TO FIGURE THIS OUT dynamic and volume
    }
    if(ed.getType().equals("Strength") | ed.getType().equals("Powerlifting") |
       ed.getType().equals("Plyometrics") | ed.getType().equals("Strongman") |
       ed.getType().equals("OlympicWeightlifting")){
      e = new Strength (ed.getName(), ed.getPrimaryMuscles(), 
                             ed.getSecondaryMuscles(), ed.getEquipment(), 
                             ed.getLevel(), fixedVolume, fixedIntensity,
                            false, ed.getMechanics(), ed.getForce(), 0); // USER INPUTS, NEED TO FIGURE THIS OUT
                                                                          // differentSets and sets
    }
    else{
      throw new IllegalArgumentException("Wrong type on exercise");
    }
    System.out.println(e);
  }
    

  public static void main(String[] args) {
//    HashMap<String,ExerciseData> preloadedExercises = makeArrays.PRELOADED_EXERCISES;
//    File file;
//    Scanner sc;
//    try {
//      file=new File("ExerciseDataFinal2.txt");
//      sc=new Scanner(file); // separated this line because it leads to a memory leak
//      sc.useDelimiter("\t|\n"); // since you can't properly close everything if it's one line
//      sc.nextLine();
//      while(sc.hasNext()){
//        String word = sc.next();
//        if(preloadedExercises.containsKey(word)){
//          System.out.println(word);
//        }
//        sc.nextLine();
//      }
//       sc.close();
//    }
//    catch(Exception e){}
  }
}


