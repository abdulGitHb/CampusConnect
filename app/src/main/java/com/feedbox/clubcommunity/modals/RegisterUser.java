package com.feedbox.clubcommunity.modals;

public class RegisterUser {

    String name, email, password, college, branch,year, phone;
//    String[] Skills;
//    name, email, password, college, branch, year, phone, skills

    public RegisterUser(String name, String email, String password, String college, String branch, String year, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.college = college;
        this.branch = branch;
        this.year = year;
        this.phone = phone;
//        Skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public String[] getSkills() {
//        return Skills;
//    }
//
//    public void setSkills(String[] skills) {
//        Skills = skills;
//    }

}
