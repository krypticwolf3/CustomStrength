package cs321.customstrength;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
class LoadExerciseData {
  protected static final HashMap<String,ExerciseData> PRELOADED_EXERCISES = LoadExerciseData.loadPreloadedData();
  protected static HashMap<String,ExerciseData> CUSTOM_EXERCISES = LoadExerciseData.loadCustomData();
  static HashMap<String,ExerciseData> loadPreloadedData() {
    File file;
    Scanner sc;
    HashMap<String,ExerciseData> preloadedExercises = new HashMap<String,ExerciseData>();
    try {
      file=new File("ExerciseDataFinal.txt");
      sc=new Scanner(file); // separated this line because it leads to a memory leak
      sc.useDelimiter("\t|\n"); // since you can't properly close everything if it's one line
      sc.nextLine(); // this gets rid of the first header line
      while (sc.hasNext()) {
        // read all of the values, there should be 8 items
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
     sc.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("Could not find ExerciseDataFinal.txt file");
    }
   return preloadedExercises; 
  }
    static HashMap<String,ExerciseData> loadCustomData() {
    File file;
    Scanner sc;
    HashMap<String,ExerciseData> customExercises = new HashMap<String,ExerciseData>();
    try {
      file=new File("CustomExerciseData.txt");
      sc=new Scanner(file); // separated this line because it leads to a memory leak
      sc.useDelimiter("\t|\n"); // since you can't properly close everything if it's one line
      while (sc.hasNext()) {
        // read all of the values, there should be 8 items
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
        customExercises.put(name, ed); // add to HashMap, key = name, data = value
      }
     sc.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("Could not find ExerciseDataFinal.txt file");
    }
   return customExercises; 
  }
  
  // this will be used to search through Custom Exercises and preloaded
  // returns a sorted list of the names
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
    Collections.sort(exerciseLoad);
    return exerciseLoad;
  }
  
  // return a sorted list of the exercise's names
  static List<String> displayExercises(HashMap<String,ExerciseData> hm){
    Set<String> exerciseSet = hm.keySet();
    List<String> exerciseList = new ArrayList<String>(exerciseSet);
    Collections.sort(exerciseList);
    return exerciseList;
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
                             ed.getMechanics(), ed.getLevel(), ed.getForce(),
                             0, Intensity.LOW); // USER INPUTS, NEED TO FIGURE THIS OUT time and intensity
    }
    if(ed.getType().equals("Stretching")){
      e = new Stretch(ed.getName(), ed.getPrimaryMuscles(), 
                             ed.getSecondaryMuscles(), ed.getEquipment(), 
                             ed.getMechanics(), ed.getLevel(), ed.getForce(),
                             fixedVolume, fixedIntensity,
                             1, new int[]{0}, new Intensity[]{Intensity.LOW}); // USER INPUTS, NEED TO FIGURE THIS OUT dynamic and volume
    }
    if(ed.getType().equals("Strength") | ed.getType().equals("Powerlifting") |
       ed.getType().equals("Plyometrics") | ed.getType().equals("Strongman") |
       ed.getType().equals("OlympicWeightlifting")){
      e = new Strength(ed.getName(), ed.getPrimaryMuscles(), 
                             ed.getSecondaryMuscles(), ed.getEquipment(), 
                             ed.getMechanics(), ed.getLevel(), ed.getForce(),
                             fixedVolume, fixedIntensity,
                             1, new int[]{0}, new int[]{0}); // USER INPUTS, NEED TO FIGURE THIS OUT
                                                                          // differentSets and sets
    }
    else{
      throw new IllegalArgumentException("Wrong type on exercise");
    }
    System.out.println(e);
  }
  
  // create a CustomExercise if the name does not already exist in the CustomExercise hashmap
  static void createCustomExercise(ExerciseData ed){
    if(LoadExerciseData.CUSTOM_EXERCISES.containsKey(ed.getName().toUpperCase())){
      throw new IllegalArgumentException("This Custom Exercise already exists, please use a different name");
    }
    try {
      FileWriter fw = new FileWriter("CustomExerciseData.txt",true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      pw.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", ed.getName().toUpperCase(), ed.getType(), ed.getPrimaryMuscles(), 
                arrayListToQuotes(ed.getSecondaryMuscles()), arrayListToQuotes(ed.getEquipment()), 
                ed.getMechanics(), ed.getLevel(), ed.getForce());
      pw.close();
      updateCustomExercises();
    }
    catch (IOException e) {
      System.out.println("Could not find CustomExerciseData.txt file");
    }
  }
  
  static void removeCustomExercise(String customExerciseName){
    String s = customExerciseName.toUpperCase();
    if(!(LoadExerciseData.CUSTOM_EXERCISES.containsKey(s))){
      return;
    }
    LoadExerciseData.CUSTOM_EXERCISES.remove(s);
    ExerciseData[] eds = LoadExerciseData.CUSTOM_EXERCISES.values().toArray(new ExerciseData[0]);
    try{
      PrintWriter pw = new PrintWriter("CustomExerciseData.txt", "UTF-8");
      for(int i = 0; i < eds.length; i++){
        ExerciseData ed = eds[i]; 
        pw.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", ed.getName(), ed.getType(), ed.getPrimaryMuscles(), 
                  arrayListToQuotes(ed.getSecondaryMuscles()), arrayListToQuotes(ed.getEquipment()), 
                  ed.getMechanics(), ed.getLevel(), ed.getForce());
      }
      pw.close();
      updateCustomExercises();
    }
    catch(IOException e){
      System.out.println("CustomExerciseData.txt file was not found");
    }
  }
  
  static void updateCustomExercises(){
    CUSTOM_EXERCISES = LoadExerciseData.loadCustomData();
  }
  
  // Helper method
  // Used in create and removeCustomExercise to put it in the original format
  public static String arrayListToQuotes(ArrayList<String> al){
    String s = "";
    for(int i = 0; i < al.size(); i++){
      s += al.get(i);
      if(i != al.size()-1){
        s += ",";
      }
    }
    return s;
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

