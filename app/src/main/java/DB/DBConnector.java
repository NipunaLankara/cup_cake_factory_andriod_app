package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnector  extends SQLiteOpenHelper {

    public DBConnector (Context context) {
        super(context,"cupcake_factory_new_database4",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table1_query = "CREATE TABLE user_details(user_id VARCHAR PRIMARY KEY NOT NULL,user_name VARCHAR NOT NULL,password VARCHAR,user_type VARCHAR)";
        db.execSQL(table1_query);

        String table2_query = "CREATE TABLE category_details(category_id VARCHAR PRIMARY KEY NOT NULL,category_name VARCHAR)";
        db.execSQL(table2_query);

        String table3_query = "CREATE TABLE cupcake_details(cupcake_id VARCHAR PRIMARY KEY NOT NULL,cupcake_name VARCHAR,category_id VARCHAR,price INTEGER NOT NULL,quantity INTEGER,FOREIGN KEY(category_id)REFERENCES category_details(category_id))";
        db.execSQL(table3_query);

        String table4_query = "CREATE TABLE order_details(order_id VARCHAR PRIMARY KEY NOT NULL,cupcake_id VARCHAR NOT NULL,user_id VARCHAR NOT NULL,quantity INTEGER,total INTEGER,FOREIGN KEY(cupcake_id) REFERENCES cupcake_details(cupcake_id))";
        db.execSQL(table4_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
