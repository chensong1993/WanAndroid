package com.shanghai.templateapp.ui.adapter.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.shanghai.templateapp.ui.fragment.HomeFragment;
import com.shanghai.templateapp.ui.fragment.MeFragment;
import com.shanghai.templateapp.ui.fragment.ProjectFragment;
import com.shanghai.templateapp.ui.fragment.StarFragment;
import com.shanghai.templateapp.ui.fragment.home.ArticleFragment;

import java.util.ArrayList;


/**
 *
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment;

    public MainViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragments.clear();
        fragments.add(new HomeFragment());
        fragments.add(new ProjectFragment());
        fragments.add(new MeFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * Get the current fragment
     */
    public Fragment getCurrentFragment() {
        return currentFragment;
    }
}