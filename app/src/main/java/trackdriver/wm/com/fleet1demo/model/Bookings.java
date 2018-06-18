package trackdriver.wm.com.fleet1demo.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class Bookings {

    private String name;

    private List<LatLng> latlngs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LatLng> getLatlngs() {
        return latlngs;
    }

    public void setLatlngs(List<LatLng> latlngs) {
        this.latlngs = latlngs;
    }

}
