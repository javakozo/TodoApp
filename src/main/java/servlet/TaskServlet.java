package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import data.DataBaseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Task;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Task> taskList = DataBaseUtil.listTasks();
            request.setAttribute("taskList", taskList);
            request.getRequestDispatcher("/tasks.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                String task = request.getParameter("task");
                DataBaseUtil.insertTask(task);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                DataBaseUtil.deleteTask(id);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean completed = Boolean.parseBoolean(request.getParameter("completed"));
                DataBaseUtil.updateTask(id, completed);
            }
            response.sendRedirect("tasks");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}