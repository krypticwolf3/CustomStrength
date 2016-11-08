import java.util.*;
class Stretch extends Exercise {
  boolean dynamic; //true=dynamic false=static
  int volume; //if dynamic==true, measure in reps, else measure in time(seconds)
  Intensity intensity;
  //fixedVolume==true && fixedIntensity==true
  Stretch(String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity, boolean dynamic, int volume, Intensity intensity) {
    super(name, primaryMuscle, secondaryMuscles, equipmentUsed, level, fixedVolume, fixedIntensity);
    this.dynamic=dynamic;
    this.volume=volume;
    this.intensity=intensity;
  }
  //fixedVolume==true && fixedIntensity==false
  Stretch(String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity, boolean dynamic, int volume) {
    super(name, primaryMuscle, secondaryMuscles, equipmentUsed, level, fixedVolume, fixedIntensity);
    this.dynamic=dynamic;
    this.volume=volume;
  }
  //fixedVolume==false && fixedIntensity==true
  Stretch(String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity, boolean dynamic, Intensity intensity) {
    super(name, primaryMuscle, secondaryMuscles, equipmentUsed, level, fixedVolume, fixedIntensity);
    this.dynamic=dynamic;
    this.intensity=intensity;
  }
  //fixedVolume==false && fixedIntensity==false
  Stretch(String name, String primaryMuscle, ArrayList<String> secondaryMuscles, String equipmentUsed, String level, boolean fixedVolume, boolean fixedIntensity, boolean dynamic) {
    super(name, primaryMuscle, secondaryMuscles, equipmentUsed, level, fixedVolume, fixedIntensity);
  }
}