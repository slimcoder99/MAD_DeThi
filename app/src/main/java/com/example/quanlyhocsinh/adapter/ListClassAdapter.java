package com.example.quanlyhocsinh.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlyhocsinh.R;
import com.example.quanlyhocsinh.model.Class;

import org.w3c.dom.Text;

import java.util.List;

public class ListClassAdapter extends BaseAdapter {

    private Activity activity;
    private List<Class> listClass;

    public ListClassAdapter(Activity activity, List<Class> listClass) {
        this.activity = activity;
        this.listClass = listClass;
    }

    @Override
    public int getCount() {
        return listClass.size();
    }

    @Override
    public Object getItem(int position) {
        return listClass.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Class newClass = listClass.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.class_item, null);

        TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);

        tvId.setText("" + newClass.getId());
        tvName.setText(newClass.getName());
        tvDescription.setText(newClass.getDescription());

        return  convertView;
    }
}
