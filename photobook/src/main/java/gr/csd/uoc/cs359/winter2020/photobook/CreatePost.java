/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csd.uoc.cs359.winter2020.photobook;

import gr.csd.uoc.cs359.winter2020.photobook.db.PostDB;
import gr.csd.uoc.cs359.winter2020.photobook.model.OnlineUsers;
import gr.csd.uoc.cs359.winter2020.photobook.model.Post;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

/**
 * Creates a post. The owner of the post is always the logged in user.
 */
@WebServlet(name = "CreatePost", urlPatterns = "/CreatePost")
@MultipartConfig
public class CreatePost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject jsonFinal = new JSONObject();

        /* we need a valid session */
        HttpSession oldSession = request.getSession(false);
        if (oldSession == null || oldSession.getAttribute("username") == null) {
            jsonFinal.put("ERROR", "NO_SESSION");
            out.print(jsonFinal.toJSONString());
            response.setStatus(401);
            return;
        }

        /* check that we have all post fields */
        jsonFinal = checkFields(request);

        /* check that there is a valid post description, latitude, longitude */
        if (jsonFinal.get("description") == null && request.getParameter("description").trim().equals("")) {
            jsonFinal.put("description", "Invalid value");
        }
        if  (jsonFinal.get("latitude") == null && !validLatLon(request.getParameter("latitude"), "latitude")) {
            jsonFinal.put("latitude", "Invalid value");
        }
        if  (jsonFinal.get("longitude") == null && !validLatLon(request.getParameter("longitude"), "longitude")) {
            jsonFinal.put("longitude", "Invalid value");
        }

        if (!jsonFinal.isEmpty()) {
            jsonFinal.put("ERROR", "INVALID_PARAMETERS");
            out.print(jsonFinal.toJSONString());
            response.setStatus(400);
            return;
        }

        /* create a Post object using the request parameters */
        Post post = new Post();
        String username = (String) oldSession.getAttribute("username");
        OnlineUsers.addUser(username);
        String r_description = request.getParameter("description");
        String r_resourceURL = request.getParameter("resourceURL");
        String r_imageURL = request.getParameter("imageURL");
        String r_imageBase64 = request.getParameter("imageBase64");
        String r_latitude = request.getParameter("latitude");
        String r_longitude = request.getParameter("longitude");
        post.setUserName(username);
        post.setDescription(r_description.replace("'", "''"));
        post.setResourceURL(r_resourceURL);
        post.setImageURL(r_imageURL);
        post.setImageBase64(r_imageBase64);
        post.setLatitude(r_latitude);
        post.setLongitude(r_longitude);

        /* add the post to the DB and get it's ID */
        int id = PostDB.addPost(post);
        if (id == -1) {
            JSONObject json = new JSONObject();
            json.put("ERROR", "SERVER_ERROR");
            out.print(json.toJSONString());
            response.setStatus(500);
            return;
        }

        /* get the Post that has the ID that we have */
        post = PostDB.getPost(id);
        if (post == null) {
            JSONObject json = new JSONObject();
            json.put("ERROR", "SERVER_ERROR");
            out.print(json.toJSONString());
            response.setStatus(500);
            return;
        }

        /* verify that the post with the ID that we have matches the request parameters */
        String d_description = post.getDescription();
        String d_resourceURL = post.getResourceURL();
        String d_imageURL = post.getImageURL();
        String d_imageBase64 = post.getImageBase64();
        String d_latitude = post.getLatitude();
        String d_longitude = post.getLongitude();
        if (username.equals(post.getUserName()) &&
            d_description.equals(r_description) &&
            d_resourceURL.equals(r_resourceURL) &&
            d_imageURL.equals(r_imageURL) &&
            d_imageBase64.equals(r_imageBase64) &&
            d_latitude.equals(r_latitude) &&
            d_longitude.equals(r_longitude)) {
                out.print(jsonFinal.toJSONString());
        }
        else {
            JSONObject json = new JSONObject();
            json.put("ERROR", "SERVER_ERROR");
            out.print(json.toJSONString());
            response.setStatus(500);
        }
    }

    /**
     * Checks that the request has all the required parameters.
     * @param request
     * @return
     */
    protected JSONObject checkFields(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String[] fields = new String[] {
                "description", "resourceURL", "imageURL", "imageBase64",
                "latitude", "longitude"};

        for (int i = 0; i < fields.length; i++) {
            if (request.getParameter(fields[i]) == null) {
                json.put(fields[i], "Missing parameter");
            }
        }

        return json;
    }

    /**
     * Checks whether the specified latlon string consists of a valid value. If param = 'latitude'
     * it is assumed that latlon is a latitude value, else it is assumed that latlon is a longitude value.
     * @param latlon
     * @param param
     * @return
     */
    protected boolean validLatLon(String latlon, String param) {
        if (latlon == null) {
            return false;
        }
        float f_latlon;
        try {
            f_latlon = Float.parseFloat(latlon);
        }
        catch (NumberFormatException e) {
            return false;
        }
        if (param.equals("latitude")) {
            return (f_latlon < 90 && f_latlon > -90);
        }
        else {
            return (f_latlon < 180 && f_latlon > -180);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}