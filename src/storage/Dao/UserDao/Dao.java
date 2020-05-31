package storage.Dao.UserDao;

import java.io.File;
import java.io.IOException;
import java.util.List;

/** Interface implemented by both dao classes
 * @author Tudor */

public interface Dao<T> {

     List<T> getAll(File file)throws IOException;
}
