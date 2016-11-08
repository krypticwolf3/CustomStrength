import java.util.*;
class Day {
  //optional
  String name;
  int numExercises=0;
  ArrayList<Exercise> exercises;
  //if there is no name, name them by number (i.e. "Day 3")
  //when each exercise is added, incriment numExercises
  Day (String name) {
    this.name=name;
  }
}