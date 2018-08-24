/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import service.ProductWebService_Service;

/**
 *
 * @author TungBT
 */
public class sellServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/API_Webser_TungBTD00282/ProductWebService.wsdl")
    private ProductWebService_Service service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (getAllProducts() != null) {
            request.setAttribute("items", getAllProducts());
            request.getRequestDispatcher("sell.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String quantity = request.getParameter("quantity");
        if (sellProduct(id, Integer.parseInt(quantity))) {
            if (getAllProducts() != null) {
                request.setAttribute("items", getAllProducts());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            request.getRequestDispatcher("product.jsp").forward(request, response);
        }
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    private boolean sellProduct(java.lang.String id, int quantity) {
        service.ProductWebService port = service.getProductWebServicePort();
        return port.sellProduct(id, quantity);
    }

    private java.util.List<service.Product> getAllProducts() {
        service.ProductWebService port = service.getProductWebServicePort();
        return port.getAllProducts();
    }

}
