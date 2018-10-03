package unipi.sem6.netselect;

import unipi.sem6.netselect.TFTopsisNetSelect.*;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TFTopsisNetSelect netsel = null;
    private ArrayList<String> list = null;
    private ArrayList<InterValFN> values = null;
    private ArrayAdapter<String>  adapter = null;
    private IntervalFuzzyNumAdapter ivfnadapter = null;
    private ListView listView = null;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private static double[] A(double ... args) {return args;} //eye-candy new double[]{x,y,z..}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.vpcontainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        this.values = new ArrayList<InterValFN>();
        this.ivfnadapter = new IntervalFuzzyNumAdapter(this, values);


        this.ivfnadapter.add(new InterValFN("AP", A(0,    0,    0,    0,    0.8), A(0,    0,    0,    0,    1)));
        this.ivfnadapter.add(new InterValFN("VP", A(0.01, 0.02, 0.03, 0.07, 0.8), A(0,    0.01, 0.05, 0.08, 1)));
        this.ivfnadapter.add(new InterValFN("P ", A(0.04, 0.1,  0.18, 0.23, 0.8), A(0.02, 0.08, 0.2,  0.25, 1)));
        this.ivfnadapter.add(new InterValFN("MP", A(0.17, 0.22, 0.36, 0.42, 0.8), A(0.14, 0.18, 0.38, 0.45, 1)));
        this.ivfnadapter.add(new InterValFN("M ", A(0.32, 0.41, 0.58, 0.65, 0.8), A(0.28, 0.38, 0.6,  0.7,  1)));
        this.ivfnadapter.add(new InterValFN("MG", A(0.58, 0.63, 0.8,  0.86, 0.8), A(0.5,  0.6,  0.9,  0.92, 1)));
        this.ivfnadapter.add(new InterValFN("G ", A(0.72, 0.78, 0.92, 0.97, 0.8), A(0.7,  0.75, 0.95, 0.98, 1)));
        this.ivfnadapter.add(new InterValFN("VG", A(0.93, 0.98, 1,    1,    0.8), A(0.9,  0.95, 1,    1,    1)));
        this.ivfnadapter.add(new InterValFN("AG", A(1,    1,    1,    1,    0.8), A(1,    1,    1,    1,    1)));



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double[] results = netsel.getResults();
                list.clear();
                for (int i = 0; i < netsel.D.size(); i++) {
                    list.add(netsel.D.get(i) + " - " + results[i]);
                }
                listView = (ListView) findViewById(R.id.netlistview);
                listView.setAdapter(adapter);
                Snackbar.make(view, "Running Net Select Test", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        this.netsel = new TFTopsisNetSelect(TestAlg.Weights);

        this.netsel.D.add(new Network("0", new InterValFN[] {TestAlg.MG, TestAlg.AG, TestAlg.VG, TestAlg.VG, TestAlg.VP, TestAlg.AG, TestAlg.VG}));
        this.netsel.D.add(new Network("1", new InterValFN[] {TestAlg.AG, TestAlg.MG, TestAlg.AG, TestAlg.MG, TestAlg.VP, TestAlg.VG, TestAlg.AG}));
        this.netsel.D.add(new Network("2", new InterValFN[] {TestAlg.M,  TestAlg.M,  TestAlg.MP, TestAlg.AG, TestAlg.P,  TestAlg.VG, TestAlg.AG}));
        this.netsel.D.add(new Network("3", new InterValFN[] {TestAlg.G,  TestAlg.G,  TestAlg.G,  TestAlg.G,  TestAlg.P,  TestAlg.AG, TestAlg.VG}));
        this.netsel.D.add(new Network("4", new InterValFN[] {TestAlg.VG, TestAlg.VG, TestAlg.MG, TestAlg.AG, TestAlg.MP, TestAlg.G,  TestAlg.G }));
        this.netsel.D.add(new Network("5", new InterValFN[] {TestAlg.MG, TestAlg.M,  TestAlg.MG, TestAlg.VG, TestAlg.MP, TestAlg.MG, TestAlg.MG}));
        this.netsel.D.add(new Network("6", new InterValFN[] {TestAlg.M,  TestAlg.MP, TestAlg.M,  TestAlg.AG, TestAlg.MP, TestAlg.G,  TestAlg.G }));

        this.list = new ArrayList<String>();
        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);


    }

    public IntervalFuzzyNumAdapter getIvfnadapter() {
        return ivfnadapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
            case 0:
                return NetworksList.newInstance("Networks");
            case 1:
                return IntervalFuzzyNumsList.newInstance("Values");
            default:
                return NetworksList.newInstance("Networks");
            }

        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
