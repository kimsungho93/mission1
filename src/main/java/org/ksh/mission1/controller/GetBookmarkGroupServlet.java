package org.ksh.mission1.controller;

import com.google.gson.Gson;
import org.ksh.mission1.dao.BookmarkGroupDAO;
import org.ksh.mission1.dto.BookmarkGroupDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetBookmarkGroupServlet", urlPatterns = {"/GetBookmarkGroupServlet"})
public class GetBookmarkGroupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookmarkGroupDAO bmgDAO = new BookmarkGroupDAO();

        try {
            List<BookmarkGroupDTO> bookmarkGroups = bmgDAO.getBookmarkGroup();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new Gson().toJson(bookmarkGroups));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
