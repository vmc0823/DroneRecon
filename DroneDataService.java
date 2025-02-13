package com.dronerecon.ws;

        import javax.servlet.*;
        import javax.servlet.http.*;
        import java.io.*;
        import java.net.URL;
        import java.util.*;
        import java.security.SecureRandom;

/**
 *
 * @author Valentina Waltman
 */
public class DroneDataService extends HttpServlet{


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        // ##############################
        // 1. Get params passed in.

        // Get the following parameters from the request object and put them into strings:
        // area_id
        // tilex
        // tiley
        // totalcols
        // totalrows
        //r
        //g
        // ##############################
        String area_id = request.getParameter("area_id");
        String tilexStr = request.getParameter("tilex");
        String tileyStr = request.getParameter("tiley");
        String totalcolsStr = request.getParameter("totalcols");
        String totalrowsStr = request.getParameter("totalrows");
        //get r and g from request
        String r = request.getParameter("r");
        String g = request.getParameter("g");

        if (area_id == null || tilexStr == null || tileyStr == null || totalcolsStr == null || totalrowsStr == null) {
            out.println("{\"error\":\"Missing parameters\"}");
            return;
        }

        int x, y, cols, rows;
        try {
            x = Integer.parseInt(tilexStr);
            y = Integer.parseInt(tileyStr);
            cols = Integer.parseInt(totalcolsStr);
            rows = Integer.parseInt(totalrowsStr);
        } catch (NumberFormatException e) {
            out.println("{\"error\":\"Invalid parameter format\"}");
            return;
        }

        //call cloud and pass data to be stored in Database (like the weather app lab)
        try {

            // Call PortalDBService.
            URL url = new URL("http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id=" + area_id + "&tilex=" + tilexStr + "&tiley=" + tileyStr + "&r=" + r + "&g=" + g );
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // ##############################
        // 2. Default value of beginning direction.

        // Set a string called sDirection to "right".
        String sDirection = "right";
        // ##############################


        // ##############################
        // 3. Calculate next drone move.

        // Determine next tile to move to.
        // Base this on current x and y.
        // Change sDirection if necessary.
        // Drone must serpentine from top left of grid back and forth down.
        // If rows are done, change sDirection to "stop".
        // ##############################
        // If tile y is even
        if (y % 2 == 0) {
            // Check if end of row
            if (x == cols - 1) {
                y++; // Move to next row
                sDirection = "left";
            } else {
                x++; // Move to the right
                sDirection = "right";
            }
        } else { // If tile y is odd
            // Check if far left end of row
            if (x == 0) {
                y++; // Move to next row
                sDirection = "right";
            } else {
                x--; // Move to the left
                sDirection = "left";
            }
        }


        // ##############################

        //if tile y is even (hint: use modulus which is %) {
            //check if end of row
            //if x is equal to total cols - 1{
                //increase current y by 1
                //set sDirection to "left"
            //}
            //else{
                //increase current x by 1
                //set sDirection to "right"
            //}

        //}
        //} else {
            //check if far left and of row.
            //if x is equal to 0{
                //increase current by 1
                //set sDirection to "right"
            //}
            //else{
                //Decrease current x by 1
                //set sDirection to "left"
            //}
        //}

        //check if should "stop"
        //if tile y equals total rows{
            //set sDirection to "stop"
        //}

        // Check if should "stop"
        if (y == rows) {
            sDirection = "stop";
        }

        // 4. Format & Return JSON string to caller.

        /* Return via out.println() a JSON string like this:
        {"area_id":"[area id from above]", "nextTileX":"[next tile x]", "nextTileY":"[next tile y]", "direction":"[direction string from above]"}
        */
        // ##############################
        //out.println("{\"area_id\":\"" + area_id + "\", \"nextTileX\":\"" + x + "\", \"nextTileY\":\"" + y + "\", \"direction\":\"" + sDirection + "\"}");
        String sReturnJson = "{\"area_id\":\"" + area_id + "\", \"nextTileX\":\"" + x + "\", \"nextTileY\":\"" + y + "\", \"direction\":\"" + sDirection + "\"}";
        out.println(sReturnJson);
    }
}

