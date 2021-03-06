package com.manuel.retrofitnjson2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CategoriasFragment extends Fragment {

    private CategoriaAdapter adapter;
    private ListView listView;
    private FrameLayout layoutContainer;

    public CategoriasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);

        listView = view.findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://hippo4sem.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiCategoria apiCategoria = retrofit.create(ApiCategoria.class);
        Call<List<Categoria>> call = apiCategoria.getCategorias();

        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                List<Categoria> categoria = response.body();

                adapter = new CategoriaAdapter(getContext(), categoria);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                });
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
