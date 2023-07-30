package module8;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss z";
    public static final String TIMEZONE = "timezone";

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        if (httpServletRequest.getParameter(TIMEZONE) == null ||
                httpServletRequest.getParameter(TIMEZONE).isEmpty()) {
            httpServletResponse.getWriter().write(parseDateToFormat("UTC"));
        } else {
            String timezone = httpServletRequest.getParameter(TIMEZONE);
            String dateToFormat = parseDateToFormat(timezone);
            httpServletResponse.getWriter().write(dateToFormat);
        }
    }

    public String parseDateToFormat(String zoneId) {
        Instant date = new Date().toInstant();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN)
                .withZone(ZoneId.of(zoneId));
        return dateFormat.format(date);
    }

}