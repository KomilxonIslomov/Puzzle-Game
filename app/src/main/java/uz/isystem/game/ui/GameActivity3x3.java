package uz.isystem.game.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import uz.isystem.game.R;
import uz.isystem.game.core.MemoryHelper;

public class GameActivity3x3 extends BaseActivity {
    private final ArrayList<Integer> numbers = new ArrayList<>();
    private final Button[][] buttons = new Button[3][3];
    Timer timer;
    boolean playable;
    boolean sound;
    int a = 0;
    private TextView timeView;
    private TextView stepView;
    private RelativeLayout buttonGroup;
    private int emptyI = 2;
    private ImageView PlayButton;
    private ImageView RestatButton;
    private ImageView SoundButton;
    private ImageView ResultatButton;
    private TextView ExitButton;
    private TextView HomeButton;
    private int emptyJ = 2;
    private int time = 0;
    private LinearLayout layout;
    private int step = 0;
    private MediaPlayer mediaPlayer;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_3x3);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loadNumbers();
        loadViews();
        setDataToView();
        createTimer();
        setListener();

    }

    private void setListener() {
        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!playable) {
                    Pause();
                } else {
                    Play();
                }
            }


        });
        RestatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playable) {
                    Play();
                    restartGame();
                } else {
                    restartGame();
                }
            }
        });

        SoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sound) {
                    NoSound();
                } else {
                    Sound();

                }
                a++;
            }
        });
        ResultatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.resultat_btn: {
                        Intent intent = new Intent(GameActivity3x3.this, ResultEndActivity.class);
                        startActivity(intent);
                        Animatoo.animateZoom(GameActivity3x3.this);
                        break;
                    }
                }
            }
        });
        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.exit_btn: {
                        finish();
                        Animatoo.animateZoom(GameActivity3x3.this);
                    }
                    break;
                }
            }
        });
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.home_btn1: {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity3x3.this);
                        builder.setTitle("Home...")
                                .setMessage("Do you want to move to home page?\nIf you click YES,the game will end!");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity3x3.this, MainActivity.class);
                                startActivity(intent);
                                restartGame();
                                Animatoo.animateZoom(GameActivity3x3.this);
                                finish();
                                onBackPressed();
                                Animatoo.animateZoom(GameActivity3x3.this);
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

    private void Sound() {
        SoundButton.setImageResource(R.drawable.voleu_24);
        sound = !sound;
    }

    private void NoSound() {
        SoundButton.setImageResource(R.drawable.ic_baseline_volume_off_24);
        sound = !sound;
    }

    private void Pause() {
        timer.cancel();
        for (int i = 0; i < 9; i++) {
            buttons[i / 3][i % 3].setEnabled(false);
        }
        PlayButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        playable = !playable;
    }

    private void Play() {
        createTimer();
        for (int i = 0; i < 9; i++) {
            buttons[i / 3][i % 3].setEnabled(true);

        }
        PlayButton.setImageResource(R.drawable.pause);
        playable = !playable;
    }

    private void createTimer() {
        timer = null;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        setTimeView(time);
                    }
                });
            }
        }, 1000, 1000);
    }

    private void setDataToView() {
        setStep();
        for (int i = 0; i < 8; i++) {
            Button button = buttons[i / 3][i % 3];
            button.setText(String.valueOf(numbers.get(i)));
            button.setBackgroundResource(R.drawable.rang1_1);
        }
        buttons[emptyI][emptyJ].setBackgroundResource(R.drawable.buttonnol);
        buttons[emptyI][emptyJ].setText("");
    }

    private void loadViews() {
        layout = findViewById(R.id.root_layout);
        HomeButton = findViewById(R.id.home_btn1);
        ExitButton = findViewById(R.id.exit_btn);
        ResultatButton = findViewById(R.id.resultat_btn);
        SoundButton = findViewById(R.id.sound_btn);
        RestatButton = findViewById(R.id.restat_btn);
        sound = false;
        playable = false;
        PlayButton = findViewById(R.id.Pause_btn);
        timeView = findViewById(R.id.time_view);
        stepView = findViewById(R.id.step_view);
        buttonGroup = findViewById(R.id.btn_group);

        for (int i = 0; i < 9; i++) {
            buttons[i / 3][i % 3] = (Button) buttonGroup.getChildAt(i);
        }
    }

    public void loadNumbers() {
        numbers.clear();
        for (int i = 1; i < 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }

    public void buttonClick(View view) {
        Button button = (Button) view;
        String tag = button.getTag().toString();

        String[] indexes = tag.split(":");
        int i = Integer.parseInt(indexes[0]);
        int j = Integer.parseInt(indexes[1]);
        int deltaI = Math.abs(i - emptyI);
        int deltaJ = Math.abs(j - emptyJ);
        if ((deltaI == 1 && deltaJ == 0) || (deltaI == 0 && deltaJ == 1)) {
            buttons[emptyI][emptyJ].setText(button.getText());
            buttons[emptyI][emptyJ].setBackground(button.getBackground());
            button.setText("");
            button.setBackgroundResource(R.drawable.buttonnol);
            emptyI = i;
            emptyJ = j;
            step++;
            setStep();
            if (!sound) {
                startMusic();
            } else {
                stopMusic();
            }
            if (emptyI == 2 && emptyJ == 2) {
                checkToWin();
            }
        }

    }

    private void startMusic() {
        if (mediaPlayer != null) {
            stopMusic();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.click3);
        mediaPlayer.start();
    }

    private void stopMusic() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    private void setStep() {
        stepView.setText(String.format("%d", step));

    }

    private void setTimeView(int time) {
        int hour = time / 3600;
        int minut = time % 3600 / 60;
        int second = time % 60;
        timeView.setText(String.format("%02d:%02d:%02d", hour, minut, second));
    }

    private void checkToWin() {
        boolean isWin = true;
        for (int i = 0; i < 8; i++) {
            Button button = (Button) buttonGroup.getChildAt(i);
            if (!button.getText().toString().equals(i + 1 + "")) {
                isWin = false;
                break;
            }
        }
        if (isWin) {
            Animatoo.animateZoom(GameActivity3x3.this);
            showAlert();
            Animatoo.animateZoom(GameActivity3x3.this);

        }
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Win")
                .setMessage("Congratulation!\nYou won the game. Click restart to play again.");
        timer.cancel();
        builder.setPositiveButton("Show result", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                Intent intent = new Intent(GameActivity3x3.this, ResultActivity.class);
                intent.putExtra("time", time);
                intent.putExtra("step", step);
                startActivity(intent);
                Animatoo.animateZoom(GameActivity3x3.this);
                timer.cancel();
                restartGame();
                Animatoo.animateZoom(GameActivity3x3.this);


            }
        });
        builder.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                restartGame();
                Animatoo.animateZoom(GameActivity3x3.this);
            }


        });
        builder.create().show();
    }


    private void restartGame() {
        emptyI = 2;
        emptyJ = 2;
        timer.cancel();
        time = 0;
        step = 0;
        setTimeView(time);
        setStep();
        createTimer();
        loadNumbers();
        setDataToView();
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