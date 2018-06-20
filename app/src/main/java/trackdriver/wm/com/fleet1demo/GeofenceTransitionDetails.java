package trackdriver.wm.com.fleet1demo;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

public class GeofenceTransitionDetails extends IntentService {

    public static final String GEOFENCE_UPDATE = "geofence_update";

    public static final String PACKAGE_NAME = GeofenceTransitionDetails.class.getPackage().getName();

    private static final String TAG = GeofenceTransitionDetails.class.getSimpleName();

    public static final int GEOFENCE_NOTIFICATION_ID = 0;

    public GeofenceTransitionDetails() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        // Handling errors
        if (geofencingEvent.hasError()) {
            String errorMsg = getErrorString(geofencingEvent.getErrorCode());
            Log.e(TAG, errorMsg);
            return;
        }

        Location triggeredLocation = geofencingEvent.getTriggeringLocation();

        double triggeredLat = triggeredLocation.getLatitude();
        double triggeredLong = triggeredLocation.getLongitude();
        Log.d(TAG, "the triggered locations: " + triggeredLat + triggeredLong);

        int geoFenceTransition = geofencingEvent.getGeofenceTransition();
        // Check if the transition type is of interest
        if (geoFenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geoFenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            // Get the geofence that were triggered
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            String geofenceTransitionDetails = getGeofenceTrasitionDetails(geoFenceTransition, triggeringGeofences,
                    triggeredLat, triggeredLong);

            // Send notification details as a String
            sendNotification(geofenceTransitionDetails);
        }
    }


    private String getGeofenceTrasitionDetails(int geoFenceTransition, List<Geofence> triggeringGeofences,
                                               double lat, double lon) {
        // get the ID of each geofence triggered
        ArrayList<String> triggeringGeofencesList = new ArrayList<>();
        for (Geofence geofence : triggeringGeofences) {
            triggeringGeofencesList.add(geofence.getRequestId());
        }

        String status = null;
        if (geoFenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER)
            status = "Entering Geofence";
        else if (geoFenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT)
            status = "Exiting Geofence";
//        return status + TextUtils.join(", ", triggeringGeofencesList);
        StringBuilder builder = new StringBuilder(status);
        builder.append(TextUtils.join(", ", triggeringGeofencesList));
        builder.append(" Lat: " + lat + ", Long: " + lon);
        return builder.toString();
    }

    private void sendNotification(String msg) {
        Log.i(TAG, "sendNotification: " + msg);
        //broadcast message.

        Intent intent = new Intent();
        intent.putExtra(GEOFENCE_UPDATE, msg);
        intent.setAction(PACKAGE_NAME);
        sendBroadcast(intent);
    }


    private static String getErrorString(int errorCode) {
        switch (errorCode) {
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return "GeoFence not available";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return "Too many GeoFences";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return "Too many pending intents";
            default:
                return "Unknown error.";
        }
    }
}
