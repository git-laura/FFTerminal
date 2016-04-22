package ff.findyourfriend.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import ff.findyourfriend.model.User;

/**
 * Created by lmartinr on 22/04/16.
 */
public class SQLiteUsers {

    private SQLiteHelper helper;

    /**
     * Inserta el usuario pasado como parámetro en la BBDD
     *
     * @param user Usuario a añadir en la BBDD
     * @return int con el id que se le ha asignado
     * al usuario añadido
     */
    public int insert(User user) {
        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ColumnsContract.Users.NAME, user.getName());
        values.put(ColumnsContract.Users.SURNAME, user.getSurname());
        values.put(ColumnsContract.Users.PHOTO, user.getPhoto());
        values.put(ColumnsContract.Users.TELEPHONE, user.getTelephone());
        values.put(ColumnsContract.Users.LATITUDE, user.getCoordenate().getLatitude());
        values.put(ColumnsContract.Users.LONGITUDE, user.getCoordenate().getLongitude());

        int id = (int) database.insert(ColumnsContract.Tables.USERS, null, values);
        database.close();
        return id;
    }

}
