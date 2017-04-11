package midterm;

import java.util.ArrayList;
import java.util.List;

/**
 * The Vendor Model Class
 *
 * @author <Michael Snopko>
 */
public class Vendor {

    private int vendorId;
    private String name;
    private String contactName;
    private String phoneNumber;

//    private List<VendorController> vendor = new ArrayList<>();
//    private VendorController currentvendor = new VendorController();
    
    public Vendor() {
    }

    /**
     *
     * @param vendorId
     * @param name
     * @param contactName
     * @param phoneNumber
     */
    public Vendor(int vendorId, String name, String contactName, String phoneNumber) {
        this.vendorId = vendorId;
        this.name = name;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }
/**
 * Retrieve the vendorId
 * @return the vendorId
 */
    public int getVendorId() {
        return vendorId;
    }
/**
 * sets vendorId
 * @param vendorId 
 */
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
/**
 * gets name of vendor
 * @return name of vendor
 */
    public String getName() {
        return name;
    }
/**
 * set name of vendor
 * @param name of vendor
 */
    public void setName(String name) {
        this.name = name;
    }
/**
 * get contact name of vendor
 * @return contact name of vendor
 */
    public String getContactName() {
        return contactName;
    }
/**
 * sets contact name of vendor
 * @param contactName of vendor
 */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
/**
 * get phone number of vendor
 * @return phone number of vendor
 */
    public String getPhoneNumber() {
        return phoneNumber;
    }
/**
 * set phone number of vendor
 * @param phoneNumber of vendor
 */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
}
