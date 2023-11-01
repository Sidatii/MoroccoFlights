package com.youcode.airafrika.Controllers.ClientServlets.FlightServlet;

import com.youcode.airafrika.Entities.Flight;
import com.youcode.airafrika.Services.FlightService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightServlet extends HttpServlet {

    private void allFlights(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Flight> flights = FlightService.getAllFlights();
            for (Flight flight: flights
            ) {
                System.out.println(flight.getFlightPath());
            }
            request.setAttribute("flights", flights);
            request.getRequestDispatcher("client/flights.jsp").forward(request, response);
        }catch (Exception exception) {
            Logger.getLogger(getServletName()).log(Level.SEVERE, "An Error Occurred in FlightServlet - allFlights Method", exception);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        allFlights(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
