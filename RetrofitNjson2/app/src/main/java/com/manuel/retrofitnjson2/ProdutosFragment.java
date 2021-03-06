package com.manuel.retrofitnjson2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutosFragment extends Fragment {

    //private TextView txtId;
    private ListView listView;
    private FrameLayout layoutContainer;
    private ProdutoAdapter adapter;

    public ProdutosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produtos, container, false);

        listView = view.findViewById(R.id.listView);
        //txtId = view.findViewById(R.id.txtId);

        int id = 0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
        }


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://hippo4sem.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiCategoria apiCategoria = retrofit.create(ApiCategoria.class);
        Call<List<Produto>> call = apiCategoria.getProdutos(id);

        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response2) {
                List<Produto> produto = response2.body();

                adapter = new ProdutoAdapter(getContext(), produto);
                listView.setAdapter(adapter);

                /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        //Toast.makeText(getContext(), "Clicked product id =" + view.getTag(), Toast.LENGTH_SHORT).show();

                        layoutContainer = (FrameLayout) view.findViewById(R.id.layoutContainer);
                        ProdutosFragment fragment = new ProdutosFragment();

                        int idProd = Integer.parseInt(view.getTag().toString());

                        Bundle args = new Bundle();
                        args.putInt("id", idProd);
                        fragment.setArguments(args);


                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, fragment).commit();
                    }
                });*/
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //txtId.setText(Integer.toString(id));

        return view;
    }

}
