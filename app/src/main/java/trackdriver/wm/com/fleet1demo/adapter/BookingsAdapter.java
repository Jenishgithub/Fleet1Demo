package trackdriver.wm.com.fleet1demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import trackdriver.wm.com.fleet1demo.R;
import trackdriver.wm.com.fleet1demo.model.Bookings;


public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ItemHolder> {

    private OnItemClickListener onItemClickListener;
    private LayoutInflater layoutInflater;
    private List<Bookings> bookings = new ArrayList<>();

    public BookingsAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setBookings(List<Bookings> bookingList) {
        this.bookings = bookingList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking, parent, false);
        return new ItemHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Bookings booking = bookings.get(position);
        holder.tvBookingName.setText(booking.getName());
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(ItemHolder item, int position, View view);
    }


    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private BookingsAdapter parent;
        private TextView tvBookingName;
        private Button btnStartTracking, btnStopTracking;


        public ItemHolder(View view, BookingsAdapter parent) {
            super(view);
            this.parent = parent;
            view.setOnClickListener(this);
            tvBookingName = view.findViewById(R.id.tv_booking_name);
            btnStartTracking = view.findViewById(R.id.btn_start_tracking);
            btnStopTracking = view.findViewById(R.id.btn_stop_tracking);
            btnStartTracking.setOnClickListener(this);
            btnStopTracking.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            final OnItemClickListener listener = parent.getOnItemClickListener();
            if (listener != null) {
                listener.onItemClick(this, getPosition(), v);
            }
        }
    }

}
