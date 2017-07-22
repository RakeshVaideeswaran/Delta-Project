package com.example.rakesh.hangmangame;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rakesh on 19/7/17.
 */

public class HangmanAdapter extends RecyclerView.Adapter<HangmanAdapter.HangmanViewHolder>{

    Context context;
    Cursor cursor;
    String column;

    HangmanAdapter(Context c,Cursor cursor, String C)
    {
        this.context = c;
        this.column = C;
        this.cursor = cursor;
    }

    @Override
    public HangmanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.singleitem,parent,false);
        HangmanViewHolder hangmanViewHolder = new HangmanViewHolder(row);

        return hangmanViewHolder;
    }

    @Override
    public void onBindViewHolder(HangmanViewHolder holder, int position) {

        if(!cursor.moveToPosition(position))
            return;

        String text = cursor.getString(cursor.getColumnIndex(column));
        long id = cursor.getLong(cursor.getColumnIndex(HangmanContract.HangmanEntry._ID));

        holder.T.setText(text.toUpperCase());
        holder.itemView.setTag(text);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class HangmanViewHolder extends RecyclerView.ViewHolder{

        TextView T;


        public HangmanViewHolder(View itemView) {
            super(itemView);

            T = itemView.findViewById(R.id.singleitemview);
        }
    }
}
