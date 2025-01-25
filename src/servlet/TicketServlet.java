package servlet;

import dto.TicketDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TicketService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flightId = Long.valueOf(req.getParameter("flightId"));
        var tickets = ticketService.findAllByFlightId(flightId);

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8);

        try (var writer = resp.getWriter()) {
            writer.write("<h1>Купленные билеты:</h1>");
            writer.write("<ul>");
            tickets.forEach(ticketDto ->
                writer.write("""
                        <li>
                            %s
                        </li>
                        """.formatted(ticketDto.seatNo())));
            writer.write("</ul>");
        }
    }
}
