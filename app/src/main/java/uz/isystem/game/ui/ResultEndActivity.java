package uz.isystem.game.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;

import uz.isystem.game.R;
import uz.isystem.game.core.MemoryHelper;
import uz.isystem.game.core.UserData;

public class ResultEndActivity extends BaseActivity {
    private LinearLayout rootGroup;
    private LinearLayout layout;
    private TextView exit, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_end2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rootGroup = findViewById(R.id.root_layout);
        layout = findViewById(R.id.root_layout);
        exit = findViewById(R.id.exit_btn);
        home = findViewById(R.id.home_btn);

        showResult();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Animatoo.animateZoom(ResultEndActivity.this);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.home_btn: {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ResultEndActivity.this);
                        builder.setTitle("Home...")
                                .setMessage("Do you want to move to home page?\nIf you click YES,the game will end!");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(ResultEndActivity.this, MainActivity.class);
                                startActivity(intent);
                                Animatoo.animateZoom(ResultEndActivity.this);
                                onBackPressed();
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
                }
            }
        });
    }

    private void showResult() {
        ArrayList<UserData> list = MemoryHelper.getHelper().getLastResult();
        for (UserData data : list) {
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            StringBuilder builder = new StringBuilder();
            builder.append("  Name: ");
            builder.append(data.getName());
            builder.append("  Step:");
            builder.append(data.getStep());
            builder.append("  Time:");
            builder.append(getTime(data.getTime()));

            textView.setTextSize(15);
            textView.setGravity(500);
            textView.setText(builder.toString());
            textView.setTextColor(Color.WHITE);
            textView.setLayoutParams(params);
            rootGroup.addView(textView);
        }
    }


    @Override
    public boolean shouldStopMedia() {
        return false;
    }

    private String getTime(int time) {
        int hour = time / 3600;
        int minute = time % 3600 / 60;
        int second = time % 60;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    @Override
    public void setThemeData() {
        layout.setBackgroundResource(MemoryHelper.getHelper().getThemeOrder());
    }
}