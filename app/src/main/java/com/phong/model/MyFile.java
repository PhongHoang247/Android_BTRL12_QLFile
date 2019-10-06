package com.phong.model;

public class MyFile {
    private String disPlay;
    private String path;//path chứa display
    private String descripTion;
    private FileSupport fileType;
    private boolean hasChild;

    public MyFile() {
    }

    public MyFile(String disPlay, String path, String descripTion, FileSupport fileType, boolean hasChild) {
        this.disPlay = disPlay;
        this.path = path;
        this.descripTion = descripTion;
        this.fileType = fileType;
        this.hasChild = hasChild;
    }

    public String getDisPlay() {
        return disPlay;
    }

    public void setDisPlay(String disPlay) {
        this.disPlay = disPlay;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescripTion() {
        return descripTion;
    }

    public void setDescripTion(String descripTion) {
        this.descripTion = descripTion;
    }

    public FileSupport getFileType() {
        return fileType;
    }

    public void setFileType(FileSupport fileType) {
        this.fileType = fileType;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }
}
