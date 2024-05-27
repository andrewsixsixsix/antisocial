package com.antisomething.antisocial.servlet;

import com.antisomething.antisocial.constant.HttpContentType;
import com.antisomething.antisocial.constant.HttpStatusCode;
import com.antisomething.antisocial.dto.request.SignupDTO;
import com.antisomething.antisocial.dto.ConstraintViolationDTO;
import com.antisomething.antisocial.util.ConstraintValidator;
import com.antisomething.antisocial.util.RequestBodyMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/signup")
public final class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> body = RequestBodyMapper.map(request);
        SignupDTO signup = SignupDTO.from(body);
        List<ConstraintViolationDTO> violations = ConstraintValidator.validate(signup);

        if (!violations.isEmpty()) {
            response.setStatus(HttpStatusCode.UNPROCESSABLE_CONTENT);
            response.setContentType(HttpContentType.APPLICATION_JSON);
            // TODO: send JSON
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
