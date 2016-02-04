package ssu.insadomapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
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

import android.widget.EditText;
import android.widget.ImageButton;
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

        //essai URL GET

        //fin essai URL GET


        //Database
        datasource = new HomeDataSource(this);
        datasource.open();

        List<Home> homes = datasource.getAllHome();

        /*String essai_nom = "mamoune"; String essai_URL = "172.30.3.66:8080";
        Home home = new Home();
        home.setName(essai_nom);home.setURL(essai_URL);*/


        //datasource.createHome(home); //ok ça marche
        //datasource.deleteHome((long) 2 ); //ok ça marche
        //datasource.deleteAllHome(); // ok ça marche


        Log.i("DEBUG_MUNIER", "onCreate: database" + "liste of home " + homes.toString() + "taille" +homes.size() );


        // Create the bundle, to pass get the database into the fragment

            /*
            // if you want do transaction in the fragment

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment fragment = CommentsFragment.newInstance(mDescribable);
            ft.replace(R.id.comments_fragment, fragment);
            ft.commit();
            */


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mSectionsPagerAdapter.setBDD(datasource);
        mViewPager = (ViewPager) findViewById(R.id.container);
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

        private HomeDataSource database;
        private List<Home> homes;
        private int Nbr_maison = 1;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setBDD (HomeDataSource mybdd ){
                this.database = mybdd;
                this.homes = this.database.getAllHome();
                this.Nbr_maison = this.homes.size();
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if( position < Nbr_maison ) {
                Log.i("DEBUG_MUNIER", "fragment: maison trouvée");
                return PlaceholderFragment.newInstance((position + 1) , this.homes.get(0));
            }else{
                Log.i("DEBUG_MUNIER", "fragment: plus de maison");
                Home home = new Home();
                home.setId(0);
                return PlaceholderFragment.newInstance((position + 1), home );
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return Nbr_maison + 1;
            //return 3;
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

        private String home_name;
        private String home_URL;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
      //  private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, Home home) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
           // args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString("Home_name", home.getName());
            args.putString("Home_URL", home.getURL());
            args.putLong("Home_id", home.getId());

            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
           // Home home = (Home) getArguments().getSerializable("Home");

            View rootView = inflater.inflate(R.layout.fragment_reception, container, false);
    //        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
    //        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
       // TODO RECUPERER la base de donné et la liste      getArguments().get
            Log.i("DEBUG_MUNIER", "onCreate_fragment: database" + " name of home " + getArguments().getString("Home_name") + "URL " + getArguments().getString("Home_URL"));


            ImageButton main_button = (ImageButton) rootView.findViewById(R.id.FragmentHomeButton);
            if( getArguments().getLong("Home_id") != 0 ){
                home_name = new String( getArguments().getString("Home_name"));
                home_URL =  new String( getArguments().getString("Home_URL" ));
                main_button.setImageResource(R.drawable.icone_house);
           //     main_button.setOnClickListener(new View.MyOnClickListener(getArguments().getString("Home_name"), getArguments().getString("Home_URL"), getActivity()));
                main_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendMessage(v);
                    }
                });

            }else{
                main_button.setImageResource(R.drawable.icone_house_plus);
            }

            return rootView;
       }

        public void sendMessage(View view) {
            Intent intent = new Intent(getActivity(), Chose_home.class);
            intent.putExtra("Home_name", home_name );
            intent.putExtra("Home_URL" , home_URL  );
            startActivity(intent);
        }




    }
/*

    private  class MyOnClickListener implements View.OnClickListener {
        private String name = null;
        private String URL  = null;
        FragmentActivity c;


        public MyOnClickListener(String name, String URL, FragmentActivity c) {
            this.name  = new String(name);
            this.URL   = new String(URL) ;
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(c , Chose_home.class);
            startActivity(intent);

        }

    }*/

}
