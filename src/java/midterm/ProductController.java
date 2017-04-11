package midterm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * The Product Controller Class
 *
 * @author <Michael>
 */
@Named
@ApplicationScoped
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private Product thisProduct = new Product();

    /**
     *No-arg Constructor -- sets up list from DB
     */
    public ProductController() {
        getDBUtils();
        thisProduct = new Product(0, "", 0);
    }

    /**
     *Retrieve the List of Product objects
     * 
     * @return the List of Product objects
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * get current product
     * @return thisProduct
     */
    public Product getThisProduct() {
        return thisProduct;
    }

    /**
     * Sets current Product
     * @param thisProduct
     */
    public void setThisProduct(Product thisProduct) {
        this.thisProduct = thisProduct;
    }

    /**
     *
     * @param id the id to search for 
     * @return productId --null if not found
     */
    public Product getByProductId(int id) {
        for (Product i : products) {
            if (i.getProductId() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     *  
     *  Retrieves Products from DB
     */
    private void getDBUtils() {

        try {
            products.clear();
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                Product p = new Product();
                p.setName(rs.getString("Name"));
                p.setProductId(rs.getInt("ProductId"));
                p.setVendorId(rs.getInt("VendorId"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Add a new Product to the Database 
     *
     * @return to index path
     */
    public String add() {
        try {
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO products VALUES (" + thisProduct.getProductId() + ",'" + thisProduct.getName() + "','" + thisProduct.getVendorId() + "')");
            getDBUtils();
            return "index";
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }

    /**
     * This delete Product
     *
     * @param productId Form products where productId equals current productId
     * @return to index after deleted Product
     */
    public String delete(int productId) {
        try {
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM products WHERE ProductId = " + productId);
            getDBUtils();
            return "index";
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }

    /**
     * Navigate to edit the current product
     * @param productId
     * @return to editProduct after this ProductId is selected
     */
    public String edit(int productId) {
        thisProduct = getByProductId(productId);
        return "editProduct";
    }
}
