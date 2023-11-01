package com.youcode.airafrika.Controllers.AdminServlets.FlightServlets;
import com.youcode.airafrika.Entities.*;
import com.youcode.airafrika.Enums.Availability;
import com.youcode.airafrika.Enums.ClassType;
import com.youcode.airafrika.Services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
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
            request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
        }catch (Exception exception) {
            Logger.getLogger(getServletName()).log(Level.SEVERE, "An Error Occurred in FlightServlet - allFlights Method", exception);
        }
    }

    private void addFlightView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Airport> airports = AirportService.getAllAirports();
        List<Plane> planes = PlaneService.getAllPlanes();
        List<Company> companies = CompanyService.getAllCompanies();
        request.setAttribute("airports", airports);
        request.setAttribute("planes", planes);
        request.setAttribute("airlines", companies);
        request.setAttribute("availabilities", Availability.values());
        request.setAttribute("classTypes", ClassType.values());
        request.getRequestDispatcher("/admin/addFlight.jsp").forward(request, response);
    }

    private void addNewFlight(HttpServletRequest request, HttpServletResponse response) {
        try {
            // flightPaths
            String departureDate = request.getParameter("departure_date");
            String arrivalDate = request.getParameter("arrival_date");
            String departureAirport = request.getParameter("departure_airport");
            String arrivalAirport = request.getParameter("arrive_airport");
//            String flight; // this current flight id
            String classType = request.getParameter("class_type");
            String price = request.getParameter("price");

            // flight
            Availability availability = Availability.AVAILABLE;

            String plane = request.getParameter("plane");
            // set plane
            Plane plane1 = PlaneService.findOne(Long.parseLong(plane));

            // set flight
            Flight flight = new Flight();
            flight.setAvailability(availability);
            flight.setPrice(Long.parseLong(price));
            flight.setPlane(plane1);
            Flight flight1 = FlightService.createFlight(flight);

            // flightpath
            FlightPath flightPath = new FlightPath();
            flightPath.setFlight(flight1);
            flightPath.setDepartureDate(LocalDate.parse(departureDate));
            flightPath.setArrivalDate(LocalDate.parse(arrivalDate));
            flightPath.setDepartureAirport(AirportService.findOne(Long.parseLong(departureAirport)));
            flightPath.setArriveAirport(AirportService.findOne(Long.parseLong(arrivalAirport)));
            flightPath.setClassType(ClassType.valueOf(classType));
            FlightPathService.create(flightPath);

            response.sendRedirect("/admin/flights?action=all");
        }catch(Exception exception) {
            Logger.getLogger(getServletName()).log(Level.SEVERE, "An Error Occurred in Flight Servlet - AddNewFlight Method", exception);
        }
    }

    private void updateFlight(HttpServletRequest request, HttpServletResponse response) {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch(action) {
            case "all":
                allFlights(req, resp);
                break;
            case "add":
                addFlightView(req, resp);
                break;
            case "update":
                updateFlight(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch(action) {
            case "add":
                addNewFlight(req, resp);
                break;
            case "update":
                updateFlight(req, resp);
                break;
        }
    }
}
