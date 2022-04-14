package uz.isystem.game.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import uz.isystem.game.R;
import uz.isystem.game.core.MemoryHelper;

public class ChoiseActivity extends BaseActivity implements View.OnClickListener {
    private TextView Game3x3, Game4x4, Game5x5, Exit, HomeChoise;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Game3x3 = findViewById(R.id.game3x3);
        HomeChoise = findViewById(R.id.home_btn);
        Game4x4 = findViewById(R.id.game4x4);
        Game5x5 = findViewById(R.id.game5x5);
        Exit = findViewById(R.id.exit_game_1);
        layout = findViewById(R.id.root_layout);

        Game3x3.setOnClickListener(this);
        Game4x4.setOnClickListener(this);
        Game5x5.setOnClickListener(this);
        Exit.setOnClickListener(this);
        HomeChoise.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.game3x3: {

                Intent intent = new Intent(ChoiseActivity.this, GameActivity3x3.class);
                startActivity(intent);
                Animatoo.animateZoom(ChoiseActivity.this);
                break;
            }
            case R.id.game4x4: {
                Intent intent = new Intent(ChoiseActivity.this, GameActivity4x4.class);
                startActivity(intent);
                Animatoo.animateZoom(ChoiseActivity.this);
                break;
            }
            case R.id.game5x5: {
                Intent intent = new Intent(ChoiseActivity.this, GameActivity5x5.class);
                startActivity(intent);
                Animatoo.animateZoom(ChoiseActivity.this);
                break;
            }
            case R.id.exit_game_1: {
                finish();
                Animatoo.animateZoom(ChoiseActivity.this);
                break;
            }
            case R.id.home_btn: {
                showAlert();
                Animatoo.animateZoom(ChoiseActivity.this);
                break;
            }
        }
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Home...")
                .setMessage("Do you want to move to home page?\nIf you click YES,the game will end!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onBackPressed();
                Animatoo.animateZoom(ChoiseActivity.this);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    @Override
    public boolean shouldStopMedia() {
        return false;
    }

    @Override
    public void setThemeData() {
        layout.setBackgroundResource(MemoryHelper.getHelper().getThemeOrder());
    }
}