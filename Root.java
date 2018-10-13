package filesystem;
import java.util.HashMap;
import java.util.Map;

public class Root {
    private Map<String, SystemObject> filesystem = new HashMap<String,SystemObject>();
    private static Root instance = null;
    private Root() {
        Directory root = new Directory("","root");
        root.setPath("root");
        filesystem.put("root",root);
        // Exists only to defeat instantiation.
    }
    public static Root getInstance() {
        if(instance == null) {
            instance = new Root();
        }
        return instance;
    }

    public Map<String, SystemObject> getFileSystem(){
        return filesystem;
    }
}