package com.example.subtitleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvSub;
    private SubtitlesAdapter subTitlesAdapter;
    private SubtitlesView srt;
    private List<SubTitles> subTitlesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSub = findViewById(R.id.rvSub);
        subTitlesAdapter = new SubtitlesAdapter(this);
        rvSub.setLayoutManager(new LinearLayoutManager(this));
        rvSub.setAdapter(subTitlesAdapter);
        srt = new SubtitlesView(this);
        srt.getSubtitleUri(Uri.parse(""));
        srt.setSubTitlesCallback(subTitles -> {
            subTitlesList.clear();
            subTitlesList.addAll(subTitles);
            subTitlesAdapter.setDataList(subTitlesList);
        });
    }
}