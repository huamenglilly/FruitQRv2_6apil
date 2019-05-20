package kiky.beam.lilly.th.ac.rmutk.fruitqr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TotalFruitAdapter extends  RecyclerView.Adapter<TotalFruitAdapter.TotaiFruitViewHolder> {

    private Context context;
    private ArrayList<String> nameStringArrayList, amountStringArrayList, unitStringArrayList;
    private OnClickItem onClickItem;
    private LayoutInflater layoutInflater;

    public TotalFruitAdapter(Context context,
                             ArrayList<String> nameStringArrayList,
                             ArrayList<String> amountStringArrayList,
                             ArrayList<String> unitStringArrayList,
                             OnClickItem onClickItem) {

        this.layoutInflater = LayoutInflater.from(context);
        this.nameStringArrayList = nameStringArrayList;
        this.amountStringArrayList = amountStringArrayList;
        this.unitStringArrayList = unitStringArrayList;
        this.onClickItem = onClickItem;

    }

    @NonNull
    @Override
    public TotaiFruitViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.recyclert_list_total_fruit, viewGroup, false);
        TotaiFruitViewHolder totaiFruitViewHolder = new TotaiFruitViewHolder(view);

        return totaiFruitViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TotaiFruitViewHolder totaiFruitViewHolder, int i) {

        String nameFruit = nameStringArrayList.get(i);
        String amount = amountStringArrayList.get(i);
        String unit = unitStringArrayList.get(i);

        totaiFruitViewHolder.nameTextView.setText(nameFruit);
        totaiFruitViewHolder.amountTextView.setText(amount);
        totaiFruitViewHolder.unitTextView.setText(unit);
        totaiFruitViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClickitem(v, totaiFruitViewHolder.getAdapterPosition());
            }
        });




    }

    @Override
    public int getItemCount() {
        return nameStringArrayList.size();
    }

    public  class TotaiFruitViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView, amountTextView, unitTextView;

        public TotaiFruitViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.txtNameFruit);
            amountTextView = itemView.findViewById(R.id.txtAmount);
            unitTextView = itemView.findViewById(R.id.txtUnit);
        }
    }
}
