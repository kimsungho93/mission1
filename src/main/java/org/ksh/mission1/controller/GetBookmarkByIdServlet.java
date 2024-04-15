package org.ksh.mission1.controller;

import org.ksh.mission1.dao.BookmarkDAO;
import org.ksh.mission1.dto.BookmarkDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetBookmarkByIdServlet", urlPatterns = {"/GetBookmarkByIdServlet"})
public class GetBookmarkByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // BM_ID 파라미터 받기
        String bmIdStr = request.getParameter("bmId");

        if (bmIdStr == null) {
            return;
        }

        int bmId = Integer.parseInt(bmIdStr);

        BookmarkDAO bmDAO = new BookmarkDAO();
        try {
            BookmarkDTO bookmarkDTO = bmDAO.getBookmarkById(bmId);
            request.setAttribute("bookmarkDTO", bookmarkDTO);
            request.getRequestDispatcher("/bookmark-delete.jsp").forward(request, response);
        } catch (SQLException e) {
            response.getWriter().println("Database error: " + e.getMessage());
        } catch (IOException e) {
            response.getWriter().println("I/O error: " + e.getMessage());
        }
    }
}
