package espol.followpets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by USER on 31/07/2016.
 */
public class ConexionBD {
    private SQLiteDatabase db;
    private Context nContext;
    private AdminSQLiteOpenHelper objBD;
    //Constructor de la Clase
    //Recibe como parámetro una variable de Tipo contexto
    // Esto debido a que Para acceder o crear la BD lo pide la Clase SQLiteOpenHelper
    public ConexionBD(Context c){
        nContext=c;

    }

    public void abrirConexion(){
        objBD =new AdminSQLiteOpenHelper(nContext,"BDpersonas",null,1 );
        db=objBD.getWritableDatabase();
    }

    public void cerrarConexion(){
        db.close();
    }

    public boolean insertar(int ci ,String nombreusuario ,String pnombre, String snombre,String papellido, String sapellido,
                            int phone ,String email,String  contrasena,String tipo ){
        boolean resultado=false;

        try{
            String query="INSERT INTO personas( ci , nombreusuario , pnombre,  snombre, papellido,  sapellido," +
                    "                             phone , email,  contrasena, tipo) VALUES("+ci+",'"+nombreusuario+"','"+
                    pnombre+"','"+snombre+"','"+papellido+"','"+sapellido+"','"+phone+"','"+email+"','"+contrasena+"','"+tipo+"')";

            db.execSQL(query);
            resultado=true;
            return resultado;
        }
        catch (Exception e){
            resultado=false;
            return resultado;
        }
    }
}