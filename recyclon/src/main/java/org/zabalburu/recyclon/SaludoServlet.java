package org.zabalburu.recyclon;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hola")
public class SaludoServlet extends HttpServlet {
   
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (var out = response.getWriter()) {
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Hola</title>");
      out.println("<style>");
      out.println("body { font-family: Arial, sans-serif; background-color: #f0f0f0; text-align: center; padding: 50px; }");
      out.println("h1 { color: #333; }");
      out.println(".container { background-color: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); display: inline-block; }");
      out.println("</style>");
      out.println("</head>");
      out.println("<body>");
      out.println("<div class='container'>");
      out.println("<h1>Saludos desde Jakarta EE 10!</h1>");
      out.println("</div>");
      out.println("</body>");
      out.println("</html>");
    }
  }
}
