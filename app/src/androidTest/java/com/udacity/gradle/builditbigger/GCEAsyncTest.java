package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * Created by santhosh on 23/05/16.
 * Tests the AsyncTask
 */
public class GCEAsyncTest extends AndroidTestCase {

    public void testNotEmptyJoke() {
        try {
            final CountDownLatch signal = new CountDownLatch(1);
            GCEAsyncTask task = new GCEAsyncTask(new GCEAsyncTask.ResultListener() {
                @Override
                public void onResult(String joke) {
                    Log.d("GCEAsyncTest", joke);
                    assertNotNull(joke);
                    signal.countDown();
                }
            });
            task.execute();
            signal.await();
        } catch (Exception e) {
            fail("Exception");
        }
    }
}
