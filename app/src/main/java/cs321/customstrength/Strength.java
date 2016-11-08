import java.util.*;
class Strength extends Exercise {
  String mechanics; //Compound, isolation, N/A
  String force; //push, pull, static, N/A 
  //true if each set uses different weight or reps
  boolean differentSets;
  int sets;
  //use int arrys to cover the case where sets are different, if differentSets is false, the array length will be 1
  int[] reps;
  int[] weight;
  //for use when fixedIntensity==false || fixedVolume==false
  int[] repsOrWeight;
  //fixedIntensity==true && fixedVolume==true
  Strength (String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity, boolean differentSets, String mechanics, String force, int sets, int[] reps, int[] weight) {
    super(name, primaryMuscle, secondaryMuscles, equipmentUsed, level, fixedVolume, fixedIntensity);
    this.mechanics=mechanics;
    this.force=force;
    this.differentSets=differentSets;
    this.sets=sets;
    this.reps=reps;
    this.weight=weight;
  }
  //fixedIntensity==false || fixedVolume==false
  Strength (String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity, boolean differentSets, String mechanics, String force, int sets, int[] repsOrWeight) {
    super(name, primaryMuscle, secondaryMuscles, equipmentUsed, level, fixedVolume, fixedIntensity);
    this.mechanics=mechanics;
    this.force=force;
    this.differentSets=differentSets;
    this.sets=sets;
    if (this.fixedIntensity==true) {
      this.repsOrWeight=weight;
      reps=new int[sets];
    }
    else { 
      this.repsOrWeight=reps;
      weight=new int[sets];
    }
  }
  //fixedIntensity==false && fixedVolume==false
  Strength (String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity, boolean differentSets, String mechanics, String force, int sets) {
    super(name, primaryMuscle, secondaryMuscles, equipmentUsed, level, fixedVolume, fixedIntensity);
    this.mechanics=mechanics;
    this.force=force;
    this.differentSets=differentSets;
    this.sets=sets;
    this.reps=new int[sets];
    this.weight=new int[sets];
  }
  public static void main (String[] args) {
    ArrayList<String> al=new ArrayList<String>();
    al.add("Calves");
    al.add("Glutes");
    al.add("Lower Back");
    Strength st=new Strength("Barbell Deadlift", "Hamstrings", al, "Barbell", "Intermediate", false, false, false, "Compound", "Pull", 3);
    System.out.println(st);
  }
}