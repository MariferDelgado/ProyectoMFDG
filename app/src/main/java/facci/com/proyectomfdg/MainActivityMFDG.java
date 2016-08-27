package facci.com.proyectomfdg;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityMFDG extends AppCompatActivity {
    DBHelper dbSQLite;
    EditText txtID, txtNombre,txtApellido,txtRecintoElectoral,txtAñoNacimiento;
    Button btnInsertar;
    Button btnModificar;
    Button btnEliminar;
    Button btnverTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mfdg);
        dbSQLite =new DBHelper(this);
    }
    public void insertarClick (View v){
        txtID=(EditText)findViewById(R.id.txtID);
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtApellido=(EditText)findViewById(R.id.txtApellido);
        txtRecintoElectoral= (EditText)findViewById(R.id.txtRecintoElectoral);
        txtAñoNacimiento= (EditText)findViewById(R.id.txtAñoNacimiento);

        boolean Insertado = dbSQLite.insertar(txtNombre.getText().toString(),txtApellido.getText().toString(),Integer.parseInt(txtRecintoElectoral.getText().toString()),Integer.parseInt(txtAñoNacimiento.getText().toString()));
        if (Insertado)
            Toast.makeText(MainActivityMFDG.this,"Datos Ingresados Correctamente",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityMFDG.this,"Lo sentimos ocurrió un error ",Toast.LENGTH_SHORT).show();
    }

    public void verTodosClick(View v){
        Cursor res = dbSQLite.selectVerTodos();
        if(res.getCount() == 0){
            mostrarMensaje("Error","No se encontraron registros");
            return;
        }
        StringBuffer buffer= new StringBuffer( );
        while (res.moveToNext()){
            buffer.append("Id :"+res.getString(0)+"/n");
            buffer.append("Nombre :"+res.getString(1)+"/n");
            buffer.append("Apellido :"+res.getString(2)+"/n");
            buffer.append("RecintoElectoral :"+res.getInt(3)+"/n/n");
            buffer.append("AñoNacimiento :"+res.getInt(4)+"/n/n");
        }
        mostrarMensaje("Registro",buffer.toString());
    }
    public void mostrarMensaje(String titulo,String Mensaje){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }
    public void modificarClick(View v){
        txtID = (EditText) findViewById(R.id.txtID);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtRecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAñoNacimiento = (EditText) findViewById(R.id.txtAñoNacimiento);

        boolean Actualizado = dbSQLite.modificar(txtID.getText().toString(),txtNombre.getText().toString(),txtApellido.getText().toString(),Integer.parseInt(txtRecintoElectoral.getText().toString()),Integer.parseInt(txtAñoNacimiento.getText().toString()));
        if (Actualizado == true){
            Toast.makeText(MainActivityMFDG.this,"Registro Actualizado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityMFDG.this,"ERROR: Registro NO Actualizado",Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarClick(View v){
        txtID = (EditText) findViewById(R.id.txtID);

        Integer registrosEliminados = dbSQLite.eliminarRegistro(txtID.getText().toString());

        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivityMFDG.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityMFDG.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }
    }
}




