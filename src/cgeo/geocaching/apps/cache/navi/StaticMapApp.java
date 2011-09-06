package cgeo.geocaching.apps.cache.navi;

import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import cgeo.geocaching.R;
import cgeo.geocaching.cgCache;
import cgeo.geocaching.cgGeo;
import cgeo.geocaching.cgWaypoint;
import cgeo.geocaching.cgeosmaps;
import cgeo.geocaching.activity.ActivityMixin;

class StaticMapApp extends AbstractNavigationApp implements
		NavigationApp {

	StaticMapApp(final Resources res) {
		super(res.getString(R.string.cache_menu_map_static), null);
	}

	@Override
	public boolean isInstalled(Context context) {
		return true;
	}

	@Override
	public boolean invoke(cgGeo geo, Activity activity, Resources res,
			cgCache cache,
			final UUID searchId, cgWaypoint waypoint, Double latitude, Double longitude) {

		if (cache == null || cache.reason == 0) {
			ActivityMixin.showToast(activity, res.getString(R.string.err_detail_no_map_static));
			return true;
		}

		if (cache.geocode != null) {
			Intent smapsIntent = new Intent(activity, cgeosmaps.class);
			smapsIntent.putExtra("geocode", cache.geocode.toUpperCase());
			activity.startActivity(smapsIntent);
			return true;
		}
		return false;
	}

}
