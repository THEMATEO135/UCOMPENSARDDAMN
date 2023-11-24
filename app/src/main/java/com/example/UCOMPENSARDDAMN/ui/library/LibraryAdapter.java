package com.example.UCOMPENSARDDAMN.ui.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.UCOMPENSARDDAMN.ui.shop.Juego;
import com.example.UCOMPENSARDDAMN.R;

import java.util.List;

public class LibraryAdapter extends ArrayAdapter<Juego> {
    public LibraryAdapter(Context context, List<Juego> objects) {
        super(context, 0, objects);
    }

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
        Glide.with(getContext()).load(game.getImage()).into(avatar);
        name.setText(game.getName());
        title.setText(game.getDescripcion());

        return convertView;
    }
}
