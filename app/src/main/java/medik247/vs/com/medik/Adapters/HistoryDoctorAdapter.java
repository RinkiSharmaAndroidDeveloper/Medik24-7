package medik247.vs.com.medik.Adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import medik247.vs.com.medik.HistoryModel;
import medik247.vs.com.medik.R;

/**
 * Created by VS3 on 5/31/2017.
 */

public class HistoryDoctorAdapter extends RecyclerView.Adapter<HistoryDoctorAdapter.MyViewHolder> {

    private ArrayList<HistoryModel> historyList;

    public HistoryDoctorAdapter(ArrayList<HistoryModel> historyList) {
        this.historyList = historyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_of_doctor_history, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HistoryModel history = historyList.get(position);
        holder.name.setText(history.getPatentName());
       // holder.address.setText(history.getPatentAddress());
        holder.phoneNumber.setText(history.getPatentPhone());
        holder.date.setText(history.getPatentdate());
        if(history.getStatus().contains("1"))
        {
            holder.accept.setText("Accepted");
        }else
        {
            holder.accept.setText("Rejected");
        }
       // holder.imageView.setImageDrawable(Drawable.createFromPath(history.getImage()));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phoneNumber,date;
        ImageView imageView;
        Button accept,reject;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            phoneNumber = (TextView) view.findViewById(R.id.phone_number);
           // phoneNumber = (TextView) view.findViewById(R.id.address);
            date = (TextView) view.findViewById(R.id.date);
            imageView = (ImageView) view.findViewById(R.id.profile_image);
            accept = (Button) view.findViewById(R.id.accept);
            reject = (Button) view.findViewById(R.id.reject);
        }
    }

}