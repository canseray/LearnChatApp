package com.example.learnchatapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsAdapter extends FragmentPagerAdapter {
    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }




    @Override
    public Fragment getItem(int position) { //tabların kontrolleri,hangi bloga tıkladıgında hangisi gelecek

        switch (position){

            case 0:
                RequestFragment requestFragment = new RequestFragment();
                return requestFragment;

            case 1:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;

            case 2:
                FriendsFragment friendsFragment = new FriendsFragment();
                return  friendsFragment;

            default:
                return null;
        }

    }




    @Override
    public int getCount() { //kac tab varsa
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) { //tıklanmayla dönecek tab baslıgı

        switch (position){

            case 0:
                return "İSTEKLER";

            case 1:
                return "MESAJLAR";

            case 2:
                return "ARKADAŞLAR";

            default:
                return null;
        }
    }
}
