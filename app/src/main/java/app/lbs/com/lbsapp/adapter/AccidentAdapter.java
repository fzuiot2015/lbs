package app.lbs.com.lbsapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import app.lbs.com.lbsapp.R;
import app.lbs.com.lbsapp.bean.Accident;

public class AccidentAdapter extends ArrayAdapter<Accident> {

    private int resourceId;

    public AccidentAdapter(@NonNull Context context, int resource, List<Accident> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Accident accident = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }

        TextView place = view.findViewById(R.id.accident_place);
        TextView time = view.findViewById(R.id.accident_time);
        TextView detail = view.findViewById(R.id.accident_detail);

        if (accident != null) {
            place.setText(accident.getAddress());
            time.setText(accident.getTime());
            detail.setText("详情： " + accident.getDescription());
        }
        return view;
    }
}
