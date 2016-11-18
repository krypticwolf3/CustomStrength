package cs321.customstrength;

import java.util.ArrayList;

abstract class Exercise {
  String name;
  String primaryMuscle;
  ArrayList<String> secondaryMuscles;
  ArrayList<String> equipmentUsed;
  //beginner, intermediate, expert 
  String level;
  boolean fixedVolume;
  boolean fixedIntensity;
  Exercise (String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity) {
    this.name=name;
    this.primaryMuscle=primaryMuscle;
    this.secondaryMuscles=secondaryMuscles;
    this.equipmentUsed=equipmentUsed;
    this.level=level;
    this.fixedVolume=fixedVolume;
    this.fixedIntensity=fixedIntensity;
  }
  public String toString() {
    StringBuilder sb=new StringBuilder(name);
    //sb.append("\nPrimary Muscle Worked: ");
    //sb.append(primaryMuscle);
    //sb.append("\nSecondary Muscles Worked: ");
    //sb.append(secondaryMuscles);
    //sb.append("\nEquipment Used: ");
    //sb.append(equipmentUsed);
    //sb.append("\nDifficulty: ");
    //sb.append(level);
    return sb.toString();
  }
}
