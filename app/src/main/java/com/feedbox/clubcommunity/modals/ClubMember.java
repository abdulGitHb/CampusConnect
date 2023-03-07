package com.feedbox.clubcommunity.modals;

public class ClubMember {
    String name, contact, gmail;
    Boolean isAdmin, isSuperAdmin, isLead;

    public ClubMember(String name, String contact, String gmail, Boolean isAdmin, Boolean isSuperAdmin, Boolean isLead) {
        this.name = name;
        this.contact = contact;
        this.gmail = gmail;
        this.isAdmin = isAdmin;
        this.isSuperAdmin = isSuperAdmin;
        this.isLead = isLead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public Boolean getLead() {
        return isLead;
    }

    public void setLead(Boolean lead) {
        isLead = lead;
    }
}
