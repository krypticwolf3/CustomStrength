import java.util.*;
class Program {
  String name;
  //number of differing weeks in the program, if program does not differ by week, this value will be 1
  int numWeeks=0;
  //use ArrayList to make editing easier 
  //when each week is added, incriment numWeeks
  ArrayList<Week> weeks;
  public Program(String name) {
    this.name=name;
  }
}