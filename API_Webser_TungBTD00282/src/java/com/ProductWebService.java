package com;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author TungBT
 */
@WebService(serviceName = "ProductWebService")
public class ProductWebService {
    private ProductDao p1;

    @WebMethod(operationName = "getAllProducts")
    public List<Product> getAllProducts() {
        p1 = new ProductDao();
        List<Product> rs = p1.findAll()
        return rs;
    }

    @WebMethod(operationName = "addProduct")
    public boolean addProduct(@WebParam(name = "p") Product p) {
        p1 = new ProductDao();
        boolean rs = p1.insert(p);
        return rs;
    }

    @WebMethod(operationName = "sellProduct")
    public boolean sellProduct(@WebParam(name = "id") String id, @WebParam(name = "quantity") int quantity) {
        p1 = new ProductDao();
        p1.update(id, quantity)
        return true;
    }
}
