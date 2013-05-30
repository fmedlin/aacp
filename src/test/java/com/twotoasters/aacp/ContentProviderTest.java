package com.twotoasters.aacp;

import android.content.ContentResolver;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

import java.util.Collection;
import java.util.List;

import com.activeandroid.Cache;
import com.activeandroid.ModelInfo;
import com.activeandroid.TableInfo;
import com.activeandroid.content.ContentProvider;
import com.activeandroid.query.Select;
import com.twotoasters.aacp.database.Thing;
import com.twotoasters.aacp.HelloAndroidActivity;
import com.twotoasters.aacp.R;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.Robolectric;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*
*  Don't try to test the ContentProvider using a ContentResolver.
*  It won't work.
*/

@RunWith(RobolectricTestRunner.class)
public class ContentProviderTest {

	ContentProvider provider;

	@Before
	public void setUp() throws Exception {
    	provider = new ContentProvider();
    	provider.onCreate();
	}

    @Test
    public void shouldInitContentProvider() throws Exception {
    	assertThat(provider.createUri(Thing.class, 1L).toString(), equalTo("content://com.twotoasters.aacp/things/1"));
    }

    @Test
    public void shouldQueryByProvider() throws Exception {
    	new Thing().save();
    	new Thing().save();
    	new Thing().save();

		Cursor c = provider.query(Uri.parse("content://com.twotoasters.aacp/things"), null, null, null, null);
    	assertNotNull(c);
    	assertThat(c.getCount(), equalTo(3));
    }

    /*
    *  Some ActiveAndroid tests on the way to working tests
    */
    
	@Test
	public void shouldInitModelInfo() throws Exception {
		ModelInfo info = new ModelInfo(Robolectric.application);
		assertNotNull(info.getTableInfos());
		assertTrue(info.getTableInfos().size() > 0);
	}

	@Test
	public void shouldInitializeCache() throws Exception {
		Collection<TableInfo> info = Cache.getTableInfos();
		assertNotNull(info);
		assertTrue(info.size() > 0);
		assertThat(Cache.getTableName(Thing.class), equalTo("Things"));
	}

    @Test
    public void itShouldQueryBySelect() throws Exception {
    	new Thing().save();
    	new Thing().save();
    	List<Thing> things = new Select().from(Thing.class).execute();
    	assertThat(things.size(), equalTo(2));
    }

}