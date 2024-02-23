package dao;

import dao.dbConnection.DBConnection;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public static int addCustomer(Customer customer) throws SQLException {

        String insert = "insert into customer(name,email,address) values (?,?,?)";
        Connection connect = DBConnection.getConnection();
        PreparedStatement preparedStatement = connect.prepareStatement(insert);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getEmail());
        preparedStatement.setString(3, customer.getAddress());
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("Insert successful");
        } else {
            System.out.println("no such inserting found");
        }
        connect.close();
        return i;
    }

    public static int updateCustomer(Customer customer) throws SQLException {
        String updateQuery = "update customer set name=?,email=?,address=? where id=?";
        Connection connect = DBConnection.getConnection();
        PreparedStatement preparedStatement
                = connect.prepareStatement(updateQuery);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getEmail());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setInt(4, customer.getId());
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("Update successful");
        } else {
            System.out.println("no such id found");
        }
        connect.close();
        return i;
    }

    public static int deleteCustomer(int id) throws SQLException {
        String deleteQuery = "delete from customer where id =?";
        Connection connect = DBConnection.getConnection();
        PreparedStatement preparedStatement
                = connect.prepareStatement(deleteQuery);
        preparedStatement.setInt(1, id);
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("delete successful");
        } else {
            System.out.println("no such id found");
        }

        connect.close();
        return i;
    }

    public static Customer getCustomerById(int id) throws SQLException {
        String allCustomerQuery = "select * from customer where id=?";
        Customer customer = new Customer();
        Connection connect = DBConnection.getConnection();

        PreparedStatement preparedStatement
                = connect.prepareStatement(allCustomerQuery);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            customer.setId(resultSet.getInt(1));
            customer.setName(resultSet.getString(2));
            customer.setEmail(resultSet.getString(3));
            customer.setAddress(resultSet.getString(4));
            customer.setCreatedAt(String.valueOf(resultSet.getTimestamp(5)));
        }

        connect.close();
        return customer;
    }

    public static List<Customer> getAllCustomer() throws SQLException {
        List<Customer> list = new ArrayList<>();

        Connection connect = DBConnection.getConnection();

        PreparedStatement preparedStatement
                = connect.prepareStatement("select * from customer");
        ResultSet resultSet
                = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt(1));
            customer.setName(resultSet.getString(2));
            customer.setEmail(resultSet.getString(3));
            customer.setAddress(resultSet.getString(4));
            customer.setCreatedAt(String.valueOf(resultSet.getTimestamp(5)));
            list.add(customer);
        }

        connect.close();
        return list;
    }

    /**
     * Prints all customers in the "customers" table.
     */
    public static void printAllCustomers() {
        String sql = "SELECT * FROM customers";
        try (Statement statement = DBConnection.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("customerId= " + resultSet.getInt("customerId") + ", "  +
                                   "name= " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
