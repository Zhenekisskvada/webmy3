package com.example.webmy3.beans;

import com.example.webmy3.data.Result;
import com.example.webmy3.db.DBConnector;
import com.example.webmy3.db.DBManager;
import com.example.webmy3.managers.AreaChecker;
import com.example.webmy3.managers.Validator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@ViewScoped
@Named
@ApplicationScoped
public class AreaCheckBean implements Serializable {

    private final AreaChecker areaChecker;
    private final Validator validator;
    private DBManager databaseManager;

    public AreaCheckBean() {
        areaChecker = new AreaChecker();
        validator = new Validator();
        DBConnector connector;

        System.out.println("========================================");

        try {
            connector = new DBConnector();
            Connection connection = connector.getConnection();
            if (connection != null) {
                databaseManager = new DBManager(connection);
            } else {
                System.err.println("Unable to establish a database connection.");
            }
        } catch (Exception e) {
            System.err.println("Error initializing database connection:");
            e.printStackTrace();
        }
    }

    private String x;
    private String y;
    private String r="2";

    private boolean isXCorrect = true;
    private boolean isYCorrect = true;
    private boolean isRCorrect = true;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        System.out.println("set x "+x);
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setR(String rik) {
        System.out.println("set r "+rik);
        r = rik;
    }


    public void setY(String y) {
        System.out.println("set y "+y);

        this.y = y;
    }

    public String getR() {
        return r;
    }
    public void submit( ) {
        long start = System.nanoTime();

        Double numX = validator.validateX(x);
        Double numY = validator.validateY(y);
        Double numR = validator.validateR(r);

        if (numX == null) {
            isXCorrect = false;
        } else {
            isXCorrect = true;
        }

        if (numY == null) {
            isYCorrect = false;
        } else {
            isYCorrect = true;
        }

        if (numR == null) {
            isRCorrect = false;
        } else {
            isRCorrect = true;
        }

        if (isXCorrect && isYCorrect && isRCorrect) {
            Result result = new Result(numX, numY, numR,
                    new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()),
                    System.nanoTime() - start,
                    areaChecker.check(numX, numY, numR));

            try {

                databaseManager.addNewResult(result);
                System.out.println("Точка добавлена");

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void cleaner() {
            try {
                databaseManager.cleanDB();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    public List<Result> getHistory() {
        List<Result> list = new ArrayList<>();

        try {
            list = databaseManager.getHistory();
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Result> getReversedHistory() {
        List<Result> copy = new LinkedList<>(getHistory());
        Collections.reverse(copy);
        return copy;
    }

    public boolean isXCorrect() {
        return isXCorrect;
    }

    public boolean isYCorrect() {
        return isYCorrect;
    }

    public boolean isRCorrect() {
        return isRCorrect;
    }


}