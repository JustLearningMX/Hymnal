package com.adonaiapps.gloriatriunfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    private Context context=this;
    private EditText met_buscar;
    private ListView lista;
    public static DrawerLayout drawerLayout;
    private ArrayList<itemsMenu> array;
    private FrameLayout contenedor_principal;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        configurarObjetos();

        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.mipmap.ic_more,
                R.string.drawer_open,
                R.string.drawer_close){

            public void onDrawerClosed(View view){
                getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView){
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu();
            }

        };

        drawerLayout.setDrawerListener(toggle);
    }

    public void configurarObjetos(){

        contenedor_principal=(FrameLayout) findViewById(R.id.contenedor_principal);
        lista = (ListView) findViewById(R.id.drawer);
        //et_buscar = (EditText) findViewById(R.id.et_buscar); //buscador en el listview

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //le ponemos el header
        View header=getLayoutInflater().inflate(R.layout.header_menu, null);
        lista.addHeaderView(header,null,false);

        //Lista de objetos
        array = new ArrayList<itemsMenu>();
        llenarListaMenu(array);

        //ADAPTER
        final MenuAdapter adaptador = new MenuAdapter(this, array);
        lista.setAdapter(adaptador);

        lista.setTextFilterEnabled(true);  //habilitador filtrado

        met_buscar = (EditText) findViewById(R.id.et_buscar); //buscador en el listview

        // Add Text Change Listener to EditText
        met_buscar.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                String text = met_buscar.getText().toString().toLowerCase(Locale.getDefault());
                adaptador.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        //PARA QUE SEA POR DEFECTO EL FRAGMENT1
        seleccionarItem(1);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                seleccionarItem(position);
            }

        });

    }
      //SELECCION DE FRAGMENT
    @SuppressLint("ResourceAsColor")
    public void seleccionarItem(int posicion){
        Fragment fragment=null;
        boolean salida=false;
        switch (posicion) {
            // BOTON PLAY, PRIMERA POSICION = 0
            case 1:
                fragment = new himno0_fg();
                break;
            case 2:
                fragment = new himno1_fg();
                break;
            case 3:
                fragment = new himno2_fg();
                break;
            case 4:
                fragment = new himno3_fg();
                break;
            case 5:
                fragment = new himno4_fg();
                break;
            case 6:
                fragment = new himno5_fg();
                break;
            case 7:
                salida=true;
                //MARCAMOS EL CLICKADO
                lista.setItemChecked(posicion, true);
                drawerLayout.closeDrawer(lista);
                finish();
        }
        //SI SE SALE DE LA APLICACION, AL HACER EL CAMBIO DE FRAGMENT DARIA NULLPOINTEREXCEPTION
        if (salida==false){
            //HACEMOS USO DE LA LIBRERIA
            FragmentManager fragmentManager=getSupportFragmentManager();
            //REEMPLAZAMOS
            fragmentManager.beginTransaction().replace(R.id.contenedor_principal, fragment).commit();
            //MARCAMOS EL CLICKADO
            lista.setItemChecked(posicion, true);
            drawerLayout.closeDrawer(lista);
        }
    }

    //LLENAR LISTA DE OBJETOS DEL MENU
    public void llenarListaMenu(ArrayList<itemsMenu> arraydir){
        itemsMenu item;
        // Introduzco los elementos
        item = new itemsMenu(1, "La Doxologia", "A Dios el Padre",getResources().getDrawable(R.mipmap.ic_launcher));
        arraydir.add(item);
        item = new itemsMenu(2, "El Aposento Alto", "Himno 1",getResources().getDrawable(R.mipmap.ic_launcher));
        arraydir.add(item);
        item = new itemsMenu(3, "Lluvias de Gracia","Himno 2",getResources().getDrawable(R.mipmap.ic_launcher));
        arraydir.add(item);
        item = new itemsMenu(4, "Jesus Vendra Otra Vez","Himno 3",getResources().getDrawable(R.mipmap.ic_launcher));
        arraydir.add(item);
        item = new itemsMenu(5, "Dulce Comunion", "Himno 4",getResources().getDrawable(R.mipmap.ic_launcher));
        arraydir.add(item);
        item = new itemsMenu(6, "Desde Que Salvo Soy", "Himno 5",getResources().getDrawable(R.mipmap.ic_launcher));
        arraydir.add(item);
        item = new itemsMenu(7, "Fin", "Salir de la aplicacion",getResources().getDrawable(R.mipmap.ic_launcher));
        arraydir.add(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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

        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Active the toggle with the icon
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }
}
