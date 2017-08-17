package com.example.ichema.testingactivo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ichema.testingactivo.api.TestAPI;
import com.example.ichema.testingactivo.application.MyApplication;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private TestAPI testAPI;
    private List items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Instancia de gson utilizada por Retrofit para usarse en otra sección de la app.
        testAPI = ((MyApplication)getApplication()).getRetrofitInstance().create(TestAPI.class);
        items = new ArrayList<Caso>();

        Call<ArrayList<Caso>> call = testAPI.get();
        call.enqueue(new Callback<ArrayList<Caso>>() {

            @Override
            public void onResponse(Call<ArrayList<Caso>> call, Response<ArrayList<Caso>> response) {

                if(response.body() != null) {
                    for(Caso c : response.body()) {
                        items.add(c);
                    }
                    // Crear un nuevo adaptador
                    adapter = new CasoAdapter(items, MainActivity.this);
                    recycler.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Caso>> call, Throwable t) {
                Log.d("Falló", "FALLO EN LA EJECUCIÓN");
            }
        });

        // Obtener el Recycler

        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new CasoAdapter(items, MainActivity.this);
        recycler.setAdapter(adapter);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                // item clicked
                System.out.println("gg");
                Intent casoView = new Intent(getApplicationContext(), CasoActivity.class);
                startActivity(casoView);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
