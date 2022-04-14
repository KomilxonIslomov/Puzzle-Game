package uz.isystem.game.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import uz.isystem.game.R;

public class OneActivity extends AppCompatActivity {

    private CountDownTimer timer;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        timer = new CountDownTimer(1_500, 1_000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(OneActivity.this, MainActivity.class);
                startActivity(intent);
                Animatoo.animateZoom(OneActivity.this);
                finish();
            }
        };
        timer.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }
}