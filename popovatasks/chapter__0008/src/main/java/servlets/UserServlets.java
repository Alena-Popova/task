package servlets;

import logic.User;
import persistent.ConnectDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserServlets  extends HttpServlet {

    ConnectDB connectDB = new ConnectDB();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        try {
            this.connectDB.connect();
            try {
                List<User> users = this.connectDB.getAllFirstN(5);
                writer.append("Users : ");
                if (users.size() != 0) {
                    writer.append(users.toString());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");

        if (id != null
                && name != null
                && login != null
                && email != null) {
            try {
                this.connectDB.connect();
                this.connectDB.insert(Collections.singletonList(new User(Integer.parseInt(id),
                        name,
                        login,
                        email,
                        new Date() )));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else  {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("Uncorect Parameter");
        }
        doGet(req, resp);
    }
}
