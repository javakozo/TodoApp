<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<html>
<head>
    <title>ToDo List</title>
</head>
<body>
    <h1>ToDo List</h1>
    <form action="tasks" method="post">
        <input type="text" name="task" />
        <input type="hidden" name="action" value="add" />
        <button type="submit">Add Task</button>
    </form>
    <ul>
        <%
            List<Task> taskList = (List<Task>) request.getAttribute("taskList");
            if (taskList != null && !taskList.isEmpty()) {
                for (Task task : taskList) {
        %>
            <li>
                <form action="tasks" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= task.getId() %>" />
                    <input type="hidden" name="action" value="update" />
                    <input type="hidden" name="completed" value="<%= !task.isCompleted() %>" />
                    <button type="submit"><%= task.isCompleted() ? "未完了" : "完了" %></button>
                </form>
                <%= task.getTask() %>
                <form action="tasks" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= task.getId() %>" />
                    <input type="hidden" name="action" value="delete" />
                    <button type="submit">削除</button>
                </form>
            </li>
        <%
                }
            } else {
        %>
            <li>No tasks available.</li>
        <%
            }
        %>
    </ul>
</body>
</html>
