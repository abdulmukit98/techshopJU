package com.example.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.cart.Model.ui.ExpandShopGroup;

import java.util.ArrayList;

/**
 * <p> Creating this class for Filling Values in Shopping rows. </p>
 */

public class CreateAndFillValuesInListShoppingRows extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ExpandShopGroup> groups;

    /**
     * <p>
     * @param context <br>
     * @param groups <br>
     * </p>
     */

    public CreateAndFillValuesInListShoppingRows(Context context, ArrayList<ExpandShopGroup> groups) {
        this.context = context;
        this.groups = groups;
    }

    /**
     * <p>
     * Finding "Count Group". <br>
     * @return
     * </p>
     */

    @Override
    public int getGroupCount() {
        return 0;
    }

    /**
     * <p>
     * @param i <br>
     * @return <br>
     * </p>
     */

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    /**
     * <p>
     * @param i <br>
     * @return
     * </p>
     */

    @Override
    public Object getGroup(int i) {
        return null;
    }

    /**
     * <p>
     * @param i <br>
     * @param i1 <br>
     * @return
     * </p>
     */

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    /**
     * <p>
     * @param i <br>
     * @return
     * </p>
     */

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    /**
     * <p>
     * @param i <br>
     * @param i1 <br>
     * @return
     * </p>
     */

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    /**
     * <p>
     * @return "False".
     * </p>
     */

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * <p>
     * @param groupPosition <br>
     * @param isLastChaild <br>
     * @param view <br>
     * @param parent <br>
     * @return
     * </p>
     */

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

    /**
     * <p>
     * @param i <br>
     * @param i1 <br>
     * @param b <br>
     * @param view <br>
     * @param viewGroup <br>
     * @return
     * </p>
     */

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    /**
     * <p>
     * @param i <br>
     * @param i1 <br>
     * @return
     * </p>
     */

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
