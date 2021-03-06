package adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import data.model.Category;
import day01.swomfire.restaurantapp.R;
import model.DishInItemList;
import utils.StyleUtils;

/**
 * Created by Swomfire on 08-Mar-18.
 */

public class ExpandableItemListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private static List<Category> listDataHeader;
    private static HashMap<Category, List<DishInItemList>> listHashMap;

    public ExpandableItemListAdapter(Context context, List<Category> listCategoryDataHeader, HashMap<Category, List<DishInItemList>> listDishHashMap) {
        this.context = context;
        listDataHeader = listCategoryDataHeader;
        listHashMap = listDishHashMap;
    }


    public static HashMap<Category, List<DishInItemList>> getListHashMap() {
        return listHashMap;
    }

    @Override
    public int getGroupCount() {
        if (listDataHeader == null) {
            return -1;
        }
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (listHashMap == null) {
            return -1;
        }
        return listHashMap.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(listDataHeader.get(i)).get(i1); // i = Group Item, i1 = Child Item
    }

    public static DishInItemList findDish(String id) {
        String[] strings = id.split(",");
        int i = Integer.parseInt(strings[0]);
        int i1 = Integer.parseInt(strings[1]);
        return listHashMap.get(listDataHeader.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        Category category = (Category) getGroup(i);
        String headerTitle = category.getCategoryId();
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (b) {
            view = layoutInflater.inflate(R.layout.item_tab_list_group_expanded, null);
        } else {
            view = layoutInflater.inflate(R.layout.item_tab_list_group, null);
            TextView lblDescription = view.findViewById(R.id.lblListItemDescription);
            lblDescription.setText(category.getDescription());
        }
        TextView lblListHeader = view.findViewById(R.id.lblListItemHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        switch (i % 4) {
            case 0:
                StyleUtils.setGradientBackground(view, new int[]{view.getResources().getColor(R.color.colorItemGroupColor1),
                        view.getResources().getColor(R.color.colorItemGroupColor1_5)}, StyleUtils.GradientMode.LEFT_RIGHT.getMode());
                break;
            case 1:
                StyleUtils.setGradientBackground(view, new int[]{view.getResources().getColor(R.color.colorItemGroupColor2),
                        view.getResources().getColor(R.color.colorItemGroupColor2_5)}, StyleUtils.GradientMode.LEFT_RIGHT.getMode());
                break;
            case 2:
                StyleUtils.setGradientBackground(view, new int[]{view.getResources().getColor(R.color.colorItemGroupColor3),
                        view.getResources().getColor(R.color.colorItemGroupColor3_5)}, StyleUtils.GradientMode.LEFT_RIGHT.getMode());
                break;
            case 3:
                StyleUtils.setGradientBackground(view, new int[]{view.getResources().getColor(R.color.colorItemGroupColor4),
                        view.getResources().getColor(R.color.colorItemGroupColor4_5)}, StyleUtils.GradientMode.LEFT_RIGHT.getMode());
                break;
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        DishInItemList dish = (DishInItemList) getChild(i, i1);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_tab_list_item, null);
        }

        //Set information form child to view
        TextView lblId = view.findViewById(R.id.lblListItemId);
        lblId.setText(i + "," + i1);

        TextView lblName = view.findViewById(R.id.lblListItem);
        lblName.setText(dish.getDish().getItemName());
        CheckBox checkBox = view.findViewById(R.id.itemCheckbox);
        checkBox.setChecked(dish.isSelected());

        TextView lblQuantity = view.findViewById(R.id.lblItemItemQuantity);
        lblQuantity.setText(String.valueOf(dish.getQuantity()));

        switch (i % 4) {
            case 0:
                StyleUtils.setGradientBackground(view, new int[]{view.getResources().getColor(R.color.colorItemGroupColor1),
                        view.getResources().getColor(R.color.colorItemGroupColor1_5)}, StyleUtils.GradientMode.LEFT_RIGHT.getMode());
                break;
            case 1:
                StyleUtils.setGradientBackground(view, new int[]{view.getResources().getColor(R.color.colorItemGroupColor2),
                        view.getResources().getColor(R.color.colorItemGroupColor2_5)}, StyleUtils.GradientMode.LEFT_RIGHT.getMode());
                break;
            case 2:
                StyleUtils.setGradientBackground(view, new int[]{view.getResources().getColor(R.color.colorItemGroupColor3),
                        view.getResources().getColor(R.color.colorItemGroupColor3_5)}, StyleUtils.GradientMode.LEFT_RIGHT.getMode());
                break;
            case 3:
                StyleUtils.setGradientBackground(view, new int[]{view.getResources().getColor(R.color.colorItemGroupColor4),
                        view.getResources().getColor(R.color.colorItemGroupColor4_5)}, StyleUtils.GradientMode.LEFT_RIGHT.getMode());
                break;
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
