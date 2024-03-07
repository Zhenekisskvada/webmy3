package com.example.webmy3.db;

import com.example.webmy3.data.Result;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBManager {

    private final Connection connection;

    private static final String ADD = "INSERT INTO results VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT result, x, y, r, time, execution_time FROM results";
    private static final String CLEAN_TABLE = "DELETE FROM results;";


    public DBManager(Connection connection) {
        this.connection = connection;
    }

    public int addNewResult(Result result) throws SQLException {
        int rows = 0;
        PreparedStatement addStatement = connection.prepareStatement(ADD);
        addStatement.setBoolean(1, result.isResult());
        addStatement.setFloat(2, (float) result.getX());
        addStatement.setFloat(3, (float) result.getY());
        addStatement.setFloat(4, (float) result.getR());
        addStatement.setString(5, result.getCurrentTime());
        addStatement.setDouble(6, result.getExecutionTime());
        rows = addStatement.executeUpdate();
        return rows;
    }
    public void cleanDB() throws SQLException {
        PreparedStatement addStatement = connection.prepareStatement(CLEAN_TABLE);
        addStatement.executeUpdate();
    }

    public List<Result> getHistory() throws SQLException {
        List<Result> list = new LinkedList<>();
        PreparedStatement getAllStatement = connection.prepareStatement(GET_ALL);
        ResultSet resultSet = getAllStatement.executeQuery();
        while (resultSet.next()) {
            boolean res = resultSet.getBoolean("result");
            double x = round(resultSet.getFloat("x"), 2);
            double y = round(resultSet.getFloat("y"), 2);
            double r = round(resultSet.getFloat("r"), 2);
            String time = resultSet.getString("time");
            long execution_time = resultSet.getLong("execution_time");
            list.add(new Result(x, y, r, time, execution_time, res));
        }
        return list;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}