package app.lbs.com.lbsapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.lbs.com.lbsapp.R;
import app.lbs.com.lbsapp.bean.Insurance;

public class InsuranceAdapter extends ArrayAdapter<Insurance> {

    private int resourceId;

    public InsuranceAdapter(@NonNull Context context, int resource, List<Insurance> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Insurance insurance = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }

        TextView insurer = view.findViewById(R.id.item_insurer);
        TextView policyId = view.findViewById(R.id.item_policy_id);
        TextView insurancePhone = view.findViewById(R.id.item_insurance_phone);
        TextView time = view.findViewById(R.id.item_time);

        if (insurance != null) {
            insurer.setText(insurance.getInsurer());
            policyId.setText("保单号： " + insurance.getPolicyId());
            insurancePhone.setText("保险电话： " + insurance.getInsurancePhone());
            String startTime = insurance.getStartTime();
            String endTime = insurance.getEndTime();
            time.setText(startTime + " - " + endTime);
        }
        return view;
    }
}
