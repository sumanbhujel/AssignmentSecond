package com.example.assignmentsecond;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignmentsecond.adapter.UserAdapter;
import com.example.assignmentsecond.model.User;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.rvUser);

        Intent intent = getIntent();
        final List<User> finaluserList = (List<User>) intent.getSerializableExtra("allusers");

        String[] sName = new String[finaluserList.size()];
        String[] sDob = new String[finaluserList.size()];
        String[] sGender = new String[finaluserList.size()];
        String[] sCountry = new String[finaluserList.size()];
        String[] sEmail = new String[finaluserList.size()];
        String[] sPhone = new String[finaluserList.size()];
        String[] sImage = new String[finaluserList.size()];

        int i = 0;
        for (User user : finaluserList) {
            sName[i] = user.getName();
            sGender[i] = user.getGender();
            sDob[i] = user.getDob();
            sCountry[i] = user.getCountry();
            sEmail[i] = user.getEmail();
            sPhone[i] = user.getPhone();
            sImage[i] = user.getImage();
            userList.add(new User(sName[i], sDob[i], sGender[i], sCountry[i], sEmail[i], sPhone[i], sImage[i]));
            i++;
        }

        UserAdapter adapter = new UserAdapter(userList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
