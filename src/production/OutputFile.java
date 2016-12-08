// Kane Templeton
// OutputFile.java

package production;


public class OutputFile {
    
    private final String filePath;
    private String buildText;
    
    public OutputFile(String path) {
        filePath=path;
        buildText="";
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

    public void clearBuild() {
        buildText="";
    }
    public String filePath() {
        return filePath;
    }
    public String text() {
        return buildText;
    }
    public boolean equals(OutputFile O) {
        return buildText.equals(O.buildText);
    }

}
