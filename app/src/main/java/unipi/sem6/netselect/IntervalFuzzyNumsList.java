package unipi.sem6.netselect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class IntervalFuzzyNumsList extends Fragment {
    private static final String ARG_PAGE = "ARG_PAGE";

    private String mPage;

    public IntervalFuzzyNumsList() {
        // Required empty public constructor
    }

    public static IntervalFuzzyNumsList newInstance(String page) {
        IntervalFuzzyNumsList fragment = new IntervalFuzzyNumsList();
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
        View view = inflater.inflate(R.layout.fragment_interval_fuzzy_nums_list, container, false);
        IntervalFuzzyNumAdapter ivfnadapter =  ((MainActivity)getActivity()).getIvfnadapter();
        ListView valuesListView = (ListView) view.findViewById(R.id.values);
        valuesListView.setAdapter(ivfnadapter);
        return view;
    }
}
