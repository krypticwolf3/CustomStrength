import java.util.*;
class Week {
  //optional
  String name;
  int numDays=0;
  //use an ArrayList to help with editing
  ArrayList<Day> days;
  //if there is no name, feed the week number into name (i.e. "Week 2")
  //when each day is added, incriment dumDays
  Week (String name) {
    this.name=name;
  }
}