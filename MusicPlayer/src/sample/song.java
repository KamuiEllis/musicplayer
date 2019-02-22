package sample;

public class song {

    String name;
    String path;

    public song(String name, String path) {

        this.name = name;
        this.path = path;

    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
