package uz.isystem.game.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import uz.isystem.game.R;
import uz.isystem.game.core.MemoryHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int a = 0;
    private TextView startButton, resultButton, settingsButton, infoButton;
    private LinearLayout layout;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        layout = findViewById(R.id.root_layout);
        infoButton = findViewById(R.id.info_btn);
        startButton = findViewById(R.id.start_game);
        resultButton = findViewById(R.id.result_game);
        settingsButton = findViewById(R.id.settings_game);
        loadTheme();
        infoButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
        resultButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Chiqish uchun ikki marta bosing!!!", Toast.LENGTH_SHORT).show();
        a++;
        if (a == 2) {
            super.onBackPressed();
            Animatoo.animateZoom(MainActivity.this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_game: {

                Intent intent = new Intent(MainActivity.this, ChoiseActivity.class);
                startActivity(intent);
                Animatoo.animateZoom(MainActivity.this);
                break;
            }
            case R.id.result_game: {
                Intent intent = new Intent(this, ResultEndActivity.class);
                startActivity(intent);
                Animatoo.animateZoom(MainActivity.this);
                break;
            }
            case R.id.settings_game: {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                Animatoo.animateZoom(MainActivity.this);
                break;
            }
            case R.id.info_btn: {
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                Animatoo.animateZoom(MainActivity.this);
                break;
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTheme();
    }

    private void loadTheme() {

        if (MemoryHelper.getHelper().getThemeState()) {
            layout.setBackgroundResource(MemoryHelper.getHelper().getThemeOrder());
        } else {
            layout.setBackgroundResource(MemoryHelper.getHelper().getThemeOrder());

        }

    }
}