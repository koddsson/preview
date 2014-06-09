package poi;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.ObjectRepresentation;
import org.restlet.data.MediaType;

import org.restlet.ext.fileupload.RestletFileUpload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

public class HelloWorldResource extends ServerResource {

    @Get
    public Representation handleDownload() {
        String path = getRequest().getResourceRef().getPath();
        String filename = path.substring(1, path.length());
        Path p = Paths.get(filename);
        byte[] data = null;
        try {
            data = Files.readAllBytes(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectRepresentation<byte[]> or=new ObjectRepresentation<byte[]>(data, MediaType.APPLICATION_PDF) {
            @Override
            public void write(OutputStream os) throws IOException {
                super.write(os);
                os.write(this.getObject());
            }
        };
    
        return or; 
    }
    /*
    public byte[] handleDownload(Representation entity) {
        String path = getRequest().getResourceRef().getPath();
        String filename = path.substring(1, path.length());

        if (filename.equals("favicon.ico") {
            return new byte[1];
        }
        
        Path p = Paths.get(filename);
        try {
            return Files.readAllBytes(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[1];
    }*/

    @Put
    public String handleUpload(Representation entity) {
        Utils utils = new Utils();
        List<FileItem> items = null;
        try {
            items = new RestletFileUpload(new DiskFileItemFactory())
                        .parseRepresentation(entity);
        } catch (FileUploadException e) {
            System.out.println("File upload failed!");
        }
        for (FileItem item : items) {
            if (!item.isFormField()) {
                MediaType type = MediaType.valueOf(item.getContentType());
                try {
                    InputStream inputStream = item.getInputStream();
                    String filename = utils.ConvertDocxToPDF(inputStream);
                    System.out.println("HEY");
                    return "http://localhost:8182/" + filename;
                } catch (IOException e) {
                    System.out.println("I dunno");
                }
            }
        }
        return "ERROR OCCURED";
    }
}
