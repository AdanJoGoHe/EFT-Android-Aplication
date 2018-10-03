package com.example.pareddehierro.eftaplication;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class FragmentArmasP extends Fragment
{
    Button buttonRequestMessage;
    TextView textViewMessage = null;
    private Spinner mSpinner;
    ArrayList<String> elementos;


    public void requestMessage()
    {
        String url = "http://192.168.201.4:40000/api/armas";
        Log.i("Prueba", "justo antes de la conexion");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        URL url;
                        String token[] = {"Nombre","Distribuidor","Calibre","Rol","Descripcion"};
                        StringBuilder tokenBuilder = new StringBuilder();
                        Log.i("Prueba", response.toString());
                        textViewMessage.setText("");
                        try
                        {
                            for(int i=0;i<token.length;i++)
                            {
                               // tokenBuilder.append(" <b>"+token[i] +" </b>: " + response.getJSONObject(0).getString(token[i]) + "\n");
                                textViewMessage.append(Html.fromHtml(" <b>"+token[i] +" </b>: "));
                                textViewMessage.append(response.getJSONObject(0).getString(token[i]) + "\n");
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            tokenBuilder.delete(0,tokenBuilder.length());
                            tokenBuilder.append("Ha ocurrido un error.");
                        }
                        //Despues del try
                        //textViewMessage.setText(tokenBuilder.toString());
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.i("ERROR", error.toString());
                        System.out.println(error.toString() + " ERROR QUE GRACIOSO");
                        textViewMessage.setText(error.toString());
                    }
                });
        RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());
        rq.add(jsonArrayRequest);
    }

    public void requestList(final ArrayList<String> elementos)
    {
        String url = "http://192.168.201.4:40000/api/armas";
        Log.i("Prueba", "justo antes de la conexion");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        URL url;
                        String token[] = {"Nombre","Distribuidor","Calibre","Rol","Descripcion"};
                        StringBuilder tokenBuilder = new StringBuilder();
                        Log.i("Prueba", response.toString());
                        textViewMessage.setText("");
                        try
                        {
                            for(int i=0;i<response.length();i++)
                            {
                                elementos.add(response.getJSONObject(i).getString(token[0]) + "\n");
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            tokenBuilder.delete(0,tokenBuilder.length());
                            tokenBuilder.append("Ha ocurrido un error.");
                        }
                        //Despues del try
                        //textViewMessage.setText(tokenBuilder.toString());
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.i("ERROR", error.toString());
                        System.out.println(error.toString() + " ERROR QUE GRACIOSO");
                        textViewMessage.setText(error.toString());
                    }
                });

        RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());
        rq.add(jsonArrayRequest);
    }

    public void requestSpecificMessage(int X)
    {
        X = X-1;
        String url = "http://192.168.201.4:40000/api/armas/"+X;
        Log.i("Prueba", "justo antes de la conexion");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        URL url;
                        String token[] = {"Nombre","Distribuidor","Calibre","Rol","Descripcion"};
                        StringBuilder tokenBuilder = new StringBuilder();
                        Log.i("Prueba", response.toString());
                        textViewMessage.setText("");
                        try
                        {
                            for(int i=0;i<token.length;i++)
                            {
                                // tokenBuilder.append(" <b>"+token[i] +" </b>: " + response.getJSONObject(0).getString(token[i]) + "\n");
                                textViewMessage.append(Html.fromHtml(" <b>"+token[i] +" </b>: "));
                                textViewMessage.append(response.getString(token[i]) + "\n");
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            tokenBuilder.delete(0,tokenBuilder.length());
                            tokenBuilder.append("Ha ocurrido un error.");
                        }
                        //Despues del try
                        //textViewMessage.setText(tokenBuilder.toString());
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.i("ERROR", error.toString());
                        System.out.println(error.toString() + " ERROR QUE GRACIOSO");
                        textViewMessage.setText(error.toString());
                    }
                });
        RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());
        rq.add(jsonObjectRequest);
    }

    private FragmentArmasViewModel mViewModel;

    public static FragmentArmasP newInstance()
    {
        return new FragmentArmasP();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_armas, container, false);

        llamadaAlServidor(v);
        creacionBotonDesplegable(v,savedInstanceState);

        return v;
    }

    private void creacionBotonDesplegable(View v, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mSpinner = v.findViewById(R.id.mSpinner);
        elementos = new ArrayList<>();
        elementos.add("N/A");
        requestList(elementos);
        ArrayAdapter adp=new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, elementos);
        mSpinner.setAdapter(adp);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Log.i("ITEMLISTA","He llegado a la lista :D");
                String elemento =(String) mSpinner.getAdapter().getItem(position);
                Toast.makeText(getActivity(),"Selectionaste: "+elemento+" Que esta en la posicion "+ position,Toast.LENGTH_SHORT).show();
                requestSpecificMessage(position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                Log.i("ITEMLISTA","No :D");
            }
        });

    }

    private void llamadaAlServidor(View v)
    {
        textViewMessage =(TextView) v.findViewById(R.id.textoInformacion);
        buttonRequestMessage =(Button) v.findViewById(R.id.botonInformacion);
        buttonRequestMessage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                requestMessage();
            }

        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentArmasViewModel.class);
        // TODO: Use the ViewModel
    }

}
