package com.adonaiapps.gloriatriunfo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

    public class MenuAdapter extends BaseAdapter implements Filterable{

	List<String> arrayList;
	List<String> mOriginalValues; // Original Values

	  protected Activity activity;
	    //ARRAYLIST CON TODOS LOS ITEMS
	    protected ArrayList<itemsMenu> items;
	     
	    //CONSTRUCTOR
	    public MenuAdapter(Activity activity, ArrayList<itemsMenu> items) {
	        this.activity = activity;
	        this.items = items;
	      }

	//CUENTA LOS ELEMENTOS
	    @Override
	    public int getCount() {
	        return items.size();
	    }
	    //DEVUELVE UN OBJETO DE UNA DETERMINADA POSICION
	    @Override
	    public Object getItem(int arg0) {
	        return items.get(arg0);
	    }
	    //DEVUELVE EL ID DE UN ELEMENTO
	    @Override
	    public long getItemId(int position) {
	        return items.get(position).getId();
	    }
	    //METODO PRINCIPAL, AQUI SE LLENAN LOS DATOS
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        // SE GENERA UN CONVERTVIEW POR MOTIVOS DE EFICIENCIA DE MEMORIA
	     //ES UN NIVEL MAS BAJO DE VISTA, PARA QUE OCUPEN MENOS MEMORIA LAS 
	 
	        View v = convertView;
	        //ASOCIAMOS LA VISTA AL LAYOUT DEL RECURSO XML DONDE ESTA LA BASE DE 
	        if(convertView == null){
	            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = inf.inflate(R.layout.item_layout, null);
	        }
	  
	        itemsMenu dir = items.get(position);
	        //RELLENAMOS LA IMAGEN Y EL TEXTO
	        ImageView foto = (ImageView) v.findViewById(R.id.item_foto);
	        foto.setImageDrawable(dir.getImg());
	        TextView txtPrinc = (TextView) v.findViewById(R.id.item_nombre);
	        txtPrinc.setText(dir.getNombre());
	        TextView txtSecund = (TextView) v.findViewById(R.id.item_descripcion);
	        txtSecund.setText(dir.getDescripcion());
	         
	        // DEVOLVEMOS VISTA
	        return v;
	    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                arrayList = (List<String>) results.values; // has the filtered values
                Log.v("arrayList", String.valueOf(arrayList));
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                Filter.FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                List<String> FilteredArrList = new ArrayList<String>();

                if (mOriginalValues == null)
                {
                    Log.v("mOriginalValues", String.valueOf(mOriginalValues));
                    mOriginalValues = new ArrayList<String>(arrayList); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                }
                else
                {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i);
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(data);
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }


}
