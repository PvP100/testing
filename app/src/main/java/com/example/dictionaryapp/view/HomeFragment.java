package com.example.dictionaryapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dictionaryapp.R;
import com.example.dictionaryapp.adapter.ItemCategoryAdapter;
import com.example.dictionaryapp.adapter.VerticalRecyclerViewAdapter;
import com.example.dictionaryapp.model.CategoryTitle;
import com.example.dictionaryapp.model.ItemCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    RecyclerView mVerticalRecycler;
    VerticalRecyclerViewAdapter verticalRecyclerViewAdapter;
    List<CategoryTitle> mListAllCategory;
    List<ItemCategory> mItemCategoryList, mItemCategoryList2, mItemCategoryList3;
    ItemCategoryAdapter itemCategoryAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mapping(view);
        init();

        return view;
    }

    private void init() {
        mItemCategoryList = new ArrayList<>();
        mListAllCategory = new ArrayList<>();
        mListAllCategory.add(new CategoryTitle("Album", mItemCategoryList));
        mListAllCategory.add(new CategoryTitle("Playlist", mItemCategoryList));

        verticalRecyclerViewAdapter = new VerticalRecyclerViewAdapter(getActivity(), mListAllCategory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        mVerticalRecycler.setLayoutManager(linearLayoutManager);
        mVerticalRecycler.setAdapter(verticalRecyclerViewAdapter);
        getAlbum();
    }

    private void mapping(View view) {
        mVerticalRecycler = view.findViewById(R.id.recycle_category);
    }

    public void getAlbum() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://ung-dung-nghe-nhac.000webhostapp.com/API/readDataAlbum.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mItemCategoryList.add(new ItemCategory(jsonObject.getString("tenAlbum"), jsonObject.getString("hinhAlbum")));
                    }
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "LO" + e.toString(), Toast.LENGTH_SHORT).show();
                }
                verticalRecyclerViewAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Loi" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}