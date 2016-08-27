package facci.com.proyectomfdg;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Autora:Delgado Guerrero María Fernanda
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NOMBRE="CNE_MDFG";
    public static final String TABLA_NOMBRE="VOTANTES_MDFG";

    public static final String COL_1="ID";
    public static final String COL_2="NOMBRE";
    public static final String COL_3="APELLIDO";
    public static final String COL_4="RECINTOELECTORAL";
    public static final String COL_5="AÑONACIMIENTO";

    public DBHelper(Context context) {
        super(context,DB_NOMBRE, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT , %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER",TABLA_NOMBRE,COL_2,COL_3,COL_4,COL_5));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLA_NOMBRE));
        onCreate(db);
    }
    public boolean insertar(String nombre,String apellido, int recintoelectoral,int añonacimiento){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recintoelectoral);
        contentValues.put(COL_5,añonacimiento);
        long resultado= db.insert(TABLA_NOMBRE,null,contentValues);

        if (resultado== -1 )
            return false;
        else
            return true;
    }
    public Cursor selectVerTodos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s",TABLA_NOMBRE),null);
        return  res;
    }
    public boolean modificar(String id,String nombre,String apellido,int recintoelectoral, int añonacimiento){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recintoelectoral);
        contentValues.put(COL_5,añonacimiento);
        db.update(TABLA_NOMBRE,contentValues,"id = ?",new String[]{id});
        return true;
    }
    public Integer eliminarRegistro(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLA_NOMBRE,"id = ?",new String[]{id});


    }
}


