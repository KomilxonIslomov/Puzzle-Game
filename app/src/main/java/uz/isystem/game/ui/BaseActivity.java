package uz.isystem.game.ui;

import androidx.appcompat.app.AppCompatActivity;

import uz.isystem.game.core.App;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

    @Override
    protected void onStop() {
        if (shouldStopMedia()) {
            App.stopMedia();
        }

        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setThemeData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public abstract boolean shouldStopMedia();

    public abstract void setThemeData();
}


