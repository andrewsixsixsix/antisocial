package com.antisomething.antisocial.servlet;

import com.antisomething.antisocial.constant.HttpContentType;
import com.antisomething.antisocial.constant.HttpStatusCode;
import com.antisomething.antisocial.dto.ConstraintViolationDTO;
import com.antisomething.antisocial.dto.request.SignupDTO;
import com.antisomething.antisocial.util.ConstraintValidator;
import com.antisomething.antisocial.util.RequestBodyMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@WebServlet("/signup")
public final class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String html;

        try {
            Path path = Path.of(getServletContext().getResource("/html/signup.html").toURI());
            html = Files.readString(path);
        } catch (URISyntaxException ex) {
            response.setStatus(500);
            return;
        }

        response.setStatus(200);
        response.setContentType(HttpContentType.TEXT_HTML);

        try (PrintWriter writer = response.getWriter()) {
            writer.write(html);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> body = RequestBodyMapper.map(request);
        SignupDTO signup = SignupDTO.from(body);
        List<ConstraintViolationDTO> violations = ConstraintValidator.validate(signup);

        if (!violations.isEmpty()) {
            response.setStatus(HttpStatusCode.UNPROCESSABLE_CONTENT);
            response.setContentType(HttpContentType.APPLICATION_JSON);
            // TODO: return an html or jsp with errors and inputs filled
            return;
        }

        response.setStatus(201);
    }
}
