package github.girish3.hackernews;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.squareup.otto.Bus;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.girish3.hackernews.provider.BusProvider;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    private Bus mBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // create and add home fragment
        HomeFragment homeFragment = HomeFragment.newInstance();
        addFragment(homeFragment);
    }

    private Bus getBus() {
        if (mBus == null) {
            mBus = BusProvider.getBusInstance();
        }
        return mBus;
    }

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();
    }
}
