package espol.followpets;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class sesion extends AppCompatActivity {

    private EditText password, ci;
    private RadioButton r1,r2;
    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        password=(EditText)findViewById(R.id.loginpass);
        ci=(EditText)findViewById(R.id.loginci);
        r1=(RadioButton) findViewById(R.id.optParticipante);
        r2=(RadioButton) findViewById(R.id.optPatrocinador);
    }


    public void login(View v) {
        String tipo="Participante";
        if (r2.isChecked()==true) {
            tipo="Patrocinador";
        }
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                tipo, null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        int codid=Integer.parseInt(ci.getText().toString());
        String contr = password.getText().toString();
        Cursor fila = bd.rawQuery(
                "SELECT ci,contrasena " +
                        "FROM personas WHERE usuario="+codid,null);
        if(fila.moveToFirst()==true){
            String texto ="Esi";
            Toast toast = Toast.makeText(this, texto, Toast.LENGTH_LONG);
            toast.show();
            //capturamos los valores del cursos y lo almacenamos en variable
            int usua=Integer.parseInt(fila.getString(0));
            //String pass=fila.getString(1);
            //preguntamos si los datos ingresados son iguales
            if (codid==usua){
                //si son iguales entonces vamos a otra ventana
                //Menu es una nueva actividad empty
                Intent ven=new Intent(this,Acercade.class);
                startActivity(ven);
                //limpiamos las las cajas de texto
                ci.setText("");
                password.setText("");
                String text ="Error al Agregar Elemento";
                Toast toas = Toast.makeText(this, text, Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
