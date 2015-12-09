package ssu.insadomapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;


import java.util.List;

public class Reception extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */


    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private SharedPreferences sharedpreferences;
    private HomeDataSource datasource;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.first_screen);
        setContentView(R.layout.activity_reception);

        // Opening SharedPreferences
//        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

        //Database
        datasource = new HomeDataSource(this);
        datasource.open();

        List<Home> homes = datasource.getAllHome();

        /*String essai_nom = "mamoune"; String essai_URL = "172.30.3.66:8080";
        Home home = new Home();
        home.setName(essai_nom);home.setURL(essai_URL);
        */

        //datasource.createHome(home); //ok ça marche
        //datasource.deleteHome((long) 2 ); //ok ça marche
        //datasource.deleteAllHome(); // ok ça marche


        Log.i("DEBUG_MUNIER", "onCreate: database" + "liste of home " + homes.toString() + "taille" +homes.size() );




        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        Log.i("DEBUG_MUNIER", "onCreate: before pageAdapter");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        Log.i("DEBUG_MUNIER", "onCreate: after findbyID");
        mViewPager.setAdapter(mSectionsPagerAdapter);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
         @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
      });
*/      Log.i("DEBUG_MUNIER", "onCreate: after pageAdapter");

/**
*
*        for (int i = 1; i<=9 ; i++) {
*            String buttonID = "receptionButton" + i ;
*            int resID = getResources().getIdentifier(buttonID, "id", "ssu.insadomap");
*            buttons[i-1] = ((ImageButton) findViewById(resID));
*        }
*
*        for (int i=0 ; i <= numberHome && i < 9 ; i++ ) {
*            //  buttons[i].setImageResource(R.drawable.icone_house);
*        }
 */







    }

/*
    @Override
    protected void onStart(){
        super.onStart();
  //      setContentView(R.layout.activity_reception);
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reception, menu);
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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int NBRE_MAISON = 3;
            View rootView = inflater.inflate(R.layout.fragment_reception, container, false);
    //        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
    //        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
       // TODO RECUPERER la base de donné et la liste      getArguments().get


            ImageView tmp = (ImageView) rootView.findViewById(R.id.imageButton);
            if( getArguments().getInt(ARG_SECTION_NUMBER) < NBRE_MAISON ){
                tmp.setImageResource(R.drawable.icone_house);
            }else if( getArguments().getInt(ARG_SECTION_NUMBER) == NBRE_MAISON ){
                tmp.setImageResource(R.drawable.icone_house_plus);
            }else{
                tmp.setImageResource(R.drawable.transparent);
            }

            return rootView;
       }
    }
}
