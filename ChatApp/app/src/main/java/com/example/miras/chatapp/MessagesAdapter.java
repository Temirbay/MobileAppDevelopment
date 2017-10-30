package com.example.miras.chatapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Miras on 24.10.2017.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {
    List<Message> messageList;
    Context cont ;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text, user, time;

        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.textView);
            user = view.findViewById(R.id.userView);
            time = view.findViewById(R.id.timeView);
        }
    }


    public MessagesAdapter(List<Message> messageList, Context con) {
        this.messageList = messageList; cont = con;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(cont)
                .inflate(R.layout.message_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.text.setText(message.getMessageText());
        holder.user.setText(message.getMessageUser());
        holder.time.setText(message.getMessageTime());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
