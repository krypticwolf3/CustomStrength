package cs321.customstrength;

import java.util.ArrayList;

class Program {
  String name;
  //number of differing weeks in the program, if program does not differ by week, this value will be 1
  int numWeeks=0;
  //use ArrayList to make editing easier 
  //when each week is added, incriment numWeeks
  ArrayList<Week> weeks;
  public Program(String name) {
    this.name=name;
    weeks = new ArrayList<Week>();
  }
  public String toString() {
      return name;
  }
}