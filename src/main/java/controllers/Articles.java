package controllers;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("articles/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public class Articles{
    @GET
    @Path("list")
    public String ArticlesList() {
        System.out.println("Invoked Articles.ArticlesList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT ArticleID, ArticleName FROM Articles");
            ResultSet results = ps.executeQuery();
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("ArticleID", results.getInt(1));
                row.put("ArticleName", results.getString(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }
    @GET
    @Path("get/{ArticleID}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String GetArticle(@PathParam("ArticleID") Integer ArticleID) {
        System.out.println("Invoked Articles.GetArticle() with ArticleID " + ArticleID);
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT ArticleName, DateAdded FROM Articles WHERE ArticleID = ?");
            ps.setInt(1, ArticleID);
            ResultSet results = ps.executeQuery();
            JSONObject response = new JSONObject();
            if (results.next()== true) {
                response.put("ArticleID", ArticleID);
                response.put("ArticleName", results.getString(1));
                response.put("DateAdded", results.getString(2));
            } else{
                System.out.println("Cannot find that ArticleID");
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }
    @POST
    @Path("delete/{ArticleID}")
    public String DeleteArticle(@PathParam("ArticleID") Integer ArticleID) {
        System.out.println("Invoked Articles.DeleteArticle() with ArticleID " + ArticleID);
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Articles WHERE ArticleID = ?" );
            ps.setInt(1, ArticleID);
            return "{\"OK\": \"Article Deleted.\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }
}
