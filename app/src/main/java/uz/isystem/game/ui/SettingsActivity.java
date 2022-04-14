package uz.isystem.game.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import uz.isystem.game.R;
import uz.isystem.game.core.App;
import uz.isystem.game.core.MemoryHelper;

public class SettingsActivity extends BaseActivity {
    private TextView exitBTN, infoBTN;
    private Switch soundSwitch, musicSwitch, nightSwitch;
    private Button clearButton;
    private RadioButton theme1, theme2, theme3;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        soundSwitch = findViewById(R.id.sound_switch);
        clearButton = findViewById(R.id.clear_btn);
        layout = findViewById(R.id.root_layout);
        exitBTN = findViewById(R.id.exit_game);
        infoBTN = findViewById(R.id.info2);
        musicSwitch = findViewById(R.id.music_switch);
        theme1 = findViewById(R.id.theme1);
        theme2 = findViewById(R.id.theme2);
        theme3 = findViewById(R.id.theme3);
        setLastData();

        setListener();


    }

    private void setListener() {
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
                Animatoo.animateZoom(SettingsActivity.this);

            }
        });
        exitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                Animatoo.animateZoom(SettingsActivity.this);
            }
        });
        infoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, InfoActivity.class);
                startActivity(intent);
                Animatoo.animateZoom(SettingsActivity.this);

                finish();
            }
        });

        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MemoryHelper.getHelper().setSoundState(b);
                setLastData();
            }
        });

        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MemoryHelper.getHelper().setMusicState(b);
                if (b) {
                    App.startMedia();
                } else {
                    App.stopMedia();
                }
                setLastData();
            }
        });
        theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setThemeOrder(R.drawable.back_1);
                setThemeData();
            }
        });
        theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setThemeOrder(R.drawable.back_2);
                setThemeData();
            }
        });
        theme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setThemeOrder(R.drawable.back_3);
                setThemeData();
            }
        });
    }

    private void setLastData() {
        boolean isSound = MemoryHelper.getHelper().getSoundState();
        soundSwitch.setChecked(isSound);
        if (isSound) {
            soundSwitch.setText(getString(R.string.sound_on));
        } else {
            soundSwitch.setText(getString(R.string.sound_off));
        }
        boolean isMusic = MemoryHelper.getHelper().getMusicState();
        musicSwitch.setChecked(isMusic);
        if (isMusic) {
            musicSwitch.setText(getString(R.string.music_on));
        } else {
            musicSwitch.setText(getString(R.string.music_off));
        }
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Stop...")
                .setMessage("Are you sure to delete all the data?!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                App.stopMedia();
                setLastData();
                MemoryHelper.getHelper().clearData();
                onBackPressed();
                Animatoo.animateZoom(SettingsActivity.this);
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
}