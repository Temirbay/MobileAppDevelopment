package com.example.miras.picassogallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity {
    @BindView (R.id.textView) TextView textView;
    @BindView  (R.id.spinner) Spinner spinner;
    @BindView(R.id.picture) BootstrapCircleThumbnail picture;
    @BindView(R.id.rotate) BootstrapButton rotate;

    Integer position = 0;

    private static final String[] all_images = {
            "https://www.vanartgallery.bc.ca/the_exhibitions/images/picasso.jpg",
            "http://www.artribune.com/wp-content/uploads/2012/11/Pablo-Picasso-Nature-morte-aux-tulipes.jpg",
            "http://www.tate.org.uk/art/images/work/T/T05/T05010_10.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        spinner.setSelection(0);

        textView.setText(all_images[0]);
        Picasso.with(this)
                .load(all_images[0])
                .into(picture);
    }

    @OnClick(R.id.rotate)
    public void onClick (View view) {
        Picasso.with(this)
                .load(all_images[position])
                .into(picture);

        YoYo.with(Techniques.Wobble)
                .duration(700)
                .playOn(findViewById(R.id.rotate));
    }


    @OnItemSelected(R.id.spinner)
    public void onItemSelected(int pos) {
        position = pos;
        textView.setText(all_images[pos]);
    }
}
