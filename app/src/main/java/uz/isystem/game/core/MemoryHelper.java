package uz.isystem.game.core;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import uz.isystem.game.R;

public class MemoryHelper {

    private static MemoryHelper helper;

    private final SharedPreferences preferences;

    public MemoryHelper(Context context) {
        preferences = context.getSharedPreferences("puzzle15", Context.MODE_PRIVATE);

    }

    public static MemoryHelper getHelper() {
        return helper;
    }

    public static void init(Context context) {
        if (helper == null) {
            helper = new MemoryHelper(context);
        }
    }

    public void saveData(UserData userData) {

        preferences.edit().putString("name_" + getLastIndex(), userData.getName()).apply();
        preferences.edit().putInt("time_" + getLastIndex(), userData.getTime()).apply();
        preferences.edit().putInt("step_" + getLastIndex(), userData.getStep()).apply();
        saveLastIndex();
    }

    private void saveLastIndex() {
        preferences.edit().putInt("index", getLastIndex() + 1).apply();
    }

    private int getLastIndex() {
        return preferences.getInt("index", 0);
    }

    public UserData getData(int index) {
        UserData userData = new UserData(
                preferences.getString("name_" + index, ""),
                preferences.getInt("time_" + index, 0),
                preferences.getInt("step_" + index, 0)
        );
        return userData;
    }

    public ArrayList<UserData> getLastResult() {
        ArrayList<UserData> list = new ArrayList<>();
        int n = getLastIndex();
        for (int i = 0; i < n; i++) {
            list.add(getData(i));
        }
        return list;
    }

    public void clearData() {
        preferences.edit().clear().apply();
    }

    public boolean getSoundState() {
        return preferences.getBoolean("sound_state", false);
    }

    public void setSoundState(boolean b) {
        preferences.edit().putBoolean("sound_state", b).apply();
    }

    public boolean getThemeState() {
        return preferences.getBoolean("theme_state", false);
    }

    public void setThemeState(boolean b) {
        preferences.edit().putBoolean("theme_state", b).apply();
    }

    public boolean getMusicState() {
        return preferences.getBoolean("music_state", false);
    }

    public void setMusicState(boolean isMusic) {
        preferences.edit().putBoolean("music_state", isMusic).apply();

    }

    public int getThemeOrder() {
        return preferences.getInt("theme_order", R.drawable.back_1);
    }

    public void setThemeOrder(int themeId) {
        preferences.edit().putInt("theme_order",
                themeId).apply();

    }


}


