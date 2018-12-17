package com.example.daniel.contactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    static List<Contacto> lista = new ArrayList<>();
    Adaptador adaptador;
    private final int READ_CONTACTOS = 1;
    final private int REQUEST_CODE_PERMISSIONS = 2;
    int permiso;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTOS);

        recyclerView = findViewById(R.id.recyclerView);

        permisos();
    }

    public void llenarLista(){
        lista = getListaContactos();
        for(Contacto c : lista){
            List<String> telefonos = getListaTelefonos(c.getId());
            if (telefonos.size() > 0){
                c.setTelefono(telefonos.get(0));
            }
        }
        Log.v("TAG", "Tamaño lista " + lista.size());
    }

    public List<Contacto> getListaContactos(){
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String proyeccion[] = null;
        String seleccion = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = ? and " +
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "= ?";
        String argumentos[] = new String[]{"1","1"};
        String orden = ContactsContract.Contacts.DISPLAY_NAME + " collate localized asc";
        Cursor cursor = getContentResolver().query(uri, proyeccion, seleccion, argumentos, orden);
        int indiceId = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int indiceNombre = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        List<Contacto> lista = new ArrayList<>();
        Contacto contacto;
        while(cursor.moveToNext()){
            contacto = new Contacto();
            contacto.setId(cursor.getLong(indiceId));
            contacto.setNombre(cursor.getString(indiceNombre));
            lista.add(contacto);
        }
        return lista;
    }

    public List<String> getListaTelefonos(long id){
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String proyeccion[] = null;
        String seleccion = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
        String argumentos[] = new String[]{id+""};
        String orden = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Cursor cursor = getContentResolver().query(uri, proyeccion, seleccion, argumentos, orden);
        int indiceNumero = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        List<String> lista = new ArrayList<>();
        String numero;
        while(cursor.moveToNext()){
            numero = cursor.getString(indiceNumero);
            lista.add(numero);
        }
        return lista;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void permisos(){
        permiso = checkSelfPermission(Manifest.permission.READ_CONTACTS);
        if(permiso != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_PERMISSIONS);
        }
        else{
            llenarLista();
            adaptador = new Adaptador(getApplicationContext(), lista);
            recyclerView.setAdapter(adaptador);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adaptador.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10){
            if(resultCode == RESULT_OK){
                Intent i = getIntent();
                Contacto contactoEditado = i.getParcelableExtra("cEditado");
                long id = contactoEditado.getId();
                String nombre = contactoEditado.getNombre();
                String telefono = contactoEditado.getTelefono();
                for (Contacto c : lista) {
                    if (c.getId() == id) {
                        c.setNombre(nombre);
                        c.setTelefono(telefono);
                        adaptador.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}
