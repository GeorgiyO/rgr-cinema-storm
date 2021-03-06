package servlets.delete;

import handler.DBHandler;
import storage.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete-second-lvl-comment")
public class DeleteSecondLvlComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        int id = Integer.parseInt(req.getParameter("id"));

        int authorId = DBHandler.getComment2AuthorById(id);

        if (currentUser == null || !currentUser.moderator || authorId != currentUser.id) {
            resp.getWriter().print("no result");
            resp.getWriter().flush();
        } else {
            DBHandler.deleteComment2(id);
        }
    }
}