package com.smb.examples;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smb.examples.loadingbananapeel.R;
import com.smb.loadingbananapeel.LoadingBananaPeelView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private final static int INTERVAL = 5000; //  5000ms = 5 seconds

        LoadingBananaPeelView mLoadingBananaPeelView;
        Timer pseudoContentDelay = new Timer("PseudoContentDelay", true);

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mLoadingBananaPeelView = (LoadingBananaPeelView)rootView.findViewById(R.id.fragment_content_loading_view);

            // Configure the banana peel empty state message, image, and click state
            mLoadingBananaPeelView.setBananaPeel(R.string.banana_peel_default_empty_message, R.drawable.ic_bananapeel_default, new LoadingBananaPeelView.BananaPeelActionListener() {
                @Override
                public void onBananaPeelClick() {
                    Toast.makeText(getActivity(), "Clicked Bananana Peel!", Toast.LENGTH_SHORT).show();
                    mLoadingBananaPeelView.showContent();
                }
            });

            // Configure the content view
            mLoadingBananaPeelView.setContentView(R.layout.view_example_content);


            // Arbitrary flip between states
            mLoadingBananaPeelView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mLoadingBananaPeelView.isShowingContentView()) {
                        mLoadingBananaPeelView.showBananaPeel();
                    } else {
                        mLoadingBananaPeelView.showContent();
                    }
                }
            });

            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mLoadingBananaPeelView.showLoading();

            // Simulate network delay
            pseudoContentDelay.schedule(new TimerTask() {
                @Override
                public void run() {
                    mLoadingBananaPeelView.showContent();
                }
            }, INTERVAL);
        }
    }
}