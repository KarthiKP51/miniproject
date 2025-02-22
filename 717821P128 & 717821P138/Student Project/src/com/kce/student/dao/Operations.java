package com.kce.student.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.kce.student.util.DBUtil;

public class Operations {
   
    public void addStudent(int id,String name,String grade,int age,String departmentName){
        Connection con = DBUtil.getConnection();
        try{
        PreparedStatement ps = con.prepareStatement("insert into student_details values(?,?,?,?)");
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, grade);
        ps.setInt(4, age);
        ps.executeUpdate();

        ps=con.prepareStatement("insert into department_details values(?,?)");
        ps.setInt(1, id);
        ps.setString(2, departmentName);
        ps.executeUpdate();

    }catch(SQLException e){
        e.printStackTrace();
    }
    }

   
    public void removeStudent(int id){
        Connection con = DBUtil.getConnection();
        try{
            PreparedStatement ps=con.prepareStatement("delete from student_details where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();

            ps=con.prepareStatement("delete from department_details where id=?");
            ps.setInt(1, id);
            int x=ps.executeUpdate();
            System.out.println(x);
        }catch(SQLException e){
        e.printStackTrace();
    }
    }

    public void updateStudent(int id,String name,String grade,int age){
        Connection con = DBUtil.getConnection();
        try{
        PreparedStatement ps = con.prepareStatement("update student_details set name=? , grade=? , age=? where id=?");
        ps.setString(1, name);
        ps.setString(2, grade);
        ps.setInt(3, age);
        ps.setInt(4,id);
        ps.executeUpdate();

    }catch(SQLException e){
        e.printStackTrace();
    }
    }

    public void updateDepartment(int id,String departmentName){
        Connection con = DBUtil.getConnection();
        try{
        PreparedStatement ps = con.prepareStatement("update department_details set dept_name=? where id=?");
        //Student Database
        ps.setString(1, departmentName);
        ps.setInt(2,id);
        ps.executeUpdate();

    }catch(SQLException e){
        e.printStackTrace();
    }
    }

    public void searchId(int id){
        Connection con = DBUtil.getConnection();
         try{
            PreparedStatement ps = con.prepareStatement("select * from student_details where id=?");
            ps.setInt(1,id);
            ResultSet res = ps.executeQuery();
            System.out.println("Student details:\n");
            System.out.format("%-15s%-15s%-15s%-15s\n","ID","Name","Grade","Age");
            while(res.next()){
                System.out.format("%-15s%-15s%-15s%-15s\n",res.getInt(1),res.getString(2),res.getString(3),res.getInt(4));
            }
            
            ps = con.prepareStatement("select * from department_details where id=?");
            ps.setInt(1, id);
            res=ps.executeQuery();
            System.out.println("\nDepartment Details:\n");
            System.out.format("%-15s%-15s\n","ID","Department Name");
            while(res.next()){
                System.out.format("%-15s%-15s\n",res.getInt(1),res.getString(2));
            }
            System.out.println();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void displayAll(){
        Connection con = DBUtil.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("select * from student_details");
            ResultSet res = ps.executeQuery();
            System.out.println("Student details:\n");
            System.out.format("%-15s%-15s%-15s%-15s\n","ID","Name","Grade","Age");
            while(res.next()){
                System.out.format("%-15s%-15s%-15s%-15s\n",res.getInt(1),res.getString(2),res.getString(3),res.getInt(4));
            }
            
            ps = con.prepareStatement("select * from department_details");
            res = ps.executeQuery();
            System.out.println("\nDepartment Details:\n");
            System.out.format("%-15s%-15s\n","ID","Department Name");
            while(res.next()){
                System.out.format("%-15s%-15s\n",res.getInt(1),res.getString(2));
            }
            System.out.println();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
