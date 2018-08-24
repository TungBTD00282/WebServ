package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import service.Product;
import service.ProductWebService_Service;

/**
 *
 * @author TungBT
 */
public class ProductServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/API_Webser_TungBTD00282/ProductWebService.wsdl")
    private ProductWebService_Service service;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");

        Product p = new Product();
        p.setId(id);
        p.setName(name);
        p.setPrice(Float.parseFloat(price));
        p.setQuantity(Integer.parseInt(quantity));

        if (addProduct(p)) {
            if (getAllProducts() != null) {
                request.setAttribute("items", getAllProducts());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            request.getRequestDispatcher("product.jsp").forward(request, response);
        }
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    private boolean addProduct(service.Product p) {
        service.ProductWebService port = service.getProductWebServicePort();
        return port.addProduct(p);
    }

    private java.util.List<service.Product> getAllProducts() {
        service.ProductWebService port = service.getProductWebServicePort();
        return port.getAllProducts();
    }

}
