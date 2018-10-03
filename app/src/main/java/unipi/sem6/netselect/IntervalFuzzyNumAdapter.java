package unipi.sem6.netselect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import unipi.sem6.netselect.TFTopsisNetSelect.*;

public class IntervalFuzzyNumAdapter extends ArrayAdapter<InterValFN> {

    public IntervalFuzzyNumAdapter(Context context, ArrayList<InterValFN> nums) {
        super(context, 0, nums);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InterValFN num = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_interval_fuzzy_num, parent, false);
        }

        TextView term = (TextView) convertView.findViewById(R.id.term);
        TextView upper = (TextView) convertView.findViewById(R.id.upper);
        TextView lower = (TextView) convertView.findViewById(R.id.lower);

        term.setText(num.Linguistic_Term);
        upper.setText(num.A_U.toString());
        lower.setText(num.A_L.toString());

        return convertView;
    }

}
