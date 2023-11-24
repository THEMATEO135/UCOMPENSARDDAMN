package com.example.UCOMPENSARDDAMN.ui.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.UCOMPENSARDDAMN.DownloadImageTask;
import com.example.UCOMPENSARDDAMN.R;

import java.util.List;

public class ShopAdapter extends ArrayAdapter<Juego> {
    private final List<Juego> objects;
    public ShopAdapter(Context context, List<Juego> objects) {
        super(context, 0, objects);
        this.objects=objects;
    }

    public List<Juego> getList(){return objects;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.shop_item,
                    parent,
                    false);
        }

        // Referencias UI.
        ImageView avatar = convertView.findViewById(R.id.iv_avatar);
        TextView name = convertView.findViewById(R.id.tv_name);
        TextView title = convertView.findViewById(R.id.tv_title);

        // Lead actual.
        Juego game = getItem(position);

        // Setup.
        new DownloadImageTask(avatar)
                .execute(game.getImage());
        //Glide.with(getContext()).load(game.getImage()).into(avatar);
        name.setText(game.getName());
        title.setText(game.getDescripcion());

        return convertView;
    }
}
