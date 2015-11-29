package com.test.nixoid.testcalendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.ViewHolder> {

    public static final int DAYS_IN_WEEK = 7;
    public static final String[] HEADERS = new String[]{"ПН","ВТ","СР","ЧТ","ПТ","СБ","ВС"};
    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    public Context context;

    private int daysOfweek[];
    private int firstDayOfWeek;

    public MonthAdapter(Context context, int[] daysOfweek, int firstDayOfWeek) {
        this.context = context;
        this.daysOfweek = daysOfweek;
        this.firstDayOfWeek = firstDayOfWeek;
    }

    @Override
    public int getItemViewType(int position) {
        return (position >= 0 && position < DAYS_IN_WEEK) ? VIEW_TYPE_HEADER : VIEW_TYPE_NORMAL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == VIEW_TYPE_HEADER) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.days, parent, false);
        }
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MonthAdapter.ViewHolder holder, int position) {
        if (position >= 0 && position < DAYS_IN_WEEK) {
            holder.days.setText(HEADERS[position]);
        } else if (position >= DAYS_IN_WEEK && position < DAYS_IN_WEEK + firstDayOfWeek){
            holder.days.setText("");
        } else {
            holder.days.setText(String.valueOf(daysOfweek[position - DAYS_IN_WEEK - firstDayOfWeek]));
        }
    }

    @Override
    public int getItemCount() {
        return daysOfweek.length + DAYS_IN_WEEK + firstDayOfWeek;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView days;
        public ViewHolder(View itemView) {
            super(itemView);
            days = (TextView) itemView.findViewById(R.id.days);
        }
    }
}

