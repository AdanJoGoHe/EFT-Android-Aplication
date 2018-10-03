package com.example.pareddehierro.eftaplication;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URL;

public class FragmentArmasP extends Fragment
{
    Button buttonRequestMessage;
    TextView textViewMessage = null;

    public void requestMessage()
    {
        String url = "http://192.168.1.76:40000/api/armas";
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
                        System.out.println(response.toString() + " DIOSTIOQUEGRACIOSO");
                        try
                        {                            for(int i=0;i<token.length;i++)
                        {
                            tokenBuilder.append(token[i] + " : " + response.getJSONObject(0).getString(token[i]) + "\n");
                        }



                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            tokenBuilder.delete(0,tokenBuilder.length());
                            tokenBuilder.append("Ha ocurrido un error.");
                        }
                        textViewMessage.setText(tokenBuilder.toString());
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

        textViewMessage =(TextView) v.findViewById(R.id.textoInformacion);
        buttonRequestMessage =(Button) v.findViewById(R.id.botonInformacion);
        buttonRequestMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                requestMessage();
            }

        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentArmasViewModel.class);
        // TODO: Use the ViewModel
    }

}
