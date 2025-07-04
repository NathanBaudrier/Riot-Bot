package fr.nathanpasdutout.utils;

import javax.swing.ImageIcon;
import java.io.InputStream;
import java.net.URL;

public class MyImage {

    private final String path;

    public MyImage(String path) {
        this.path = path;
    }

    public InputStream getImageAsInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(this.path);
    }

    public URL getImageAsURL() {
        return this.getClass().getClassLoader().getResource(this.path);
    }

    public ImageIcon getImageAsIcon() {
        return new ImageIcon(this.getImageAsURL());
    }
}
