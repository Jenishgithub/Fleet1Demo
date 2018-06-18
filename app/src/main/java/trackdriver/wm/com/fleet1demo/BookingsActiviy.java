package trackdriver.wm.com.fleet1demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackdriver.wm.com.fleet1demo.adapter.BookingsAdapter;
import trackdriver.wm.com.fleet1demo.model.Bookings;

public class BookingsActiviy extends AppCompatActivity {

    private RecyclerView rvBookingsList;
    private BookingsAdapter bookingsAdapter;
    private List<Bookings> bookings;

    public LatLng[] location_1 = new LatLng[]{
            new LatLng(27.66994, 85.32041),
            new LatLng(27.66599, 85.32267),
            new LatLng(27.66634, 85.33301)};

    public LatLng[] locations_2 = new LatLng[]{
            new LatLng(27.68034, 85.30216),
            new LatLng(27.67333, 85.30274),
            new LatLng(27.66168, 85.31725),
            new LatLng(27.66664, 85.33246),
            new LatLng(27.67647, 85.34948)};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookings_activity);
        bookings = new ArrayList<>();
        setRecyclerView();
    }

    private void setRecyclerView() {

        rvBookingsList = findViewById(R.id.rv_bookings_list);

        populateBookingsData();
        bookingsAdapter = new BookingsAdapter(this);
        bookingsAdapter.setBookings(bookings);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvBookingsList.setLayoutManager(mLayoutManager);
        rvBookingsList.setItemAnimator(new DefaultItemAnimator());
        rvBookingsList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvBookingsList.setAdapter(bookingsAdapter);

        bookingsAdapter.setOnItemClickListener(new BookingsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BookingsAdapter.ItemHolder item, int position, View view) {

                switch (view.getId()) {
                    case R.id.btn_start_tracking:

                        break;

                    case R.id.btn_stop_tracking:

                        break;

                }
            }
        });
    }

    private void populateBookingsData() {
        Bookings bookings_1 = new Bookings();
        bookings_1.setName("Booking1");
        bookings_1.setLatlngs(new ArrayList<>(Arrays.asList(location_1)));
        bookings.add(bookings_1);
        Bookings bookings_2 = new Bookings();
        bookings_2.setName("Booking2");
        bookings_2.setLatlngs(new ArrayList<>(Arrays.asList(locations_2)));
        bookings.add(bookings_2);
    }


}
