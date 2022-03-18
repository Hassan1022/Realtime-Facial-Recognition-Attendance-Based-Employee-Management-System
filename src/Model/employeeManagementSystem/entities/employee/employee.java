package Model.employeeManagementSystem.entities.employee;

import java.io.File;
import java.io.FileInputStream;
import  java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class employee {
    private static int employeeCount=1;
    // the first var is used as a counter for the employee id's  which is used
    // as the automatic id generator for employees
    private int employeeId,deptId,age,salary, employeeType;
    private String firstName,middleName, lastName,email,sex,phone, address, designation, nationalId;
    private String relativeName,relation, relativePhone, password;
    private employeeImage employeePhoto;
    private Date birthDate;

    public employee(int employeeId, int deptId, int age, int salary,
                    String firstName, String middleName, String lastName, String email, String sex,
                    String phone, String address, String designation, String nationalId,
                    int employeeType, String relativeName, String relation, String relativePhone,
                    employeeImage employeePhoto, Date birthDate, String password) {
        this.employeeId=employeeId;
        this.deptId = deptId;
        this.age = age;
        this.salary = salary;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.designation = designation;
        this.nationalId = nationalId;
        this.employeeType = employeeType;
        this.relativeName = relativeName;
        this.relation = relation;
        this.relativePhone = relativePhone;
        this.employeePhoto = employeePhoto;
        this.birthDate = birthDate;
        this.password=password;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String adress) {
        this.address = address;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setEmployeeType(int employeeType) {
        this.employeeType = employeeType;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setRelativePhone(String relativePhone) {
        this.relativePhone = relativePhone;
    }

    public void setEmployeePhoto(employeeImage employeePhoto) {
        this.employeePhoto = employeePhoto;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getDeptId() {
        return deptId;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return address;
    }

    public String getDesignation() {
        return designation;
    }

    public String getNationalId() {
        return nationalId;
    }

    public int getEmployeeType() {
        return employeeType;
    }

    public String getRelativeName() {
        return relativeName;
    }

    public String getRelation() {
        return relation;
    }

    public String getRelativePhone() {
        return relativePhone;
    }

    public employeeImage getEmployeePhoto() {
        return employeePhoto;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    // used for converting a string date into java.util.date to java.sql.date

//    public static Date getDate(String date) throws ParseException {
//        java.util.Date d=new SimpleDateFormat("yyyy/MM/dd").parse(date);
//        Date date1=new Date(d.getTime());
//        System.out.println(date1);
//        return date1;
//    }
}
