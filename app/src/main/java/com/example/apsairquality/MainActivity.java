package com.example.apsairquality;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

import API.OpenMeteo;
import Data.*;




public class MainActivity extends AppCompatActivity {

    ImageButton btn1;
    TextView DisplayLocation, DisplayMP2_5, DisplayMP10, DisplayCO2, DisplayStatus;
    SearchView searchView;
    ListView listView;
    Map<String, List<String>> Data;
    ArrayAdapter<String> adapter;
    ArrayList<String> States;

    StoredState State = new StoredState();
    Methods method = new Methods();
    OpenMeteo client = new OpenMeteo();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Data = State.StartStateData();
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.EstateList);
        States = new ArrayList<>();

        btn1 = findViewById(R.id.Revealer);

        DisplayLocation = findViewById(R.id.locationGet);
        DisplayMP2_5 = findViewById(R.id.Particules2Get);
        DisplayMP10 = findViewById(R.id.Particules10Get);
        DisplayCO2 = findViewById(R.id.CarbonGet);
        DisplayStatus = findViewById(R.id.StatusGet);

        listView.setVisibility(View.GONE);

        States = method.FillList(Data);


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, States);
        listView.setAdapter(adapter);

    //  filtra enquanto o usuario digita
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    // Se apagar tudo, mostra todas as opções
                    adapter.getFilter().filter("");
                } else {
                    adapter.getFilter().filter(newText);
                }
                return false;
            }
        });

        // Quando o usuário clica no campo de busca, mostra toda a lista
        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                DisplayLocation.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                adapter.getFilter().filter(""); // Mostra todas as opções
            } else {
                listView.setVisibility(View.GONE);
                DisplayLocation.setVisibility(View.VISIBLE);
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {

            String selectedItem = (String) parent.getItemAtPosition(position);
            searchView.setQuery(selectedItem, false);
            searchView.clearFocus();
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                String state = searchView.getQuery().toString();

                for (String trial : Data.keySet()) {
                    if (trial.equalsIgnoreCase(state)) {
                        DisplayLocation.setText("Buscando...");
                        DisplayMP2_5.setText("Buscando...");
                        DisplayMP10.setText("Buscando...");
                        DisplayCO2.setText("Buscando...");
                        DisplayStatus.setText("Buscando...");

                        List<String> Answers = Data.get(trial);

                        searchView.setQuery("", false);
                        new Thread(() -> {


                            ArrayList<String> result = client.findPolution(Answers.get(0), Answers.get(1));

                            //esse aqui é um caso a parte porque é o parametro para medir a qualidade do AR
                            int compare = Integer.parseInt(result.get(2));

                            runOnUiThread(() -> {
                                // Atualiza a interface com o resultado da API
                                DisplayLocation.setText(trial.toUpperCase(Locale.ROOT));
                                DisplayMP2_5.setText(result.get(0)+" μg/m³");
                                DisplayMP10.setText(result.get(1)+" μg/m³");
                                DisplayCO2.setText(result.get(2)+" ppm");
                                if(compare<=800) {
                                    DisplayStatus.setText("Boa");
                                } else if (compare>800 && compare<=1000){
                                    DisplayStatus.setText("Aceitavel");
                                } else if (compare>1000 && compare<=2000){
                                    DisplayStatus.setText("Ruim");
                                } else if (compare>2000){
                                    DisplayStatus.setText("Perigosa");
                                }

                            });
                        }).start();

                        break;
                    } else {
                        DisplayLocation.setText("Escolha um Estado");
                        DisplayMP2_5.setText("...");
                        DisplayMP10.setText("...");
                        DisplayCO2.setText("...");
                        DisplayStatus.setText("...");
                    }
                }
            }
        });
    }
}