package com.guide_operator.Dao;

import com.guide_operator.Entities.Guide;
import com.guide_operator.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@Repository("mysql")
public class MysqlUserDao implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static class UserRowMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setFullname(resultSet.getString("fullname"));
            user.setAdress(resultSet.getString("address"));
            user.setEmail(resultSet.getString("email"));
            user.setPhonenumber(resultSet.getString("phonenumber"));
            user.setIs_guide(resultSet.getInt("is_guide"));

            return user;
        }
    }
    @Override
    public Collection<User> getAllUser(){
        // SELECT column_name(s) FROM table_name
        final String sql="SELECT * FROM user";
        List<User> users=jdbcTemplate.query(sql,new UserRowMapper());
        return users;
    }

    @Override
    public User getUserById(int id){
        // SELECT column_name(s) FROM table_name where column = value
        final String sql="SELECT * FROM user where id=?";
        User users=jdbcTemplate.queryForObject(sql,new UserRowMapper(),id);
        return users;
    }
    @Override
    //method for login
    public User getUserByUsername(String username){
        // SELECT column_name(s) FROM table_name where column = value
        final String sql="SELECT * FROM user where username=?";
        User users=jdbcTemplate.queryForObject(sql,new UserRowMapper(),username);
        return users;
    }
    @Override
    public void removeUserById(int id){

    }
    @Override
    public void updateUser(User user){

    }

    @Override
    public  void  insertUsertoDb(User user){
//        // INSERT INTO table_name (column1, column2, column3,...)
//        // VALUES (value1, value2, value3,...)
//        // INSERT INTO table_name1 (column1, column2, column3,...) SELECT column1, column2, column3,... FROM table_name2 WHERE condition
//        if(user.getIs_guide()==1) {
//        final String sql = "INSERT INTO User (username, password, fullname,address,phonenumber,email, is_guide) Values (?,?,?,?,?,?,?) ";
//
//        final  String username =
    }
    public void insertGuidetoDb(Guide guide){
        // INSERT INTO table_name (column1, column2, column3,...)
//        // VALUES (value1, value2, value3,...)
//        // INSERT INTO table_name1 (column1, column2, column3,...) SELECT column1, column2, column3,... FROM table_name2 WHERE condition
        final String sql = "INSERT INTO User (username, password, fullname,address,phonenumber,email, is_guide) Values (?,?,?,?,?,?,?);";
        final  String sql1="INSERT INTO guide (userid,cardnumber, cardtype, status,expirationdate, experience, gender) values((SELECT id FROM user WHERE user.username=?),?,?,?,?,?,?);";
        final String username= guide.getUsername();
        final String password= guide.getPassword();
        final String fullname=guide.getFullname();
        final String adress=guide.getAdress();
        final String phonenumber=guide.getPhonenumber();
        final String email=guide.getEmail();
        final int userid = guide.getUserid();
        final String cardnumber=guide.getCardnumber();
        final String cardtype=guide.getCardtype();
        final  String status=guide.getStatus();
        final Date expirationdate=guide.getExpirationdate();
        final String experience=guide.getExperience();
        final String gender=guide.getGender();

        jdbcTemplate.update(sql,new Object[]{username,password,fullname,adress,phonenumber,email});
        jdbcTemplate.update(sql1, new Object[]{userid,cardnumber,cardtype,status,expirationdate,experience,gender});


    }
}
