package main.java.it.unisa.ErasmusTracking.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AddReceivingInstituteTest extends Mockito {
    private AddReceivingInstitute servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        System.out.println("print");
        servlet = new AddReceivingInstitute();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }
    @Test
    public void doPost() throws ServletException, IOException {
        request.addParameter("nomeContatto", "fferrucci@unisa.it");
        request.addParameter("sizeEnterprise", "fferrucci@unisa.it");
        request.addParameter("nomeMentore", "password");
        request.addParameter("email", "fferrucci@unisa.it");
        request.addParameter("emailMentore", "ci@unisa.it");
        request.addParameter("website", "ww.www.www");
        request.addParameter("location", "1");
        request.addParameter("learningAgreement", "1");

        servlet.doPost(request, response);
        assertEquals("text/html", response.getContentType());
    }
}