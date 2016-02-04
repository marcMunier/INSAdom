package ssu.mamontreessai2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private View tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        /*
        setContentView(R.layout.acitvity_main_essai);
        FragmentManager fragmentManager =getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CardFragment cardFragment = CardFragment.create("fragment 1 (titre)", "bienvenue", R.drawable.vachenormande);
        fragmentTransaction.add(R.id.frame_layout, cardFragment);
        fragmentTransaction.commit();


    <FrameLayout
        android:id="@+id/frame_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_box="bottom">

    </FrameLayout>

        */
        tmp = findViewById(R.id.watch_view_stub);
        setContentView(R.layout.acitvity_main_essai);
        GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyFragmentGridPagerAdapter(this, getFragmentManager()));

        /*
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });

        */

    }


    public void onEnterAmbient (Bundle ambientDetails){
    if(isAmbient()) {
        super.onEnterAmbient(ambientDetails);
        setContentView(R.layout.ambiant);
        Log.i("DEBBUG", "onEntrer");
    }

    }

@Override
    public void onUpdateAmbient (){
    super.onUpdateAmbient();
    Log.i("DEBBUG", "onUpdate");


}
@Override
    public void onExitAmbient (){
        super.onExitAmbient();
    Log.i("DEBBUG", "onExit");

    if (tmp != null){   setContentView(tmp);
        Log.i("DEBBUG", "onExit2");
    }
    setContentView(R.layout.acitvity_main_essai);
    }




}
