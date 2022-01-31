package com.example.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.cart.Model.ui.ExpandShopGroup;

import java.util.ArrayList;

public class CreateAndFillValuesInListShoppingRows extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ExpandShopGroup> groups;

    public CreateAndFillValuesInListShoppingRows(Context context, ArrayList<ExpandShopGroup> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChaild, View view, ViewGroup parent) {
        ExpandShopGroup group=(ExpandShopGroup) getGroup(groupPosition);
        if (view==null)
        {
            LayoutInflater inf=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=View.inflate(R.layout.expandlist_shopping_group, null);
        }

        TextView tv=(TextView) view.findViewById(R.id.tvGroup);
        tv.setText(group.getName());

        TextView tvDate=(TextView) view.findViewById(R.id.tvGroupDate);
        tvDate.setText(group.getDate().split("T")[0]);

        View viewId=view.findViewById(R.id.del);






        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
