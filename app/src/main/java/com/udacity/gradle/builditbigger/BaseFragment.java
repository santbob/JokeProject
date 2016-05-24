package com.udacity.gradle.builditbigger;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;

import com.udacity.gradle.builditbigger.androidjokelibrary.JokeActivity;

/**
 * Created by santhosh on 24/05/16.
 */
public class BaseFragment extends Fragment {

    private ProgressDialog mProgressDialog;

    @Override
    public void onResume() {
        super.onResume();
        dismissProgressDialog();
    }

    public void launchDispalyJokeIntent(String joke) {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(getActivity(), getString(R.string.please_wait), getString(R.string.loading));
            mProgressDialog.setCancelable(false);
        } else {
            mProgressDialog.setTitle(R.string.please_wait);
            mProgressDialog.setMessage(getResources().getString(R.string.loading));
            mProgressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null && !getActivity().isDestroyed()) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            mProgressDialog = null;
        }
    }
}
