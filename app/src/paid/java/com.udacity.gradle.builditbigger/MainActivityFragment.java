package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by santhosh on 24/05/16.
 */
public class MainActivityFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button tellAJoke = (Button) root.findViewById(R.id.tell_a_joke);
        tellAJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                GCEAsyncTask asyncTask = new GCEAsyncTask(new GCEAsyncTask.ResultListener() {
                    @Override
                    public void onResult(String joke) {
                        launchDispalyJokeIntent(joke);
                    }
                });
                asyncTask.execute();
            }
        });

        return root;
    }
}
