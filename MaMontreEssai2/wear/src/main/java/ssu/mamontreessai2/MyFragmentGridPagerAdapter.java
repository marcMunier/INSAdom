package ssu.mamontreessai2;

import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import java.util.List;
import java.util.Random;

public class MyFragmentGridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;
    private List mRows;
    private int I, J;

    public MyFragmentGridPagerAdapter(Context ctx, FragmentManager fm) {

        super(fm);
        mContext = ctx;
        Random r = new Random();
        I = r.nextInt(5);
        J = r.nextInt(5);


    }

    @Override
    public android.app.Fragment getFragment(int i, int j) {

        CardFragment fragment;
        if (i == I && j == J) {
             fragment = CardFragment.create("trouvé !!! ","trouvé !!!", R.drawable.vachenormande);
        }else{
             fragment = CardFragment.create("trouve la "," vache ", R.drawable.card_background);

        }
        return fragment;

    }

    @Override
    public int getRowCount() {
        return 5;
    }

    @Override
    public int getColumnCount(int i) {
        return 5;
    }
}

