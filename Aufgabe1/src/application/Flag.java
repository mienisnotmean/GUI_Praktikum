package application;

public class Flag {

    private boolean modified;
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public boolean isModified() {
        return modified;
    }

    public Flag() {
        this.modified = false;
        this.file = null;
    }

}
