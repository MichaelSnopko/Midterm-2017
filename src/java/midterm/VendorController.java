package midterm;

import java.sql.Connection;
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
 * The Vendor Controller Class
 *
 * @author <Michael Snopko>
 */
@Named
@ApplicationScoped
public class VendorController {

    private List<Vendor> vendors = new ArrayList<>();
    private Vendor thisVendor = new Vendor();

    /**
     *No-arg Constructor -- sets up list from DB
     */
    public VendorController() {
        thisVendor = new Vendor(0, "", "", "");
        getDBUtils();
    }

    /**
     *Retrieve the List of Vendor objects
     * @return the List of Vendor objects
     */
    public List<Vendor> getVendors() {
        return vendors;
    }

    /**
     *
     * @param id the id to search for 
     * @return id --null if not found
     */
    public Vendor getById(int id) {
        for (Vendor v : vendors) {
            if (v.getVendorId() == id) {
                return v;
            }
        }
        return null;
    }

    /**
     * set current vendor from Lists of Vendors
     * @param vendors current vendors
     */
    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    /**
     * get current vendor
     * @return thisVendor 
     */
    public Vendor getThisVendor() {
        return thisVendor;
    }

    /**
     * Sets current Vendor
     * @param thisVendor
     */
    public void setThisVendor(Vendor thisVendor) {
        this.thisVendor = thisVendor;
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
            stmt.executeUpdate("INSERT INTO Vendors VALUES (" + thisVendor.getVendorId() + ",'" + thisVendor.getName() + "','" + thisVendor.getContactName() + "','" + thisVendor.getPhoneNumber() + "')");
            getDBUtils();
            return "index";
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }

    /**
     *  
     *  Retrieves Vendors from DB
     */
    private void getDBUtils() {
        try {
            vendors.clear();
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Vendors");
            while (rs.next()) {
                Vendor v = new Vendor();
                v.setVendorId(rs.getInt("VendorId"));
                v.setName(rs.getString("Name"));
                v.setContactName(rs.getString("ContactName"));
                v.setPhoneNumber(rs.getString("PhoneNumber"));
                vendors.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
