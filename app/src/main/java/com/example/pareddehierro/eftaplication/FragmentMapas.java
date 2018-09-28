package com.example.pareddehierro.eftaplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMapas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentMapas extends Fragment
{
    Button buttonRequestMessage;
    TextView textViewMessage = null;

    private static String aux;

    public void requestMessage()
    {
        String url = "http://192.168.1.76:40000/api/armas/1";
        Log.i("Prueba", "justo antes de la conexion");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.i("Prueba", response.toString());
                        System.out.println(response.toString() + " DIOSTIOQUEGRACIOSO");
                        textViewMessage.setText(response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.i("ERROR", error.toString());
                        System.out.println(error.toString() + " ERROR DIOSTIOQUEGRACIOSO");

                        textViewMessage.setText(error.toString());
                    }
                });
        RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());
        rq.add(jsonObjectRequest);
    }


    private OnFragmentInteractionListener mListener;

    public FragmentMapas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_mapas, container, false);

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


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
