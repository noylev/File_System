package filesystem;
import java.util.*;
public class Directory extends SystemObject {

    private Map<String, SystemObject> files = new HashMap<String,SystemObject>();

    public Directory(String parentDirName, String name){
        super(parentDirName, name);
    }

    protected Map<String, SystemObject> getFiles(){
        return this.files;
    }
    protected String getParentName(){
        return this.parentDir;
    }

    protected Directory getDir(String dirName){
        if (this.name == dirName){
            return this;
        }
        else{
            Iterator it = this.files.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry pair = (Map.Entry)it.next();
                if(pair.getKey() == dirName){
                    return (Directory)pair.getValue();
                }
                else{
                    Object result = pair.getValue();
                    if(result instanceof Directory) {
                        return ((Directory)result).getDir(dirName);

                    }
                }
            }
        }
        return null;
    }
}
