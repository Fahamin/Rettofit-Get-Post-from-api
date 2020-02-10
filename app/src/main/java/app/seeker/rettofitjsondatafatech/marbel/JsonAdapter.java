package app.seeker.rettofitjsondatafatech.marbel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.seeker.rettofitjsondatafatech.R;

public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.myViewHoldera> {
    private Context context;
    private List<Hero> dataModelList;
    private RecyclerView recyclerView;

    public JsonAdapter(Context context, List<Hero> dataModelList, RecyclerView recyclerView) {
        this.context = context;
        this.dataModelList = dataModelList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public myViewHoldera onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row_item, viewGroup, false);

        return new myViewHoldera(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHoldera myViewHoldera, int i) {

        Hero hero = dataModelList.get(i);
        myViewHoldera.nameTv.setText(hero.getName());
        myViewHoldera.realnameTV.setText(hero.getRealname());
        myViewHoldera.createTv.setText(hero.getCreatedby());

    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    class myViewHoldera extends RecyclerView.ViewHolder {
        TextView nameTv, realnameTV, createTv;

        public myViewHoldera(@NonNull View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.tvName);
            realnameTV = itemView.findViewById(R.id.tvRealname);
            createTv = itemView.findViewById(R.id.tvCreatea);

        }
    }
}
