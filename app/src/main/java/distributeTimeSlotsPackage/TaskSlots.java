package distributeTimeSlotsPackage;

import com.example.a50001_1d_planner.Task;

public class TaskSlots implements Comparable<TaskSlots>{
    private TimeSlots timeSlots = null;
    private String nameTask;
    private int id;
    private boolean hasBreak = false;
    public TaskSlots(String name,int id){
        this.nameTask=name;
        this.id=id;
    }

    public void setTimeSlots(TimeSlots timeSlots) {
        this.timeSlots = timeSlots;
    }

    public TimeSlots getTimeSlots() {
        return timeSlots;
    }

    public int getId() {
        return id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getTaskTime(){
        if(timeSlots ==null){
            System.out.println("null time Slot");
            return "";
        }
        return timeSlots.toString();
    }
    @Override
    public String toString(){
        return timeSlots.getStartToEndTime()+" " + nameTask;
    }

    public String getStartTimeString(){
        return timeSlots.getStartTimeString(hasBreak);
    }

    public String getEndTimeString(){
        return timeSlots.getEndTimeString();
    }

    @Override
    public int compareTo(TaskSlots otherTaskSlot) {
        return Double.compare(this.timeSlots.getTime(), otherTaskSlot.getTimeSlots().getTime());
    }
}
