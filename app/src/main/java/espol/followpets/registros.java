package espol.followpets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class registros extends AppCompatActivity {
    private EditText username, ci, pn,sn,pa,sa,phone, email,contrasena ,password;
    private RadioButton r1,r2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        username=(EditText)findViewById(R.id.txtusername);
        pn=(EditText)findViewById(R.id.pnombre);
        sn=(EditText)findViewById(R.id.snombre);
        pa=(EditText)findViewById(R.id.papellido);
        sa=(EditText)findViewById(R.id.sapellido);
        ci=(EditText)findViewById(R.id.ci);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
        contrasena=(EditText)findViewById(R.id.txtPass);
        r1=(RadioButton) findViewById(R.id.optParticipante);
        r2=(RadioButton) findViewById(R.id.optPatrocinador);
        password=(EditText)findViewById(R.id.loginpass);
    }


    public void registrar(View v) {
        String tipo="Participante";
        if (r2.isChecked()==true) {
            tipo="Patrocinador";
        }
        //AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,tipo, null, 1);
        //SQLiteDatabase bd = admin.getWritableDatabase();
        Integer codid=Integer.parseInt(ci.getText().toString());
        String pnon = pn.getText().toString();
        String user = username.getText().toString();
        String snon = sn.getText().toString();
        String pap = pa.getText().toString();
        String sap = sa.getText().toString();
        Integer phon=Integer.parseInt(phone.getText().toString());
        String emai = email.getText().toString();
        String contr = contrasena.getText().toString();

        ConexionBD ObjCnx = new ConexionBD(this);
        ObjCnx.abrirConexion();
        if(ObjCnx.insertar(codid ,user ,pnon, snon,pap, sap, phon ,emai,contr,tipo)==true){
            String texto ="Elemento Agregado Correctamente";
            Toast toast = Toast.makeText(this, texto, Toast.LENGTH_LONG);
            toast.show();
        }
        else{
            String texto ="Error al Agregar Elemento";
            Toast toast = Toast.makeText(this, texto, Toast.LENGTH_LONG);
            toast.show();
        }

        //Cerramos Conexión
        ObjCnx.cerrarConexion();
        ci.setText("");
        pn.setText("");
        sn.setText("");
        pa.setText("");
        sa.setText("");
        phone.setText("");
        email.setText("");
        contrasena.setText("");
        r1.setSelected(false);
        r2.setSelected(false);
    }
    //ContentValues registro = new ContentValues();
      //  registro.put("ci", codid);
        //registro.put("nombreusuario", user);
        //registro.put("pnombre", pnon);
        //registro.put("snombre", snon);
        //registro.put("papellido", pap);
        //registro.put("sapellido", sap);
        ///registro.put("phone", phon);
        //registro.put("email", emai);
        //registro.put("contrasena", contr);
        //registro.put("tipo", tipo);
        //bd.insert(tipo, null, registro);
        //bd.close();


        //Toast.makeText(this, "Se cargaron los datos del "+tipo, Toast.LENGTH_SHORT).show();
        public void consulta(View v) {
            AdminSQLiteOpenHelper admini = new AdminSQLiteOpenHelper(this,"Bdpersonas", null, 1);
            SQLiteDatabase bd = admini.getWritableDatabase();
            String dni = password.getText().toString();
            Cursor fila = bd.rawQuery(
                    "select ci,contrasena  from personas where contrasena=" + dni
                            + "", null);
            if (fila.moveToFirst()) {
                String texto ="Esi";
                Toast toast = Toast.makeText(this, texto, Toast.LENGTH_LONG);
                toast.show();
                Intent ven=new Intent(this,Acercade.class);
                startActivity(ven);
            } else
                Toast.makeText(this, "No existe una persona con dicho dni",
                        Toast.LENGTH_SHORT).show();
            bd.close();

        }
    public void salir(View v) {
        finish();
    }
}
