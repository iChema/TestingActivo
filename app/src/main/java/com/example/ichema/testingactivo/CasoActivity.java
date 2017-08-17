package com.example.ichema.testingactivo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ichema.testingactivo.api.TestAPI;
import com.example.ichema.testingactivo.application.MyApplication;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CasoActivity extends AppCompatActivity {
    PieChart pieChart;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private Button btnComenzar;
    private List<Prueba> items;
    private Caso casoPrueba;
    private TestAPI testAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso);
        Gson gson = new Gson();
        casoPrueba = gson.fromJson(getIntent().getExtras().getString("caso"), Caso.class);
        btnComenzar = (Button) findViewById(R.id.comenzar);

        TextView nombreCaso = (TextView) findViewById(R.id.nombre_caso);
        nombreCaso.setText(casoPrueba.getNombre());
        actualizarImagen();

        // Inicializar Pruebas
        items = new ArrayList();

        testAPI = ((MyApplication)getApplication()).getRetrofitInstance().create(TestAPI.class);

        Call<ArrayList<Prueba>> call = testAPI.getPruebas(casoPrueba.getId());
        call.enqueue(new Callback<ArrayList<Prueba>>() {

            @Override
            public void onResponse(Call<ArrayList<Prueba>> call, Response<ArrayList<Prueba>> response) {
                if(response.body() != null) {
                    for(Prueba p : response.body()) {
                        items.add(p);
                    }
                    // Crear un nuevo adaptador
                    adapter = new PruebaAdapter(items, CasoActivity.this);
                    recycler.setAdapter(adapter);
                    addDataSet();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Prueba>> call, Throwable t) {
                Log.d("Falló", "FALLO EN LA EJECUCIÓN");
            }
        });
        // Obtener el Recycler

        recycler = (RecyclerView) findViewById(R.id.reciclador2);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new PruebaAdapter(items, CasoActivity.this);
        recycler.setAdapter(adapter);
        recycler.setNestedScrollingEnabled(false);

        pieChart = (PieChart) findViewById(R.id.idPieChart);

        pieChart.setDescription(null);
        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(false);
        pieChart.setHoleRadius(80f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("HDI");
        pieChart.setCenterTextSize(50);
        pieChart.setMinimumHeight(350);
        
        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });

        pieChart.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                // item clicked
                System.out.println("gg");
                Intent casoView = new Intent(getApplicationContext(), PruebaActivity.class);
                startActivity(casoView);
            }
        });

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ArrayList<Boolean>> call = testAPI.test(casoPrueba.getId());
                call.enqueue(new Callback<ArrayList<Boolean>>() {


                    @Override
                    public void onResponse(Call<ArrayList<Boolean>> call, Response<ArrayList<Boolean>> response) {

                    }

                    @Override
                    public void onFailure(Call<ArrayList<Boolean>> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void addDataSet() {
        PieDataSet pieDataSet = new PieDataSet(getPieEntries(), "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(10);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.colorPrimary));
        colors.add(Color.RED);
        colors.add(Color.GRAY);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    void actualizarImagen() {
        ImageView imageView = (ImageView) findViewById(R.id.estatusImagen);

        if(casoPrueba.getFecha_ejecucion() == null){
            imageView.setImageResource(R.drawable.info);
        } else if(casoPrueba.getFecha_ejecucion() != null && casoPrueba.isEn_ejecucion() == true){
            imageView.setImageResource(R.drawable.load);
        } else if(casoPrueba.getFecha_ejecucion() != null && casoPrueba.isEn_ejecucion() == false && casoPrueba.getStatus() == "fallido"){
            imageView.setImageResource(R.drawable.cancel);
        } else if(casoPrueba.getFecha_ejecucion() != null && casoPrueba.isEn_ejecucion() == false && casoPrueba.getStatus() == "pasado") {
            imageView.setImageResource(R.drawable.checked);
        }
    }

    public ArrayList<PieEntry> getPieEntries() {
        ArrayList<PieEntry> a = new ArrayList<>();
        int aprobadas = 0, fallidas = 0;
        for(Prueba p : items) {
            if(p.getStatus().equals("aprobado")) {
                aprobadas++;
            }
            else if(p.getStatus().equals("fallido")) {
                fallidas++;
            }
        }

        a.add(new PieEntry(aprobadas, "Pasadas"));
        a.add(new PieEntry(fallidas, "Fallidas"));
        return a;
    }
}
