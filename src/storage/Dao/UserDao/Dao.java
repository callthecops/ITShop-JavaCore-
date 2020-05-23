package storage.Dao.UserDao;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

     List<T> getAll(File file)throws IOException;
}
