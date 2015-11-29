package com.test.nixoid.testcalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

public class MonthFragment extends Fragment {

    private static String[] months;

    static final String ARG_SECTION_NUMBER = "check";
    private MonthAdapter adapter;
    int pageNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARG_SECTION_NUMBER);
        months = getResources().getStringArray(R.array.months);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, pageNumber);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int[] dayz = new int[days];
        for (int i = 0; i < days; i++) {
            dayz[i] = i+1;
        }
        int firstDayOfWeek = getFirsDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleMonth);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new MonthAdapter(getContext(), dayz, firstDayOfWeek);
        recyclerView.setAdapter(adapter);

        TextView textView = (TextView) view.findViewById(R.id.section_label);
        textView.setText(months[pageNumber]);

        return view;
    }

    private int getFirsDayOfWeek(int dayNumber) {
        switch (dayNumber) {
            case 1:
                return 6;
            case 2:
                return 0;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 4;
            case 7:
                return 5;
            default:
                return 0;
        }
    }
}
