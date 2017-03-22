package it.poggiolevante.customadapterprj;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.poggiolevante.customadapterprj.data.MyJsonReader;
import it.poggiolevante.customadapterprj.data.Previsione;
import it.poggiolevante.customadapterprj.threads.RichiestaHttpGet;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_FILE="TempSalvate";
     List<Previsione> listaPrevisioni = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView lista = (ListView)findViewById(R.id.lista);

        SharedPreferences preferences = getSharedPreferences(PREF_FILE, 0);
        float f = preferences.getFloat("temperatura", -299);
        String d = preferences.getString("data", "");

        final TextView tempSalvata = (TextView) findViewById(R.id.tempSalvata);
        if (f <= -299) // non ho trovato il dato
         {
             tempSalvata.setText("Temperatura non salvata");
         }
         else {
            tempSalvata.setText("Temperatura: "+ f+ "° salvata il "+ d);
        }

// crea / estrai la lista delle previsioni


/*
        listaPrevisioni.add(
                new Previsione("22 marzo", "18:30", "Foggia", 15.7, 0.4, 0));
        listaPrevisioni.add(
                new Previsione("22 marzo", "22:30", "Foggia", 12.7, 0.8, 1));
        listaPrevisioni.add(
                new Previsione("23 marzo", "03:30", "Foggia", 11.7, 0.7, 1));
        listaPrevisioni.add(
                new Previsione("23 marzo", "09:30", "Foggia", 16.7, 0.7, 2));
*/
        Handler myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
              Bundle bundle = msg.getData();
                if (bundle.getBoolean("success")) {
                    MyJsonReader.jsonString = bundle.getString("result");
                    String citta = MyJsonReader.parse();
                    TextView txtCitta = (TextView)findViewById(R.id.citta);
                    txtCitta.setText(" Previsioni per "+ citta);

                    listaPrevisioni = MyJsonReader.listaPrevisioni;

                    MeteoAdapter adapter = new MeteoAdapter(listaPrevisioni, getApplicationContext());
                    lista.setAdapter(adapter);


                }
            }
        };


        RichiestaHttpGet richiestaDati = new RichiestaHttpGet(
     "http://api.openweathermap.org/data/2.5/forecast?lat=40.6565901&lon=17.69125&units=metric&appid=e4dc66b316a46b96910367017313d1d7"
        );
        richiestaDati.doRequest(myHandler);







        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Date d = new Date();
                Previsione p = listaPrevisioni.get(i);
                float f = (float)p.getTemp();
                SharedPreferences preferences = getSharedPreferences(PREF_FILE, 0);
                preferences.edit().
                        putFloat("temperatura", f).
                        putString("data", d.toLocaleString()).
                        commit();
                tempSalvata.setText("Nuova Temperatura: "+ f+ "° salvata il "+ d);

            }
        });



    }
}
