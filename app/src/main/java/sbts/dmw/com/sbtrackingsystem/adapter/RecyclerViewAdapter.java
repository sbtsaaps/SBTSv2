package sbts.dmw.com.sbtrackingsystem.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import sbts.dmw.com.sbtrackingsystem.R;
import sbts.dmw.com.sbtrackingsystem.model.Student;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context sContext;
    private List<Student> sData;
    RequestOptions option;

    public RecyclerViewAdapter(Context sContext, List<Student> sData) {

        this.sContext = sContext;
        this.sData = sData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(sContext);
        view = inflater.inflate(R.layout.student_row_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.student_name.setText(sData.get(i).getName());
        myViewHolder.student_division.setText(sData.get(i).getDivision());
        myViewHolder.student_roll_no.setText(sData.get(i).getRoll_no());
        myViewHolder.student_class.setText(sData.get(i).getS_class());

        Glide.with(sContext).load(sData.get(i).getPhoto()).apply(option).into(myViewHolder.student_thumbnail);
    }

    @Override
    public int getItemCount() {
        return sData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView student_name, student_division, student_roll_no, student_class;
        ImageView student_thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            student_name = itemView.findViewById(R.id.student_name);
            student_division = itemView.findViewById(R.id.student_division);
            student_roll_no = itemView.findViewById(R.id.student_roll_no);
            student_class = itemView.findViewById(R.id.student_class);
            student_thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }
}
