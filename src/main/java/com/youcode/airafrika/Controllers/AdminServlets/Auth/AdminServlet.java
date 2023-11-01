package com.youcode.airafrika.Controllers.AdminServlets.Auth;

import com.youcode.airafrika.Entities.Admin;
import com.youcode.airafrika.Services.AdminService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminServlet extends HttpServlet {

    private void adminLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();

            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(password);

            Admin admin1 = AdminService.login(admin);
            if (admin1 != null){
                session.setAttribute("admin_email", admin1.getEmail());
                session.setAttribute("isAdminLogged", true);
                response.sendRedirect("/admin/flights?action=all");
            }else
                response.sendRedirect("index.jsp");
        }catch (Exception exception) {
            Logger.getLogger(getServletName()).log(Level.SEVERE, "an Error Occurred in Admin Servlet.", exception);
        }
    }

    private void adminLogout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                session.invalidate();
                response.sendRedirect("index.jsp");
            }
        }catch (Exception exception) {
            Logger.getLogger(getServletName()).log(Level.SEVERE, "An Error Occurred in Admin Servlet.", exception);
        }
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("admin/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "login":
                adminLogin(req, resp);
                break;
            case "logout":
                adminLogout(req, resp);
                break;
        }
    }
}
