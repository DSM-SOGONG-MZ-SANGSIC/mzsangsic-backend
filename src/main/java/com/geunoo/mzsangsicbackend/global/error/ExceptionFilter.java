package com.geunoo.mzsangsicbackend.global.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geunoo.mzsangsicbackend.global.error.exceptions.InternalServerErrorException;
import com.geunoo.mzsangsicbackend.global.error.exceptions.UnauthorizedException;
import com.gil.easyjwt.exception.EasyJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            if (e.getCause() instanceof CustomException exception) {
                writeErrorResponse(response, exception);
            } else if (e instanceof EasyJwtException exception) {
                writeErrorResponse(response, new UnauthorizedException(exception.getMessage()));
            } else {
                e.printStackTrace();
                writeErrorResponse(response, new InternalServerErrorException());
            }
        }

    }

    private void writeErrorResponse(HttpServletResponse response, CustomException exception) throws IOException {
        response.setStatus(exception.getStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), exception.getStatus());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
