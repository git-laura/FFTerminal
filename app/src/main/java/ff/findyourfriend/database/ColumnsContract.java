package ff.findyourfriend.database;

import ff.findyourfriend.model.Coordenate;

/**
 * Created by lmartinr on 22/04/16.
 */
public class ColumnsContract {

    public interface Tables {
        String USERS = "users";
    }

    public interface Users {
        String NAME = "name";
        String SURNAME = "surname";
        String PHOTO = "photo";
        String TELEPHONE = "telephone";
        String LATITUDE = "latitude";
        String LONGITUDE = "longitude";
    }
}
