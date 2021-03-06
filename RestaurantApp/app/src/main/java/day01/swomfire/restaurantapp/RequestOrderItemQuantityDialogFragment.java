package day01.swomfire.restaurantapp;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import model.DishInItemList;
import utils.StyleUtils;


public class RequestOrderItemQuantityDialogFragment extends DialogFragment {
    private static View view;
    private TextView currentItemQuantityText;
    private TextView itemQuantityText;
    private TextView lblId;

    public void setUp(View view1) {
        view = view1;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View quantityDialog = inflater.inflate(R.layout.fragment_dialog_item_request_quantity, null);
        builder.setView(quantityDialog);
        StyleUtils.setGradientBackground(quantityDialog, R.id.itemItemQuantityDialog,
                new int[]{getContext().getResources().getColor(R.color.colorPrimary),
                        getContext().getResources().getColor(R.color.colorPrimary)}, StyleUtils.GradientMode.TOP_BOTTOM.getMode());
        // Get current item quantity
        currentItemQuantityText = view.findViewById(R.id.lblItemRequestRowQuantity);

        View parent1 = (View) view.getParent();
        View parent2 = (View) parent1.getParent();
        lblId = parent2.findViewById(R.id.itemRequestId);

        // Set Quantity for dialog
        itemQuantityText = quantityDialog.findViewById(R.id.itemItemQuantityDialogQuantity);
        itemQuantityText.setText(currentItemQuantityText.getText());

        // Change button edit
        Button btnChange = quantityDialog.findViewById(R.id.btnItemItemQuantityDialogChange);
        btnChange.setOnClickListener(view -> {
            currentItemQuantityText.setText(itemQuantityText.getText());
            String position = String.valueOf(lblId.getText());
            DishInItemList dishInItemList = RequestOrderActivity.getDishInRequestItemList(Integer.parseInt(position));
            int quantityNew = Integer.valueOf(String.valueOf(itemQuantityText.getText()));
            if (quantityNew == 0) {
                dishInItemList.setQuantity(1);
                dishInItemList.setSelected(false);
            } else {
                dishInItemList.setQuantity(quantityNew);
            }
            RequestOrderItemQuantityDialogFragment.this.dismiss();
        });
        // Add button edit
        Button btnAdd = quantityDialog.findViewById(R.id.btnItemItemQuantityDialogAdd);
        btnAdd.setOnClickListener(view -> {
            int quantity = Integer.parseInt(String.valueOf(itemQuantityText.getText()));
            if (++quantity <= 10) {
                itemQuantityText.setText(String.valueOf(quantity));

            }
        });
        // Sub button edit
        Button btnSub = quantityDialog.findViewById(R.id.btnItemItemQuantityDialogSub);
        btnSub.setOnClickListener(view -> {
            int quantity = Integer.parseInt(String.valueOf(itemQuantityText.getText()));
            if (--quantity >= 0) {
                itemQuantityText.setText(String.valueOf(quantity));
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        RequestOrderActivity.initRecycleListView(getActivity());
    }
}
