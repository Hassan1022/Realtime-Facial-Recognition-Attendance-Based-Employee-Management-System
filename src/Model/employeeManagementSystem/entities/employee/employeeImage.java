package Model.employeeManagementSystem.entities.employee;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
public class employeeImage {

    InputStream image=null;

    public employeeImage(InputStream image) {
        this.image = image;

    }
    public InputStream getImage() {
        return image;
    }



}
