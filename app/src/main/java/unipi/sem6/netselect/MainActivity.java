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

public class MainActivity extends AppCompatActivity {
    private TFTopsisNetSelect netsel = null;
    private ArrayList<String> list = null;
    private ArrayAdapter<String>  adapter = null;
    private ListView listView = null;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

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
