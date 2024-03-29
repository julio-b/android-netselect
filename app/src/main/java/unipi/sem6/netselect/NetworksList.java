package unipi.sem6.netselect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class NetworksList extends Fragment {
    private static final String ARG_PAGE = "ARG_PAGE";

    private String mPage;

    public NetworksList() {
        // Required empty public constructor
    }

    public static NetworksList newInstance(String page) {
        NetworksList fragment = new NetworksList();
        Bundle args = new Bundle();
        args.putString(ARG_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getString(ARG_PAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_networks_list, container, false);
        NetworkAdapter netadapter =  ((MainActivity)getActivity()).getNetadapter();
        ListView netListView = (ListView) view.findViewById(R.id.netlistview);
        netListView.setAdapter(netadapter);
        return view;
    }
}
