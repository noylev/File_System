package filesystem;
public class File extends SystemObject {

    private long size;

    public File(String parentDirname, String name, long size){
        super(parentDirname,name);
        this.size = size;
    }
    public String toString(){
        return super.toString()+" || size "+this.size;
    }
}
