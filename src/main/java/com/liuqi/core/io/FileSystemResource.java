package com.liuqi.core.io;

import com.liuqi.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource {

    private final String path;

    private final File file;

    public FileSystemResource(String path) {
        Assert.notNull(path,"path must not be null");
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return "file ["+this.file.getAbsolutePath()+"]";
    }
}
