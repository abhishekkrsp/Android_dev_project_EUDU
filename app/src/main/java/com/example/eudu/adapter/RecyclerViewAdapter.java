package com.example.eudu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eudu.R;
import com.example.eudu.details;

import java.util.List;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<details> detailsList;
    private List<String> datelist;

    public RecyclerViewAdapter(Context context, List<details> detailsList,List<String> datelist) {
        this.context = context;
        this.detailsList = detailsList;
        this.datelist=datelist;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        details det=detailsList.get(position);
        String datetime=datelist.get(position);
        holder.date.setText(datetime);
        holder.company.setText(det.getCompany());
        holder.rpm.setText(det.getRpm());
        holder.powerRating.setText(det.getPowerRating());
        holder.volts.setText(det.getVolts());
        holder.amps.setText(det.getAmps());
        holder.encl.setText(det.getEncl());
        holder.inscl.setText(det.getInscl());
        holder.manufactureYear.setText(det.getManufactureYear());
        holder.ambTemp.setText(det.getAmbTemp());
        holder.duty.setText(det.getDuty());
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView date;
        private TextView company;
        private TextView rpm;
        private TextView powerRating;
        private TextView volts;
        private TextView amps;
        private TextView encl;
        private TextView inscl;
        private TextView manufactureYear;
        private TextView ambTemp;
        private TextView duty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            date=itemView.findViewById(R.id.tvDate);
            company=itemView.findViewById(R.id.tvCompany);
            rpm=itemView.findViewById(R.id.tvrpm);
            powerRating=itemView.findViewById(R.id.tvp_rating);
            volts=itemView.findViewById(R.id.tvVolts);
            amps=itemView.findViewById(R.id.tvAmps);
            encl=itemView.findViewById(R.id.tvEncl);
            inscl=itemView.findViewById(R.id.tvIns_cl);
            manufactureYear=itemView.findViewById(R.id.tvMfg_year);
            ambTemp=itemView.findViewById(R.id.tvAmb_temp);
            duty=itemView.findViewById(R.id.tvDuty);

        }

        @Override
        public void onClick(View v) {

            Log.d("click from rvh","Clicked");
        }
    }
}
