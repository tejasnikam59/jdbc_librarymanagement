package jdbc_librarymanagement;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class UserCRUD {
	
public Connection getConnection() throws Exception {
		
		FileInputStream fileInputStream=new FileInputStream("librarydbConfig.properties");
		Properties properties=new Properties();
		properties.load(fileInputStream);
		
		Class.forName(properties.getProperty("className"));
		
		Connection connection=DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("password"));
		
		return connection;
	}
	
	public void signUp(User user) throws Exception {
		Connection connection = getConnection();

		PreparedStatement preparedStatement = connection
				.prepareStatement("Insert into user(name,phone,email,password) values(?,?,?,?)");

		preparedStatement.setString(1, user.getName());
		preparedStatement.setLong(2, user.getPhone());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setString(4, user.getPassword());

		int result = preparedStatement.executeUpdate();

		if (result != 0) {
			System.out.println("SignUp successfully");

		} else {
			System.out.println("Invalid !!!!");
		}

		connection.close();
	}
	
public boolean login(User user) throws Exception {
		
		Connection connection=getConnection();
		
		PreparedStatement preparedStatement=connection.prepareStatement("select password from user where email=?");
		preparedStatement.setString(1, user.getEmail());
		
		ResultSet resultSet= preparedStatement.executeQuery();;
			
		String password=null;
		while (resultSet.next()) {
			password=resultSet.getString("password");
		}
		connection.close();
		if (user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
		
		
	}
}


