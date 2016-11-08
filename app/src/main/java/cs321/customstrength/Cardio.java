package cs321.customstrength;

import java.util.ArrayList;

class Cardio extends Exercise {
  int time; //in minutes
  Intensity intensity;
  //fixedVolume==true && fixedIntensity==true
  Cardio (String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity, int time, Intensity intensity) {
    super(name, primaryMuscle, secondaryMuscles, equipmentUsed, level, fixedVolume, fixedIntensity);
    this.time=time;
    this.intensity=intensity;
  }
  //fixedVolume==true && fixedIntensity==false
  Cardio (String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity, int time) {
    super(name, primaryMuscle, secondaryMuscles, equipmentUsed, level, fixedVolume, fixedIntensity);
    this.time=time;
  }
  //fixedVolume==false && fixedIntensity==true
  Cardio (String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity, Intensity intensity) {
    super(name, primaryMuscle, secondaryMuscles, equipmentUsed, level, fixedVolume, fixedIntensity);
    this.intensity=intensity;
  }
  //fixedVolume==false && fixedIntensity==false
  Cardio (String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity) {
    super(name, primaryMuscle, secondaryMuscles, equipmentUsed, level, fixedVolume, fixedIntensity);
  }
}