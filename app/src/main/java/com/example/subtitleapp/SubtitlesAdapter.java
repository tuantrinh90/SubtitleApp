package com.example.subtitleapp;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubtitlesAdapter extends BaseRecycleAdapter<SubTitles, SubtitlesAdapter.SubTitleViewHolder> {
    private Context context;

    public SubtitlesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SubTitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subtitle, parent, false);
        return new SubTitleViewHolder(itemView);
    }

    public SubTitles getItem(int position) {
        if (listItems == null || listItems.size() <= 0) return null;
        return listItems.get(position);
    }

    @Override
    public void onBindViewHolder(SubTitleViewHolder holder, int position) {
        SubTitles subTitles = listItems.get(position);
        holder.tvItemSub.setText(subTitles.getItemSub());
        int colorRes = 0;
        switch (position % 7) {
            case 0:
                colorRes = R.color.colorSub1;
                break;
            case 1:
                colorRes = R.color.colorSub2;
                break;
            case 2:
                colorRes = R.color.colorSub3;
                break;
            case 3:
                colorRes = R.color.colorSub4;
                break;
            case 4:
                colorRes = R.color.colorSub5;
                break;
            case 5:
                colorRes = R.color.colorSub6;
                break;
            case 6:
                colorRes = R.color.colorSub7;
                break;
        }
        holder.tvItemSub.setTextColor(ContextCompat.getColor(context, colorRes));
        String firtSub = subTitles.getItemSub().split(":")[0];
        String lastSub = subTitles.getItemSub().split(":")[1];
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString firstSpannable = new SpannableString(firtSub);
        SpannableString lastSpannable = new SpannableString(lastSub);
        if (subTitles.isSelected()) {
            firstSpannable.setSpan(new StyleSpan(Typeface.BOLD), 0, firtSub.length(), 0);
            builder.append(firstSpannable);
            builder.append(":");
            builder.append(lastSpannable);
        } else {
            firstSpannable.setSpan(new StyleSpan(Typeface.NORMAL), 0, firtSub.length(), 0);
            builder.append(firstSpannable);
            builder.append(":");
            builder.append(lastSpannable);
        }
        holder.tvItemSub.setText(builder, TextView.BufferType.SPANNABLE);
    }

    class SubTitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvItemSub)
        TextView tvItemSub;

        private SubTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


