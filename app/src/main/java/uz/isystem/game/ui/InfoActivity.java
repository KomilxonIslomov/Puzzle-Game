package uz.isystem.game.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import uz.isystem.game.R;
import uz.isystem.game.core.MemoryHelper;

public class InfoActivity extends BaseActivity {
    TextView instagram, telegram;
    private TextView exitButton;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        layout = findViewById(R.id.root_layout);
        exitButton = findViewById(R.id.exit_game_info);
        instagram = findViewById(R.id.instagram);
        telegram = findViewById(R.id.telegram);

        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://t.me/Islomov_Komilxon");
                Animatoo.animateZoom(InfoActivity.this);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.instagram.com/komilkhan_0104/?hl=en");
                Animatoo.animateZoom(InfoActivity.this);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Animatoo.animateZoom(InfoActivity.this);
                finish();
                Animatoo.animateZoom(InfoActivity.this);
            }
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));

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