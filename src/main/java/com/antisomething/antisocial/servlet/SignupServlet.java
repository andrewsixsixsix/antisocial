package com.antisomething.antisocial.servlet;

import com.antisomething.antisocial.util.RequestBodyMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

@WebServlet("/signup")
public final class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> body = RequestBodyMapper.map(request);

        response.setStatus(HttpServletResponse.SC_OK);
        response.reset();
    }
}
