package com.example.hxplay.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hxplay.R;
import com.example.hxplay.adapter.PersonTypeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: henry.xue
 * @date: 2024-05-24
 */
public class PersonTabFragment extends Fragment {

    private String content;
    private RecyclerView rv;
    private List<String> typeList = new ArrayList<>();

    public PersonTabFragment(String content) {
        this.content = content;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_person_tab,
                container, false);
        rv = view.findViewById(R.id.rv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int num = new Random().nextInt(30);
        typeList.clear();
        for (int i = 0; i < num; i++) {
            typeList.add(content + i);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        PersonTypeAdapter personTypeAdapter = new PersonTypeAdapter(R.layout.item_person_type_rv, typeList);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(personTypeAdapter);
    }
}