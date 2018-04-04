package com.sanus.sanus.domain.new_chat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.new_chat.data.Messages;
import com.sanus.sanus.domain.new_chat.presenter.NewChatPresenter;


import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private NewChatPresenter presenter;
    private List<Messages> commentsDoctorList;

    private static final int VIEW_TYPE_ME = 1;
    private static final int VIEW_TYPE_OTHER = 2;

    public void add(Messages messages){
        commentsDoctorList.add(messages);
        notifyItemInserted(commentsDoctorList.size() - 1);
    }

    public MessagesAdapter(Context context, List<Messages> commentsDoctorList, NewChatPresenter presenter) {
        this.context = context;
        this.commentsDoctorList = commentsDoctorList;
        this.presenter = presenter;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_ME:
                View viewChatMine = layoutInflater.inflate(R.layout.item_chat_mine, parent, false);
                viewHolder = new MyChatViewHolder(viewChatMine);
                break;
            case VIEW_TYPE_OTHER:
                View viewChatOther = layoutInflater.inflate(R.layout.item_chat_other, parent, false);
                viewHolder = new OtherChatViewHolder(viewChatOther);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (TextUtils.equals(commentsDoctorList.get(position).getAutor(),
                FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            configureMyChatViewHolder((MyChatViewHolder) holder, position);
        } else {
            configureOtherChatViewHolder((OtherChatViewHolder) holder, position);
        }
    }


    private void configureMyChatViewHolder(MyChatViewHolder myChatViewHolder, int position) {
        myChatViewHolder.message.setText(commentsDoctorList.get(position).getMensaje());
    }

    private void configureOtherChatViewHolder(OtherChatViewHolder otherChatViewHolder, int position) {
        otherChatViewHolder.mensage1.setText(commentsDoctorList.get(position).getMensaje());
    }

    @Override
    public int getItemCount() {
        if (commentsDoctorList != null) {
            return commentsDoctorList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.equals(commentsDoctorList.get(position).getAutor(),
                FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            return VIEW_TYPE_ME;
        } else {
            return VIEW_TYPE_OTHER;
        }
    }

    private static class MyChatViewHolder extends RecyclerView.ViewHolder {
        View mView;
        private TextView message;

        MyChatViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            message = itemView.findViewById(R.id.viewMessage);
        }
    }

    private static class OtherChatViewHolder extends RecyclerView.ViewHolder {
        View mView;
        private TextView mensage1;
        OtherChatViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mensage1 = itemView.findViewById(R.id.viewMessage1);
        }
    }
}