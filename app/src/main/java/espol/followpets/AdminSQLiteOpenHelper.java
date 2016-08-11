package espol.followpets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 30/07/2016.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE personas(ci integer primary key,nombreusuario text,pnombre text, snombre text, papellido text, sapellido text, " +
                " phone integer, email text, contrasena text, tipo text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS personas");
        db.execSQL("CREATE TABLE personas(ci integer primary key,nombreusuario text,pnombre text, snombre text, papellido text, sapellido text, " +
                " phone integer, email text, contrasena text, tipo text)");
    }

}
