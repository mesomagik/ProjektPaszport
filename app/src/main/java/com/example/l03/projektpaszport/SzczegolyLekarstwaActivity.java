package com.example.l03.projektpaszport;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tomek on 2016-11-17.
 */

public class SzczegolyLekarstwaActivity extends AppCompatActivity {
    private TextView tvIlosc;
    private TextView tvGodzina;
    private TextView tvSposobUzycia;
    private ImageView ivZdjecie;
    private Lekarstwo lekarstwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szczegoly_lekarstwa);
        tvIlosc = (TextView) findViewById(R.id.tvIlosc);
        tvGodzina = (TextView) findViewById(R.id.tvGodzina);
        tvSposobUzycia = (TextView) findViewById(R.id.tvSposobUzycia);
        ivZdjecie = (ImageView) findViewById(R.id.ivZdjecie);

        Intent intent = getIntent();
        lekarstwo = (Lekarstwo) intent.getSerializableExtra("lekarstwo");

        Bitmap bitmap = BitmapFactory.decodeFile(lekarstwo.getZdjecie());
        ivZdjecie.setImageBitmap(bitmap);

        tvIlosc.setText(lekarstwo.getIlosc());
        tvGodzina.setText(lekarstwo.getGodzina());
        tvSposobUzycia.setText(lekarstwo.getSposob_zazycia());

    }
}
