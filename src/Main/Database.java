package Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Database {
    private volatile static Database instance = null;
    private Connection connection = null;
    private Database() {
        initDatabase();
    }
    public static Database getInstance() {
        if(instance == null) {
            synchronized (Database.class) {
                if(instance == null)
                    instance = new Database();
            }
        }
        return instance;
    }



    public boolean insertMemberData(String name, String password) {
        boolean retValue = false;
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(
                    "INSERT INTO member (name, pwd) "
                            + "values('"+name+"', '"+password+"')");
            retValue = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            retValue = false;
        }
        return retValue;
    }
    public void insertBookData(int id, int isbn, int number, String authors, String title, String publisher, String book_date, Boolean status, String regist_date) {
        System.out.println("insert new book");
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(
                    "INSERT INTO book (id, isbn, number, authors, title, publisher, book_date, status, regist_date) "
                            + "values('"+id+"', '"+isbn+"', '"+number+"', '"+authors+"', '"+title+"', '"+publisher+"', '"+book_date+"', '"+status+"', '"+regist_date+"')");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void initDatabase() {
        // create a database connection
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            if( !checkExistTable("member") ) {
                statement.executeUpdate(
                        "CREATE TABLE member "
                                + "(id INTEGER NOT NULL, "
                                + "name STRING NOT NULL, "
                                + "pwd STRING NOT NULL, "
                                + "PRIMARY KEY(ID AUTOINCREMENT))");
            }
            if( !checkExistTable("book") ) {
                statement.executeUpdate(
                        "CREATE TABLE book "
                                + "(id INTEGER NOT NULL, "
                                + "isbn INTEGER NOT NULL, "
                                + "number INTEGER NOT NULL, "
                                + "authors STRING NOT NULL, "
                                + "title STRING NOT NULL, "
                                + "publisher STRING NOT NULL, "
                                + "book_date STRING NOT NULL, "
                                + "status BOOLEAN NOT NULL, "
                                + "regist_date STRING NOT NULL, "
                                + "PRIMARY KEY(ID AUTOINCREMENT))");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean checkExistTable(String tableName) {
        boolean retValue = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM sqlite_master WHERE name='"+tableName+"'");
            if(rs.getInt(1) == 1)
                retValue = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return retValue;
    }

    public boolean checkExistName(String name) {
        boolean retValue = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM member WHERE name='"+name+"'");
            if(rs.getInt(1) >= 1)
                retValue = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retValue;
    }

    public boolean checkExistBookById(int id) {
        boolean retValue = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM book WHERE id='"+id+"'");
            if(rs.getInt(1) >= 1)
                retValue = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retValue;
    }

    public boolean checkExistBookByTitle(String title) {
        boolean retValue = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM book WHERE title='"+title+"'");
            if(rs.getInt(1) >= 1)
                retValue = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retValue;
    }

    /*
     * 2. 데이터베이스에서 member 테이블을 조회하여 입력된
     * name과 password가 같이 있는지 확인합니다.
     */
    public boolean checkMemberLogin(String name, String pwd) {
        boolean retValue = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM member WHERE name='"+name+"'AND pwd ='"+pwd+"'");
            if(rs.getInt(1) >= 1)
                retValue = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retValue;
    }

    public void insertMemberJTable(JTable table) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String[] header = { "id", "name", "password" };
            model.setColumnIdentifiers(header);
            model.setRowCount(0);

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM member");
            while (rs.next()) {
                String[] record = new String[3];
                record[0] = Integer.toString(rs.getInt("id"));
                record[1] = rs.getString("name");
                record[2] = rs.getString("pwd");

                model.addRow(record);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertBookJTable(JTable table) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String[] header = { "id", "isbn", "number", "authors", "title", "publisher", "book_date", "status", "regist_date" };
            model.setColumnIdentifiers(header);
            model.setRowCount(0);

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM book");
            while (rs.next()) {
                String[] record = new String[9];
                record[0] = Integer.toString(rs.getInt("id"));
                record[1] = Integer.toString(rs.getInt("isbn"));
                record[2] = Integer.toString(rs.getInt("number"));
                record[3] = rs.getString("authors");
                record[4] = rs.getString("title");
                record[5] = rs.getString("publisher");
                record[6] = rs.getString("book_date");
                record[7] = rs.getString("status");
                record[8] = rs.getString("regist_date");

                model.addRow(record);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean isCanLend(String title) {
        boolean retValue = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT status FROM book WHERE title='"+title+"'");
            while (rs.next()) {
                if (rs.getString("status").equals("false")) {
                    retValue = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retValue;
    }

    /*
     * 책을 대출 또는 반납 상태로 설정
     * true: 대출됨(반납 가능), false: 반납됨(대출 가능)
     */
    public void setBookStatus(String title, boolean val) {
        try {
            System.out.println("lend book " + title);
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE book SET status = '"+val+"' where title='" + title + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
