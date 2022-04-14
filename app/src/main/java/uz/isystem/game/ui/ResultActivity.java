package uz.isystem.game.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import uz.isystem.game.R;
import uz.isystem.game.core.MemoryHelper;
import uz.isystem.game.core.UserData;

public class ResultActivity extends BaseActivity implements View.OnClickListener {
    String name;
    private TextView timeResult;
    private TextView stepResult;
    private ConstraintLayout layout;
    private EditText editText;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        editText = findViewById(R.id.name_input);
        layout = findViewById(R.id.root_layout);
        Intent intent = getIntent();
        int step = intent.getIntExtra("step", 0);
        int time = intent.getIntExtra("time", 0);
        stepResult = findViewById(R.id.step_result);
        timeResult = findViewById(R.id.time_result);
        stepResult.setText(String.format("Steps: %d", step));
        int hour = time / 3600;
        int minute = time / 60;
        int seconds = time % 60;

        timeResult.setText(String.format("Time: %02d:%02d:%02d", hour, minute, seconds));

        findViewById(R.id.home_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
                Animatoo.animateZoom(ResultActivity.this);
            }
        });
        findViewById(R.id.save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animatoo.animateZoom(ResultActivity.this);
                name = editText.getText().toString();
                MemoryHelper.getHelper().saveData(new UserData(
                        name, time, step
                ));
                Animatoo.animateZoom(ResultActivity.this);
                finish();
                Animatoo.animateZoom(ResultActivity.this);
            }
        });
        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Animatoo.animateZoom(ResultActivity.this);
            }
        });
        findViewById(R.id.exit_game_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Animatoo.animateZoom(ResultActivity.this);
            }
        });
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Home...")
                .setMessage("Do you want to move to home page?\nIf you click YES,the game will end!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Animatoo.animateZoom(ResultActivity.this);
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
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

    @Override
    public void onClick(View v) {

    }
}

