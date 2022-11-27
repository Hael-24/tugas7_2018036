package com.example.pertemuan5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.example.pertemuan5.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    RecyclerView recylerView;
    String s1[], s2[],s3[];
    int images[] =
            {R.drawable.a1,R.drawable.b1,R.drawable.c1,R.drawable.d1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recylerView = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.makanan);
        s2 = getResources().getStringArray(R.array.deskripsi);
        s3 = getResources().getStringArray(R.array.star);
        MakananAdapter appAdapter = new MakananAdapter(this, s1, s2, s3, images);
        recylerView.setAdapter(appAdapter);
        recylerView.setLayoutManager(new
                LinearLayoutManager(this));
        LinearLayoutManager line = new LinearLayoutManager((this), LinearLayoutManager.HORIZONTAL, false);
        recylerView.setLayoutManager(line);

        @NonNull ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final OneTimeWorkRequest request = new
                OneTimeWorkRequest.Builder(MyWorker.class).build();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueueUniqueWork(
                        "Notifikasi", ExistingWorkPolicy.REPLACE, request);
            }
        });
    }
}
