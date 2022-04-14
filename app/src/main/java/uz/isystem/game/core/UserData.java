package uz.isystem.game.core;

public class UserData {

    private String name;
    private int step;
    private int time;


    public UserData(String name, int timeResult, int stepResult) {
        this.name = name;
        this.step = stepResult;
        this.time = timeResult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", step=" + step +
                ", time=" + time +
                '}';
    }
}
