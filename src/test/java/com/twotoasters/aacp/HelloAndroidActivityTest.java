package com.twotoasters.aacp;

import com.twotoasters.aacp.HelloAndroidActivity;
import com.twotoasters.aacp.R;
import org.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class HelloAndroidActivityTest {

    @Test
    public void shouldHaveHappySmiles() throws Exception {
        String appName = new HelloAndroidActivity().getResources().getString(R.string.app_name);
        assertThat(appName, equalTo("aacp"));
    }
}