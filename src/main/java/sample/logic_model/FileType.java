package sample.logic_model;

import sample.environment.Database;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FileType {

    /** CREATE NEW FILE-TYPE
     *
     * @param _title
     */
    public FileType(String _title){
        m_title = _title;
        FileType.create(_title);
    }

    public FileType(int _id, String _title){
        m_id = _id;
        m_title = _title;
    }

    public int m_id;
    public String m_title;

    // DATABASE TRANSACTIONS


    /**FIND FILE-TYPE BY ID
     *
     * @param _id
     * @return filetype
     */
    public static FileType find(int _id) throws SQLException {
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        FileType filetype = null;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM filetype WHERE id = " + _id + ";";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                filetype = new FileType(rs.getInt("id"), rs.getString("title"));
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }

        return filetype;
    }

    /**FIND FILE-TYPE BY TITLE
     *
     * @param _title
     * @return filetype
     */
    public static FileType find_by_title(String _title) throws SQLException {
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        FileType filetype = null;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM filetype WHERE title = '" + _title + "';";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                filetype = new FileType(rs.getInt("id"), rs.getString("title"));
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }

        return filetype;
    }

    /**RETURNS LIST OF ALL FILETYPES
     *
     * @param
     * @return filetypes
     */
    public static List<FileType> all(){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        List<FileType> filetypes = new ArrayList<FileType>();

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM filetype;";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                FileType filetype = new FileType(rs.getInt("id"), rs.getString("title"));
                filetypes.add(filetype);
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }

        return filetypes;
    }

    /**UPDATE FILETYPES
     *
     * @param   _title
     * @return filetype
     */
    public FileType update(String _title){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        FileType filetype = null;
        try {
            stmt = Database.m_connection.createStatement();
            stmt.execute("UPDATE filetype SET title = '" + _title + "' WHERE id = " + this.m_id + ";");
            filetype = FileType.find(this.m_id);
            System.out.println(filetype);
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        return filetype;
    }

    /**DELETE A SPECIFIC FILETYPE
     *
     * @param
     * @return result
     */
    public boolean delete(){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        boolean result = false ;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "DELETE FROM filetype WHERE id = " + this.m_id + ";";
            if(stmt.execute(query)){
                result = true;
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        return result;
    }

    /**CREATE A FILETYPE
     *
     * @param _title
     * @return filetype
     */
    public static FileType create(String _title){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        FileType filetype = null;

        try {
            stmt = Database.m_connection.createStatement();
            int affected_rows = stmt.executeUpdate("INSERT INTO filetype (title) VALUES (\"" + _title + "\");", Statement.RETURN_GENERATED_KEYS);
            if(affected_rows == 0) {
                throw new SQLException("Failed to create Record");
            } else {
                rs = stmt.getGeneratedKeys();
                if(rs.next()){
                     filetype = FileType.find(rs.getInt(1));
                }
            }
            rs = stmt.getResultSet();
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        return filetype;
    }

    @Override
    public String toString() {
        StringBuilder returnstring = new StringBuilder("<MVC.models.FileType::{");
        returnstring.append("m_id: " + m_id);
        returnstring.append(", m_title: " + m_title);
        return returnstring.append(" }>").toString();
    }
}
