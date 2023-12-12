package jdbc_librarymanagement;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class BookCRUD {
	public Connection getConnection() throws Exception {

		FileInputStream fileInputStream = new FileInputStream("librarydbConfig.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);

		Class.forName(properties.getProperty("className"));

		Connection connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("user"), properties.getProperty("password"));

		return connection;
	}

	public void addBook(Book book) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("Insert into book(name,authorname,price,genre) values(?,?,?,?)");
		preparedStatement.setString(1, book.getName());
		preparedStatement.setString(2, book.getAuthor());
		preparedStatement.setDouble(3, book.getPrice());
		preparedStatement.setString(4, book.getGenre());

		int result = preparedStatement.executeUpdate();
		
		if(result!=0) {
			System.out.println("Book Details Saved....");
		}
		else {
			System.out.println("Somthing Wrong....\n Try again");
		}
		connection.close();
	}

	public void showBookAll(Book book) throws Exception  {
		Connection connection = getConnection();
		
		PreparedStatement preparedStatement=connection.prepareStatement("Select * from book");
		
		ResultSet resultSet= preparedStatement.executeQuery();
		if(resultSet.next()) {
			ResultSet resultSet1= preparedStatement.executeQuery();
			while (resultSet1.next()) {
					System.out.println("ID: "+resultSet1.getInt("id"));
					System.out.println("Name: "+resultSet1.getString("name"));
					System.out.println("Author: "+resultSet1.getString("authorname"));
					System.out.println("Price: "+resultSet1.getDouble("price"));
					System.out.println("Genre: "+resultSet1.getString("genre"));
					System.out.println("------------------------------");
					
			}

		}
		else {
			System.out.println("No Data Found...");
		}
		connection.close();
		
	}

	public void findByID(int id) throws Exception {
		Connection connection=getConnection();
		
		PreparedStatement preparedStatement=connection.prepareStatement("Select * from book where id=?");
		preparedStatement.setInt(1, id);
		
		ResultSet resultSet= preparedStatement.executeQuery();
		if(resultSet.next()) {
			ResultSet resultSet1= preparedStatement.executeQuery();
			while (resultSet1.next()) {
					System.out.println("Name: "+resultSet1.getString("name"));
					System.out.println("Author: "+resultSet1.getString("authorname"));
					System.out.println("Price: "+resultSet1.getDouble("price"));
					System.out.println("Genre: "+resultSet1.getString("genre"));
					System.out.println("------------------------------");
					
			}

		}
		else {
			System.out.println("No Data Found...");
		}
		connection.close();
		
	}

	public void findByName(String name) throws Exception {
		Connection connection=getConnection();
		
		PreparedStatement preparedStatement=connection.prepareStatement("Select * from book where name=?");
		preparedStatement.setString(1, name);
		
		ResultSet resultSet= preparedStatement.executeQuery();
		if(resultSet.next()) {
			ResultSet resultSet1= preparedStatement.executeQuery();
			while (resultSet1.next()) {
					System.out.println("Name: "+resultSet1.getString("name"));
					System.out.println("Author: "+resultSet1.getString("authorname"));
					System.out.println("Price: "+resultSet1.getDouble("price"));
					System.out.println("Genre: "+resultSet1.getString("genre"));
					System.out.println("------------------------------");
					
			}

		}
		else {
			System.out.println("No Data Found...");
		}
		connection.close();
		
	}

	public void findByAuthorName(String author) throws Exception {
		
		Connection connection=getConnection();
		
		PreparedStatement preparedStatement=connection.prepareStatement("Select * from book where authorname=?");
		preparedStatement.setString(1, author);
		
		ResultSet resultSet= preparedStatement.executeQuery();
		if(resultSet.next()) {
			ResultSet resultSet1= preparedStatement.executeQuery();
			while (resultSet1.next()) {
					System.out.println("Name: "+resultSet1.getString("name"));
					System.out.println("Author: "+resultSet1.getString("authorname"));
					System.out.println("Price: "+resultSet1.getDouble("price"));
					System.out.println("Genre: "+resultSet1.getString("genre"));
					System.out.println("------------------------------");
					
			}

		}
		else {
			System.out.println("No Data Found...");
		}
		connection.close();
	}

	public void findByGenre(String genre) throws Exception {
		
		Connection connection=getConnection();
		
		PreparedStatement preparedStatement=connection.prepareStatement("Select * from book where genre=?");
		preparedStatement.setString(1, genre);
		
		ResultSet resultSet= preparedStatement.executeQuery();
		if(resultSet.next()) {
			ResultSet resultSet1= preparedStatement.executeQuery();
			while (resultSet1.next()) {
					System.out.println("Name: "+resultSet1.getString("name"));
					System.out.println("Author: "+resultSet1.getString("authorname"));
					System.out.println("Price: "+resultSet1.getDouble("price"));
					System.out.println("Genre: "+resultSet1.getString("genre"));
					System.out.println("------------------------------");
					
			}

		}
		else {
			System.out.println("No Data Found...");
		}
		connection.close();
		
	}

	public void updateBook(Book book) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("update book set name=?,authorname=?,price=?,genre=? where id=?");
		preparedStatement.setString(1, book.getName());
		preparedStatement.setString(2, book.getAuthor());
		preparedStatement.setDouble(3, book.getPrice());
		preparedStatement.setString(4, book.getGenre());
		preparedStatement.setInt(5, book.getId());
		
		int result=preparedStatement.executeUpdate();
		if(result!=0) {
			System.out.println("Book Information Updated...");
		}
		else {
			System.out.println("Book Information not Updated...");
		}
		connection.close();
		
	}

	public void deleteByGenre(String genre) throws Exception {
		
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("delete from book where genre=?");
		preparedStatement.setString(1, genre);
		
		int result=preparedStatement.executeUpdate();
		if(result!=0) {
			System.out.println("Book Information Deleted...");
		}
		else {
			System.out.println("Book Information not Deleted...");
		}
		connection.close();
	}

	public void deleteByAuthor(String author) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("delete from book where authorname=?");
		preparedStatement.setString(1, author);
		
		int result=preparedStatement.executeUpdate();
		if(result!=0) {
			System.out.println("Book Information Deleted...");
		}
		else {
			System.out.println("Book Information not Deleted...");
		}
		connection.close();
		
	}

	public void deleteByID(int id) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("delete from book where id=?");
		preparedStatement.setInt(1, id);
		
		int result=preparedStatement.executeUpdate();
		if(result!=0) {
			System.out.println("Book Information Deleted...");
		}
		else {
			System.out.println("Book Information not Deleted...");
		}
		connection.close();
		
	}

	public void deleteByName(String name) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("delete from book where name=?");
		preparedStatement.setString(1, name);
		
		int result=preparedStatement.executeUpdate();
		if(result!=0) {
			System.out.println("Book Information Deleted...");
		}
		else {
			System.out.println("Book Information not Deleted...");
		}
		connection.close();
		
	}

}