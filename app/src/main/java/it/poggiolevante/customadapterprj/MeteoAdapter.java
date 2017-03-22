package it.poggiolevante.customadapterprj;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.poggiolevante.customadapterprj.data.Previsione;

/**
 * Created by root on 21/03/17.
 */

public class MeteoAdapter extends ArrayAdapter<Previsione> {

    List<Previsione> listaDati ;
    public MeteoAdapter(List<Previsione> dati,  Context context) {
       super(context, R.layout.layout_riga, dati);

        listaDati = dati;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Log.i("CustomPrj", " Position "+ position);
        Previsione p = listaDati.get(position);

        // recupero classe di utility per costruire la view
        LayoutInflater l = LayoutInflater.from(getContext());
        convertView = l.inflate(R.layout.layout_riga, null);
        // inserisco i dati nella view appena costruita
        // prelevandoli dal modello dei dati p

        TextView giornoOra = (TextView)convertView.findViewById(R.id.giornoOra);
        TextView temperatura = (TextView)convertView.findViewById(R.id.temperatura);
        TextView umidita = (TextView)convertView.findViewById(R.id.umidita);
        ImageView tempo = (ImageView)convertView.findViewById(R.id.cielo);

        giornoOra.setText(p.getGiorno() + " h:"+p.getOra());
        temperatura.setText(p.getTemp()+"°");
        umidita.setText(p.getUmidita()+"%");

        switch (p.getCielo()) {
            case 0:
                tempo.setImageResource(R.drawable.ic_sole);
                break;
            case 1:
                tempo.setImageResource(R.drawable.ic_nuvola);
                break;
            default:
                tempo.setImageResource(R.drawable.ic_pioggia);

        }


        return convertView;

    }
}
