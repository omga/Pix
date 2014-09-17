package com.pix.model;

/**
 * Created by Andrew on 21.04.14.
 */
public class Affiliate extends PixUser {
    private String companyName;
    private String phoneNumber;
    private String websiteURL;
    public Affiliate(){

    }

    public Affiliate(String userName, String firstName, String lastName, String email, String password, String companyName, String phoneNumber, String websiteURL) {
        super(userName, firstName, lastName, email, password);
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.websiteURL = websiteURL;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }
}
