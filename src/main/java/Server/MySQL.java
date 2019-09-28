package Server;

import java.sql.*;
import java.util.Date;

public class MySQL {
    public String getCardInfo(Connection connection, String CardId, String type) {
        String typeValue = "";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select "+ type + " from card where card_id = " + CardId);
            while (rs.next()) {
                typeValue = rs.getString(type);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return typeValue;
    }

    public void insertNewCard(Connection connection, String name, String cardID, String hashedPassword) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO card VALUES (default,?,?,?,?,default)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, cardID);
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            preparedStatement.setString(4, hashedPassword);
            preparedStatement.executeUpdate();

            ResultSet tableKeys = preparedStatement.getGeneratedKeys();
            tableKeys.next();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Boolean verifyLogin(Connection connection, String cardID, String pin){
        String hashedDBpin ="";
        Boolean isMatchedPin = false;
        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT pin_password FROM card WHERE card_id = ?");
            preparedStatement.setString(1, cardID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                hashedDBpin = resultSet.getString("pin_password");
            }
            connection.close();
            PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
            isMatchedPin = passwordEncryptor.unencrypt(pin, hashedDBpin);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("isMatchedPin: " + isMatchedPin);
        return isMatchedPin;
    }
}
