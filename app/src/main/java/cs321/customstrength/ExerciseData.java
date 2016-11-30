package cs321.customstrength;

import java.util.ArrayList;
class ExerciseData{
    String name;
    String type;
    String primaryMuscle;
    String mechanics;
    String level;
    String force;
    ArrayList<String> secondaryMuscles;
    ArrayList<String> equipmentUsed;
    public ExerciseData(String name, String type, String primaryMuscle, String secondaryMuscles,
                        String equipmentUsed, String mechanics, String level, String force){
        this.name = name;
        this.type = type;
        this.primaryMuscle = primaryMuscle;
        this.secondaryMuscles = extractStringsFromQuotes(secondaryMuscles);
        this.equipmentUsed = extractStringsFromQuotes(equipmentUsed);
        this.mechanics = mechanics;
        this.level = level;
        this.force = force;
    }
    public ArrayList<String> extractStringsFromQuotes(String stringORquote){
        ArrayList<String> returnArray = new ArrayList<String>();
        if(stringORquote.charAt(0) == '"'){
            String strings[] = stringORquote.split(",");
            String firstString = strings[0];
            String lastString = strings[strings.length-1];
            strings[0] = firstString.substring(1,firstString.length());
            strings[strings.length-1] = lastString.substring(0,lastString.length()-1);
            for(int i = 0; i < strings.length; i++){
                returnArray.add(strings[i]);
            }
        }
        else if(this.primaryMuscle.equals(stringORquote)){
            returnArray.add("N/A");
        }
        else{
            returnArray.add(stringORquote);
        }
        return returnArray;
    }

    public String getName(){ return this.name; }
    public String getType(){ return this.type; }
    public String getPrimaryMuscles(){ return this.primaryMuscle; }
    public ArrayList<String> getSecondaryMuscles(){ return this.secondaryMuscles; }
    public ArrayList<String> getEquipment(){ return this.equipmentUsed; }
    public String getMechanics(){ return this.mechanics; }
    public String getLevel(){ return this.level; }
    public String getForce(){ return this.force; }
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        sb.append("\nPrimary Muscle Worked: ");
        sb.append(primaryMuscle);
        sb.append("\nSecondary Muscles Worked: ");
        sb.append(secondaryMuscles);
        sb.append("\nEquipment Used: ");
        sb.append(equipmentUsed);
        sb.append("\nDifficulty: ");
        sb.append(level);
        return sb.toString();
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof ExerciseData){
            ExerciseData ed = (ExerciseData)o;
            return ed.name.equals(this.name) && ed.type.equals(this.type);
        }
        return false;

    }
    @Override public int hashCode(){
        int result = 31;
        result = 17 * result + type.hashCode();
        result = 17 * result + name.hashCode();
        return result;
    }
}