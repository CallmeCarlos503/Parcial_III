package com.unidad3.crud01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre, txtApellidos, txtTelefono, txtCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
    }
    public void Agregar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "parcial", null, 1);
        SQLiteDatabase BaseDatos = admin.getReadableDatabase();

        String Nombre = txtNombre.getText().toString();
        String Apellidos = txtApellidos.getText().toString();
        String Telefono = txtTelefono.getText().toString();
        String Correo = txtCorreo.getText().toString();

        try{
            if(!Nombre.isEmpty() && !Apellidos.isEmpty() && !Telefono.isEmpty() && !Correo.isEmpty())
            {
                ContentValues registro = new ContentValues();
                registro.put("nombre", Nombre);
                registro.put("apellidos", Apellidos);
                registro.put("telefono", Telefono);
                registro.put("correo", Correo);

                BaseDatos.insert("contactos", null, registro);
                BaseDatos.close();

                LimpiarCajas();
            }
            else
            {
                Toast.makeText(this, "LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "ERROR AL INGRESAR DATOS", Toast.LENGTH_LONG).show();
        }
    }
    public void ConsultarNombre(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "parcial", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String Nombre = txtNombre.getText().toString();
        if(!Nombre.isEmpty())
        {
            Cursor fila = BaseDatos.rawQuery(
                    "SELECT apellidos, telefono, correo " +
                    "FROM contactos " +
                    "WHERE nombre =" + Nombre, null);

            if(fila.moveToFirst())
            {
                //txtNombre.setText(fila.getString(0));
                txtApellidos.setText(fila.getString(0));
                txtTelefono.setText(fila.getString(1));
                txtCorreo.setText(fila.getString(2));
                BaseDatos.close();
            }
            else
            {
                Toast.makeText(this, "NOMBRE NO EXISTE", Toast.LENGTH_LONG).show();
                BaseDatos.close();
                LimpiarCajas();
            }
        }
        else
        {
            Toast.makeText(this, "NOMBRE VACIO", Toast.LENGTH_LONG).show();
        }
    }
    public void ConsultaApellido(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "parcial", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String Apellidos = txtApellidos.getText().toString();

        if(!Apellidos.isEmpty())
        {
            Cursor fila = BaseDatos.rawQuery(
                    "SELECT nombre, telefono, correo " +
                            "FROM contactos " +
                            "WHERE apellidos =" + Apellidos, null);

            if(fila.moveToFirst())
            {
                txtNombre.setText(fila.getString(0));
                //txtApellidos.setText(fila.getString(1));
                txtTelefono.setText(fila.getString(1));
                txtCorreo.setText(fila.getString(2));
                BaseDatos.close();
            }
            else
            {
                Toast.makeText(this, "APELLIDO NO EXISTE", Toast.LENGTH_LONG).show();
                BaseDatos.close();
                LimpiarCajas();
            }
        }
        else
        {
            Toast.makeText(this, "APELLIDO VACIO", Toast.LENGTH_LONG).show();
        }
    }
    public void ConsultarTelefono(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "parcial", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String Telefono = txtTelefono.getText().toString();

        if(!Telefono.isEmpty())
        {
            Cursor fila = BaseDatos.rawQuery(
                    "SELECT nombre, apellidos, correo " +
                            "FROM contactos " +
                            "WHERE telefono =" + Telefono, null);

            if(fila.moveToFirst())
            {
                txtNombre.setText(fila.getString(0));
                txtApellidos.setText(fila.getString(1));
                //txtTelefono.setText(fila.getString(2));
                txtCorreo.setText(fila.getString(2));
                BaseDatos.close();
            }
            else
            {
                Toast.makeText(this, "TELEFONO NO EXISTE", Toast.LENGTH_LONG).show();
                BaseDatos.close();
                LimpiarCajas();
            }
        }
        else
        {
            Toast.makeText(this, "TELEFONO VACIO", Toast.LENGTH_LONG).show();
        }
    }
    public void ConsultarCorreo(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "parcial", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String Correo = txtCorreo.getText().toString();

        if(!Correo.isEmpty())
        {
            Cursor fila = BaseDatos.rawQuery(
                    "SELECT nombre, apellidos, telefono " +
                            "FROM contactos " +
                            "WHERE correo =" + Correo, null);

            if(fila.moveToFirst())
            {
                txtNombre.setText(fila.getString(0));
                txtApellidos.setText(fila.getString(1));
                txtTelefono.setText(fila.getString(2));
                BaseDatos.close();
            }
            else
            {
                Toast.makeText(this, "CORREO NO EXISTE", Toast.LENGTH_LONG).show();
                BaseDatos.close();
                LimpiarCajas();
            }
        }
        else
        {
            Toast.makeText(this, "CORREO VACIO", Toast.LENGTH_LONG).show();
        }
    }
    public void Modificar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "parcial", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String Nombre = txtNombre.getText().toString();
        String Apellidos = txtApellidos.getText().toString();
        String Telefono = txtTelefono.getText().toString();
        String Correo = txtCorreo.getText().toString();

        if(!Nombre.isEmpty() && !Apellidos.isEmpty() && !Telefono.isEmpty() && !Correo.isEmpty())
        {
            ContentValues registro = new ContentValues();
                registro.put("nombre", Nombre);
                registro.put("apellidos", Apellidos);
                registro.put("telefono", Telefono);
                registro.put("correo", Correo);

                int cantidad = BaseDatos.update("contactos", registro, "nombre=" + Nombre, null);
                BaseDatos.close();

                if(cantidad == 1)
                {
                    Toast.makeText(this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                    LimpiarCajas();
                }
                else
                {
                    Toast.makeText(this, "ERROR AL MODIFICAR", Toast.LENGTH_LONG).show();
                }
        }
        else
        {
            Toast.makeText(this, "UN CAMPO ESTA VACIO", Toast.LENGTH_LONG).show();
        }
    }
    public void Eliminar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "parcial", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String Nombre = txtNombre.getText().toString();

        try
        {
            if(!Nombre.isEmpty())
            {
                int Cantidad = BaseDeDatos.delete("contactos", "nombre=" + Nombre, null);
                BaseDeDatos.close();
                LimpiarCajas();

                if(Cantidad == 1)
                {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this, "ERROR AL ELIMINAR", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(this, "NOMBRE VACIO", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {

        }
    }
    public void LimpiarCajas()
    {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }

    public void Finalizar(View view)
    {
        finish();
    }
}