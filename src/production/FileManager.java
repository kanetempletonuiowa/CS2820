// Kane Templeton
// FileManager.java

package production;

import java.io.IOException;
import java.io.PrintWriter;


public class FileManager {
    
    private String buildText;
    
    public FileManager() {
        buildText="";
    }
    
    public void createNewFile(String path, String text) {
        try{
            PrintWriter writer = new PrintWriter(path);
            writer.print(text);
            writer.close();
        } catch (IOException e) {
            Production.controls().output(path+": file not found.");
        }
    }
    
    public void append(String text) {
        buildText+=text;
    }
    public void appendLine(String text) {
        buildText+=text+"\n";
    }
    public void appendLine() {
        appendLine("");
    }
    public void createBuild(String path) {
        createNewFile(path,buildText);
    }
    
    public void clearBuild() {
        buildText="";
    }
    
    
    

}


class File {
    String path;
    String text;
    File(String path, String txt){
        this.path=path;
        text=txt;
    }
}