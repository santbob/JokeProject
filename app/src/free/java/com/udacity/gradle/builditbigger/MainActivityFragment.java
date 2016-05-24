package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by santhosh on 24/05/16.
 * Free
 */
public class MainActivityFragment extends BaseFragment {

    private InterstitialAd mInterstitialAd;
    private String mJoke;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                launchDispalyJokeIntent(mJoke);
            }
        });


        Button tellAJoke = (Button) root.findViewById(R.id.tell_a_joke);
        tellAJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                GCEAsyncTask asyncTask = new GCEAsyncTask(new GCEAsyncTask.ResultListener() {
                    @Override
                    public void onResult(String joke) {
                        if (mInterstitialAd.isLoaded()) {
                            mJoke = joke;
                            mInterstitialAd.show();
                        } else {
                            launchDispalyJokeIntent(joke);
                        }
                    }
                });
                asyncTask.execute();
            }
        });

        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

}
