package com.example.daniel.contactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Editar extends AppCompatActivity {

    private EditText editText, editText2;
    private Button btGuardar, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        btGuardar = findViewById(R.id.button);
        btCancel = findViewById(R.id.button2);

        Intent i = getIntent();
        final Contacto contacto = i.getParcelableExtra("contacto");
        editText.setText(contacto.getNombre());
        editText2.setText(contacto.getTelefono());

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Contacto editado= new Contacto(contacto.getId(), editText.getText().toString(), editText2.getText().toString());
                intent.putExtra("cEditado", editado);
                intent.putExtra("editado", true);
                //setResult(RESULT_OK);
                startActivity(intent);
            }
        });
    }
}
