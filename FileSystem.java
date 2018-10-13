package filesystem;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class FileSystem {
    Root root = Root.getInstance();
    private Map<String, SystemObject> filesystem = root.getFileSystem();

    public void addFile(String parentDirName, String fileName, int fileSize) {
        File file = new File(parentDirName, fileName, fileSize);
        Directory rootDir = (Directory)filesystem.get("root");
        Directory dir = rootDir.getDir(parentDirName);
        String parent_path = dir.getPath();
        file.setPath(parent_path + "\\" + fileName);
        dir.getFiles().put(fileName, file);
        System.out.println("file was added with name " + fileName);
    }

    public void addDir(String parentDirName, String dirName) {
        Directory root = (Directory) filesystem.get("root");
        Directory dir = new Directory(parentDirName, dirName);
        Directory parent = root.getDir(parentDirName);

        if (parent != null) {
            String parent_path = parent.getPath();
            dir.setPath(parent_path + "\\" + dirName);
            parent.getFiles().put(dirName, dir);
            System.out.println("directory was added with name " + dirName);
        } else {
            throw new NoSuchElementException("No Such Parent Folder exists!!");
        }
    }


    public void delete(String name) {
        if(name == "root"){
            System.out.println("root can not be deleted!");
        }
        else if (filesystem != null) {
            Directory root = (Directory) filesystem.get("root");
            Directory to_delete = root.getDir(name);
            if (to_delete != null) {
                String parent_name = to_delete.getParentName();
                Directory parent_dir = root.getDir(parent_name);
                parent_dir.getFiles().remove(name);
                System.out.println(name + " was removed from file system");

            }
            else{throw new NoSuchElementException("There is no such file or directory: "+name);}
        }
    }

    public void showFileSystem() {
        if(filesystem == null){
            System.out.println("No file system");
        }
        else{
            Directory root = (Directory) filesystem.get("root");
            System.out.println(root.getPath());
            showFolder(root);
        }
    }


    public void showFolder(Directory directory) {
        Iterator it = directory.getFiles().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Object temp = pair.getValue();
            if (temp instanceof Directory) {
                System.out.println(temp.toString());
                showFolder((Directory)temp);
            } else if (temp instanceof File) {
                System.out.println(temp.toString());
            }
        }
    }

    public static void main(String[] args) {

        FileSystem system = new FileSystem();
        system.addDir("root", "C");
        system.addDir("C", "Noy");
        system.addFile("Noy","CV.txt",7);
        //system.addFile("Noy","name is too longgggggggggggggggggg",7);
        system.addFile("Noy","trip_to_paris.img",7);
        system.addFile("C","work.xml",6);
        system.showFileSystem();
        system.delete("Noy");
        system.delete("root");
        //system.delete("made up folder");


        system.showFileSystem();

    }
}

