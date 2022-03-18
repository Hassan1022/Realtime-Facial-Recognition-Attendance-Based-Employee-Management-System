package Model.employeeManagementSystem.entities.attendance;

import Model.employeeManagementSystem.connection.sqlConnection;
import Model.employeeManagementSystem.entities.attendance.daoAttendance.initiateLiveAttendanceInterface;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class initiateLiveAttendance implements initiateLiveAttendanceInterface {
    private static Connection connection = sqlConnection.getConnection();

    public void insert_attendance() throws Exception {
        // command to run python
        // same command as running python from terminal
        String command = "/home/hassan/anaconda3/bin/python3 /home/hassan/IdeaProjects/Normal/python/real_time_facial_recognition_attendance.py";

        // running a separate process that runs the python script for facial recognition
        Process p = Runtime.getRuntime().exec(command);
    }

    public int retreiveAttendance(int employeeId) throws SQLException, ParseException {
        int present = 0;
        String s = "" + java.time.LocalDate.now();
        String array[] = s.split("-");
        System.out.println(array[0] + " " + array[1]);
        s = array[0] + "-" + array[1] + "-" + "01";
        String t = array[0] + "-" + array[1] + "-" + "31";
        java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        Date date1 = new Date(d.getTime());
        d = new SimpleDateFormat("yyyy-MM-dd").parse(t);
        Date date2 = new Date(d.getTime());

        String query = "select count(*) as present from Attendance where (employeeId=? and (date>? and date<?) " +
                "and attendance=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setDate(2, date1);
        preparedStatement.setDate(3, date2);
        preparedStatement.setInt(4, 1);

        ResultSet resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {
            present = resultSet.getInt("present");
        }
        return present;
    }

    public  ArrayList<Integer>retreiveMonthAttendance(int employeeId) throws SQLException, ParseException {
        ArrayList<Integer> attendanceCount= new ArrayList<>();


        String daTe = "" + java.time.LocalDate.now();
        String array[] = daTe.split("-");
        String month = array[1];
        boolean logic=true;
        int i = 1;
        while (logic) {
            String m="";
            if (i<=9){
                m="0" + i;
            }
            else{
                m="" + i;
            }
            if (!(m.equals(month) || m.equals(month))){
                System.out.println(m);
                int present = 0;

                String s = "" + java.time.LocalDate.now();

//                System.out.println(array[0] + " " + array[1]);
                s = array[0] + "-" + m + "-" + "01";
                String t="";
                if (i==1 || i==3 || i==5|| i==7 || i==8 || i==10 || i==12) {
                     t = array[0] + "-" + m + "-" + "31";
                }
                else if(i==2){
                     t = array[0] + "-" + m + "-" + "28";
                }
                else{
                     t = array[0] + "-" + m + "-" + "30";
                }

                java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(s);
                Date date1 = new Date(d.getTime());
                d = new SimpleDateFormat("yyyy-MM-dd").parse(t);
                Date date2 = new Date(d.getTime());

                System.out.println(date1);

                System.out.println(date2);


                String query = "select count(*) as present from Attendance where (employeeId=? and (date>=? and date<?) " +
                            "and attendance=?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, employeeId);
                preparedStatement.setDate(2, date1);
                preparedStatement.setDate(3, date2);
                preparedStatement.setInt(4, 1);


                ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()) {
                        System.out.println(resultSet.getInt("present"));
                        attendanceCount.add(resultSet.getInt("present"));
                    }

            }
            else{
                logic=false;
            }
            i=i+1;
        }
        return attendanceCount;
    }


    public  ArrayList<Integer>retreiveLateAttendance(int employeeId) throws SQLException, ParseException {
        ArrayList<Integer> attendanceCount= new ArrayList<>();
        ArrayList<Integer> lateCount= new ArrayList<>();

        String daTe = "" + java.time.LocalDate.now();
        String array[] = daTe.split("-");
        String month = array[1];
        boolean logic=true;
        int i = 1;
        while (logic) {
            String m="";
            if (i<=9){
                m="0" + i;
            }
            else{
                m="" + i;
            }
            if (!(m.equals(month) || m.equals(month))){
                System.out.println(m);
                int present = 0;

                String s = "" + java.time.LocalDate.now();

//                System.out.println(array[0] + " " + array[1]);
                s = array[0] + "-" + m + "-" + "01";
                String t="";
                if (i==1 || i==3 || i==5|| i==7 || i==8 || i==10 || i==12) {
                    t = array[0] + "-" + m + "-" + "31";
                }
                else if(i==2){
                    t = array[0] + "-" + m + "-" + "28";
                }
                else{
                    t = array[0] + "-" + m + "-" + "30";
                }

                java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(s);
                Date date1 = new Date(d.getTime());
                d = new SimpleDateFormat("yyyy-MM-dd").parse(t);
                Date date2 = new Date(d.getTime());

                System.out.println(date1);

                System.out.println(date2);

                String query2="select count(*) as present from Attendance where (employeeId=? and (date>=? and date<?) " +
                        "and attendance=? and time>?)";

                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                preparedStatement2.setInt(1, employeeId);
                preparedStatement2.setDate(2, date1);
                preparedStatement2.setDate(3, date2);
                preparedStatement2.setInt(4, 1);
                preparedStatement2.setString(5, "10:00:00");

                ResultSet resultSet2 = preparedStatement2.executeQuery();

                while (resultSet2.next()) {
                    System.out.println(resultSet2.getInt("present"));
                    lateCount.add(resultSet2.getInt("present"));
                }
            }
            else{
                logic=false;
            }
            i=i+1;
        }
        return lateCount;
    }


//    public static void main(String [] args) throws Exception{
//       System.out.println(retreiveMonthAttendance(1));
//    }
}

