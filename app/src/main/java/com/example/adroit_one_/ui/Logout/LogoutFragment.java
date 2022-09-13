package com.example.adroit_one_.ui.Logout;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adroit_one_.LoginActivity;
import com.example.adroit_one_.NavigationDrowerUsingForFragmentsActivity;
import com.example.adroit_one_.R;

public class LogoutFragment extends Fragment {
    private TextView Logout;
    private TextView Cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        Logout = view.findViewById(R.id.tvLogoutId);
        Cancel = view.findViewById(R.id.tvCancelId);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NavigationDrowerUsingForFragmentsActivity.class);
                startActivity(intent);
            }
        });

        return  view;
    }
}