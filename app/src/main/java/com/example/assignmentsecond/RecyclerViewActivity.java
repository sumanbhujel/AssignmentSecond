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

        recyclerView =findViewById(R.id.rvUser);

        Intent intent = getIntent();
        final List<User> finaluserList = (List<User>) intent.getSerializableExtra("allusers");

        String[] userNames = new String[finaluserList.size()];
        String[] userDob = new String[finaluserList.size()];
        String[] userGender = new String[finaluserList.size()];
        String[] userCountry = new String[finaluserList.size()];
        String[] userEmail = new String[finaluserList.size()];
        String[] userPhone = new String[finaluserList.size()];
        String[] userAddress = new String[finaluserList.size()];
        //String[] userimage = new String[finaluserList.size()];

        int i = 0;
        for (User user : finaluserList) {
            userNames[i] = user.getName();
            userGender[i] = user.getGender();
            userDob[i] = user.getDob();
            userCountry[i] = user.getCountry();
            userEmail[i] = user.getEmail();
            userPhone[i] = user.getPhone();
            //userimage[i] = user.getImage();
            userList.add(new User(userNames[i], userDob[i], userGender[i], userCountry[i], userEmail[i], userPhone[i]));
            i++;
        }

        UserAdapter adapter = new UserAdapter(userList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
