package filesystem;
import java.util.Date;
public abstract class SystemObject {

    protected String name;
    protected String parentDir;
    protected Date date_created;
    protected String path;

    public SystemObject(String parentDirName, String name){
        if(name.length()>32){
            System.out.println("Name is too long! make sure name is less or equal than 32 characters");
            System.exit(0);
        }
        this.name = name;
        this.parentDir = parentDirName;
        this.date_created = new Date();

    }
    protected String getPath(){
        return this.path;
    }
    protected void setPath(String path){
        this.path = path;
    }
    public String toString(){
        return this.path+" || "+this.name+ " || "+ this.date_created.toString();
    }
}
