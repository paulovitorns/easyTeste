package br.com.easyteste.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.easyteste.R;
import br.com.easyteste.model.PlaceItem;
import br.com.easyteste.model.Places;
import br.com.easyteste.presenter.MapsPrensenter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    private Places          places;
    private Context         context;
    private MapsPrensenter  prensenter;

    public PlacesAdapter(Places places, Context context, MapsPrensenter prensenter) {
        this.places     = places;
        this.context    = context;
        this.prensenter = prensenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(context).inflate(R.layout.view_row_place, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlaceItem place = places.getItems().get(position);

        if(place != null){
            holder.name.setText(place.getName());
            holder.setItem(place);
            holder.setPresenter(prensenter);
        }
    }

    @Override
    public int getItemCount() {
        return places.getItems().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.txtPlaceName) TextView name;

        private PlaceItem       item;
        private MapsPrensenter  presenter;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setItem(PlaceItem item) {
            this.item = item;
        }

        public void setPresenter(MapsPrensenter presenter) {
            this.presenter = presenter;
        }

        @Override
        public void onClick(View view) {
            presenter.placeSelected(item);
        }
    }
}
