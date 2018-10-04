package unipi.sem6.netselect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import unipi.sem6.netselect.TFTopsisNetSelect.Network;

public class NetworkAdapter extends ArrayAdapter<Network> {

    public NetworkAdapter(Context context, ArrayList<Network> nets) {
        super(context, 0, nets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Network net = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_network, parent, false);
        }

        TextView netname = (TextView) convertView.findViewById(R.id.netname);
        TextView bandwidth = (TextView) convertView.findViewById(R.id.bandwidth);
        TextView delay = (TextView) convertView.findViewById(R.id.delay);
        TextView jitter = (TextView) convertView.findViewById(R.id.jitter);
        TextView packetloss = (TextView) convertView.findViewById(R.id.packetloss);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView serviceflex = (TextView) convertView.findViewById(R.id.serviceflex);
        TextView security = (TextView) convertView.findViewById(R.id.security);
        TextView result = (TextView) convertView.findViewById(R.id.result);

        netname.setText(net.name);
        bandwidth.setText(net.criteria[0].Linguistic_Term);
        delay.setText(net.criteria[1].Linguistic_Term);
        jitter.setText(net.criteria[2].Linguistic_Term);
        packetloss.setText(net.criteria[3].Linguistic_Term);
        price.setText(net.criteria[4].Linguistic_Term);
        serviceflex.setText(net.criteria[5].Linguistic_Term);
        security.setText(net.criteria[6].Linguistic_Term);
        result.setText(net.result == null ? "None" : String.format(Locale.US, "%.12f", net.result));

        return convertView;
    }

}
