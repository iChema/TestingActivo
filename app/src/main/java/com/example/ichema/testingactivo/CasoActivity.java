package com.example.ichema.testingactivo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class CasoActivity extends AppCompatActivity {
    PieChart pieChart;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private float[] yData = {5f,4f,2f};
    private String[] xData = {"Aprobadas","Reprobadas","Sin realizar"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso);

        // Inicializar Pruebas
        List items = new ArrayList();

        items.add(new Prueba(R.drawable.uno, "Prueba 1", 230));
        items.add(new Prueba(R.drawable.uno, "Prueba 2", 456));
        items.add(new Prueba(R.drawable.uno, "Prueba 3", 342));
        items.add(new Prueba(R.drawable.uno, "Prueba 4", 645));
        items.add(new Prueba(R.drawable.uno, "Prueba 5", 459));

        // Obtener el Recycler

        recycler = (RecyclerView) findViewById(R.id.reciclador2);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new PruebaAdapter(items);
        recycler.setAdapter(adapter);

        pieChart = (PieChart) findViewById(R.id.idPieChart);

        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(10f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("TestingActivo");
        pieChart.setCenterTextSize(10);
        
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
    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        int aux = yData.length;
        int aux2 = xData.length;

        for(int i = 0; i < aux; i++){
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for(int i = 0; i < aux2; i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Resultado de pruebas");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(10);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
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
}
