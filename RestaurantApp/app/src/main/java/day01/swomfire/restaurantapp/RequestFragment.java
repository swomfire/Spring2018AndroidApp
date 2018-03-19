package day01.swomfire.restaurantapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import adapter.CustomRVAdapter;
import data.model.OrderRequest;
import data.remote.RmaAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.RmaAPIUtils;

public class RequestFragment extends Fragment {
    private RecyclerView rv;
    private android.widget.ExpandableListAdapter listAdapter;
    private List<OrderRequest> requestList;

    private RmaAPIService mService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_request, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mService = RmaAPIUtils.getAPIService();

        rv = getView().findViewById(R.id.rv_request_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        //loadRequestList();

        initList();
        CustomRVAdapter adapter = new CustomRVAdapter(requestList);
        rv.setAdapter(adapter);
    }

    public void loadRequestList() {
        mService.getRequestOrderList().enqueue(new Callback<List<OrderRequest>>() {
            @Override
            public void onResponse(Call<List<OrderRequest>> call, Response<List<OrderRequest>> response) {
                if (response.isSuccessful()) {
                    requestList = response.body();

                    CustomRVAdapter adapter = new CustomRVAdapter(requestList);
                    rv.setAdapter(adapter);
                    System.out.println("Loaded list");
                    Log.d(this.getClass().getSimpleName(), "GET loaded from API");
                }
            }

            @Override
            public void onFailure(Call<List<OrderRequest>> call, Throwable t) {
                System.out.println("Failed to load Order Request list");

            }
        });
    }

    private void initList() {
        requestList = new ArrayList<>();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setTableNo("4");
        orderRequest.setItemSeq("213");
        orderRequest.setItemName("Capu");
        requestList.add(orderRequest);
    }

}
