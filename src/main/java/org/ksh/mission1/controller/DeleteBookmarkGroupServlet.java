package org.ksh.mission1.controller;

import org.ksh.mission1.dao.BookmarkGroupDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteBookmarkGroupServlet", urlPatterns = {"/DeleteBookmarkGroupServlet"})
public class DeleteBookmarkGroupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bmgIdStr = request.getParameter("bmgId");

        if (bmgIdStr == null) {
            return;
        }

        int bmgId = Integer.parseInt(bmgIdStr);

        BookmarkGroupDAO bmgDAO = new BookmarkGroupDAO();
        try {
            bmgDAO.deleteBookmarkGroup(bmgId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
