package ff.findyourfriend.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lmartinr on 22/04/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "findYourFriends.db";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create user table
        String CREATE_USER_TABLE = "CREATE TABLE " + ColumnsContract.Tables.USERS + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnsContract.Users.NAME + " TEXT, " +
                ColumnsContract.Users.SURNAME + " TEXT, " +
                ColumnsContract.Users.PHOTO + " TEXT, " +
                ColumnsContract.Users.TELEPHONE + " TEXT, " +
                ColumnsContract.Users.LATITUDE + " DOUBLE, " +
                ColumnsContract.Users.LONGITUDE + " DOUBLE)";

        // create users table
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older users table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ColumnsContract.Tables.USERS);

        // create fresh users table
        this.onCreate(db);
    }
}
