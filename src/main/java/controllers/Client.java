package controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

<<<<<<< HEAD
@Path("/")
public class Client {

    @GET
    @Path("client/img/{path}")
=======
/* ------------------------------------------------------------------------------
 This class serves up the static HTML, CSS, JavaScript and images to the client.
 You shouldn't need to change anything unless you are adding other file types.
 ------------------------------------------------------------------------------ */
@Path("client/")
public class Client {

    @GET
    @Path("img/{path}")
>>>>>>> b8d4ca86d4e89f105c947ddc4aaa287e55762f02
    @Produces({"image/jpeg,image/png"})
    public byte[] getImageFile(@PathParam("path") String path) {
        return getFile("client/img/" + path);
    }

    @GET
<<<<<<< HEAD
    @Path("client/js/{path}")
=======
    @Path("js/{path}")
>>>>>>> b8d4ca86d4e89f105c947ddc4aaa287e55762f02
    @Produces({"text/javascript"})
    public byte[] getJavaScriptFile(@PathParam("path") String path) {
        return getFile("client/js/" + path);
    }

    @GET
<<<<<<< HEAD
    @Path("client/css/{path}")
=======
    @Path("lib/{path}")
    @Produces({"text/javascript"})
    public byte[] getJavaScriptLibraryFile(@PathParam("path") String path) {
        return getFile("client/lib/" + path);
    }

    @GET
    @Path("css/{path}")
>>>>>>> b8d4ca86d4e89f105c947ddc4aaa287e55762f02
    @Produces({"text/css"})
    public byte[] getCSSFile(@PathParam("path") String path) {
        return getFile("client/css/" + path);
    }

    @GET
    @Path("{path}")
    @Produces({"text/html"})
    public byte[] getIHTMLFile(@PathParam("path") String path) {
<<<<<<< HEAD
        return getFile(path);
    }

    private byte[] getFile(String filename) {
        try {
=======
        return getFile("client/" + path);
    }

    @GET
    @Path("favicon.ico")
    @Produces({"image/x-icon"})
    public byte[] getFavicon() {
        return getFile("client/favicon.ico");
    }

    private static byte[] getFile(String filename) {
        try {

>>>>>>> b8d4ca86d4e89f105c947ddc4aaa287e55762f02
            File file = new File("resources/" + filename);
            byte[] fileData = new byte[(int) file.length()];
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dis.readFully(fileData);
            dis.close();
            System.out.println("Sending: " + filename);
            return fileData;
        } catch (IOException ioe) {
            System.out.println("File IO error: " + ioe.getMessage());
        }
        return null;
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> b8d4ca86d4e89f105c947ddc4aaa287e55762f02
