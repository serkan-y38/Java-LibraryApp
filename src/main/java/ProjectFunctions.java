import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectFunctions {

    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    //Connect to database
    public ProjectFunctions() {

        String url = "jdbc:mysql://" + DatabaseModel.host + ":" + DatabaseModel.port + "/" + DatabaseModel.dbName+ "?useUnicode=true&characterEncoding=utf8";

        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver is not findable");
        }

        try {
            con = DriverManager.getConnection(url, DatabaseModel.usernameDb, DatabaseModel.passwordDb);
            System.out.println("Connected Successfully");

        } catch (SQLException ex) {
            System.out.println("Ops Something Went Wrong ");
            //ex.printStackTrace();
        }

    }
    
    public static void main(String[]args){
        ProjectFunctions projectFunctions =new ProjectFunctions();
    }
    //Connect to database


    public boolean login(String username, String password) {
        String query = "Select * From admin where username = ? and password = ?";

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(ProjectFunctions.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }


    public ArrayList<PersonModel> getPersonTable(){
        ArrayList<PersonModel> personModels = new ArrayList<PersonModel>();
        
        try {
            statement=con.createStatement();
            String query="Select * from person";
            
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String bookname=rs.getString("bookname");
                Date date=rs.getDate("date");
                              
                personModels.add(new PersonModel(id, name, surname, bookname, date));
            }
            return personModels;
        
        } catch (SQLException ex) {
            Logger.getLogger(ProjectFunctions.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
           

    public void deleteFromPersonTable(int id) {

        String sorgu = "Delete From person where id = ?";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProjectFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    
    public void updatePerson(int id, String newname, String newsurname, String newbookname, Date newdate) {
       
        String query =  "Update person set  name = ? , surname = ? , bookname = ?, date = ?  where id = ?";
        
        try {
            preparedStatement = con.prepareStatement(query);
            
            preparedStatement.setString(1,newname);
            preparedStatement.setString(2,newsurname);
            preparedStatement.setString(3,newbookname);
            preparedStatement.setDate(4,newdate);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
                   
        } catch (SQLException ex) {
            Logger.getLogger(ProjectFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    public void addToPersonTable(String name, String surname, String bookname){
        
        String query = "Insert Into person (name,surname,bookname) VALUES(?,?,?)";
        try {
            preparedStatement=con.prepareStatement(query);
        
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, bookname);
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProjectFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
