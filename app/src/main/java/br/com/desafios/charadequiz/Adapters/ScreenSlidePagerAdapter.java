package br.com.desafios.charadequiz.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragments;
    private final Context context;
    private boolean enabled;

    public ScreenSlidePagerAdapter(final Context context, final FragmentManager fragmentManager,
                                   final List<Fragment> fragments) {
        super(fragmentManager);
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(final int position) {
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

}
