package uk.ac.tees.W9519946.chat.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import uk.ac.tees.W9519946.chat.Fragments.Calls;
import uk.ac.tees.W9519946.chat.Fragments.Emergency_Chat;
import uk.ac.tees.W9519946.chat.Fragments.News;

public class FragementAdapter extends FragmentPagerAdapter {
    public FragementAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Emergency_Chat();
            case 1: return new News();
            case 2: return new Calls();
            default: return new Emergency_Chat();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0){
            title = "CHATS";
        }
        if (position == 1){
            title = "NEWS";
        }
        if (position == 2){
            title = "CALLS";
        }

        return title;
    }
}
