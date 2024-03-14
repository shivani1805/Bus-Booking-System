import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
  static final String GREEN= "\033[1;32m";
  static final String WHITE_BOLD = "\033[1;97m";
  static final String RESET = "\033[0m";
  static final String RED = "\033[0;31m";
  public static void main(String[] args) throws SQLException {
    String newUsername = "";
    String existingUsername = "";
    String role = "";
    Scanner scanner = new Scanner(System.in);

    boolean validCredentials = false;
    boolean customerDbOption = false;
    boolean adminDbOption = false;
    boolean usernameUnique = false;
    boolean newUserOrNot = false;

    Connection connection = null;
    Statement statement = null;
    String adminornot = "";

    while (!validCredentials) {
      System.out.print(WHITE_BOLD + "Enter MySQL username: " +RESET);
      String username = scanner.nextLine();
      System.out.print(WHITE_BOLD + "Enter MySQL password: "+ RESET);
      String password = scanner.nextLine();

      try {
        String url = "jdbc:mysql://localhost:3306/bus_ticket_booking";
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        validCredentials = true;


      } catch (SQLException e) {
        System.out.println(RED+"\nInvalid credentials. Please try again."+RESET);
      }
    }

    while (!newUserOrNot){
      System.out.println(WHITE_BOLD + "\nWelcome to the bus ticket booking portal!" +RESET);
      System.out.println(WHITE_BOLD + "\nAre you a new user? Type Yes or No." +RESET);
      String newornot = scanner.nextLine();
      if (newornot.equalsIgnoreCase("yes")){
        System.out.println(WHITE_BOLD + "\nDo you want to sign up as an Admin or a Customer?" + RESET);
        adminornot = scanner.nextLine();
        while (!usernameUnique) {
          try {
            System.out.println(WHITE_BOLD +"\nCreate your username" + RESET);
            newUsername = scanner.nextLine();
            String query1 = "INSERT INTO user (username, user_role) VALUES (?, ?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
            preparedStatement1.setString(1, newUsername);
            preparedStatement1.setString(2, adminornot);
            preparedStatement1.executeUpdate();
            usernameUnique = true;
          } catch (SQLException e) {
            System.out.println(RED + "This username already exists. Please choose a new one" + RESET);
          }
        }
        usernameUnique = false;
        if (adminornot.equalsIgnoreCase("admin")) {
          System.out.println("\nEnter your first name");
          String fn = scanner.nextLine();

          System.out.println("\nEnter your last name");
          String ln = scanner.nextLine();

          String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
          Pattern emailPattern = Pattern.compile(emailRegex);
          String email;
          do {
            System.out.println("\nEnter your email ID");
            email = scanner.nextLine();
            Matcher emailMatcher = emailPattern.matcher(email);
            if (!emailMatcher.matches()) {
              System.out.println(RED+"Invalid email format. Please enter a valid email."+RESET);
            }
          } while (!emailPattern.matcher(email).matches());
          String phoneRegex = "^[0-9]{10}$";
          Pattern phonePattern = Pattern.compile(phoneRegex);
          Long pn;
          do {
            System.out.println("\nEnter your phone number");
            pn = scanner.nextLong();
            scanner.nextLine();
            pn.toString();
            Matcher phoneMatcher = phonePattern.matcher(pn.toString());
            if (!phoneMatcher.matches()) {
              System.out.println(RED+"Invalid phone number format. Please enter a 10-digit number."+RESET);
            }
          } while (!phonePattern.matcher(pn.toString()).matches());
          String query1 = "INSERT INTO admin (first_name, last_name, email, phone_no, username) VALUES (?,?,?,?,?)";
          PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
          preparedStatement1.setString(1, fn);
          preparedStatement1.setString(2, ln);
          preparedStatement1.setString(3, email);
          preparedStatement1.setLong(4, pn);
          preparedStatement1.setString(5, newUsername);
          preparedStatement1.executeUpdate();
          System.out.print(GREEN+ "User profile succesfully created \n"+ RESET);
        }
        else {
          System.out.println("\nEnter your first name");
          String fn = scanner.nextLine();

          System.out.println("\nEnter your last name");
          String ln = scanner.nextLine();

          String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
          Pattern emailPattern = Pattern.compile(emailRegex);
          String email;
          do {
            System.out.println("\nEnter your email ID");
            email = scanner.nextLine();
            Matcher emailMatcher = emailPattern.matcher(email);
            if (!emailMatcher.matches()) {
              System.out.println(RED+"Invalid email format. Please enter a valid email."+RESET);
            }
          } while (!emailPattern.matcher(email).matches());
          String phoneRegex = "^[0-9]{10}$";
          Pattern phonePattern = Pattern.compile(phoneRegex);
          Long pn;
          do {
            System.out.println("\nEnter your phone number");
            pn = scanner.nextLong();
            scanner.nextLine();
            pn.toString();
            Matcher phoneMatcher = phonePattern.matcher(pn.toString());
            if (!phoneMatcher.matches()) {
              System.out.println(RED+"Invalid phone number format. Please enter a 10-digit number."+RESET);
            }
          } while (!phonePattern.matcher(pn.toString()).matches());
          String query1 = "INSERT INTO customer (first_name, last_name, email, phone_no, username) VALUES (?,?,?,?,?)";
          PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
          preparedStatement1.setString(1, fn);
          preparedStatement1.setString(2, ln);
          preparedStatement1.setString(3, email);
          preparedStatement1.setLong(4, pn);
          preparedStatement1.setString(5, newUsername);
          preparedStatement1.executeUpdate();
          System.out.print(GREEN+ "\nUser profile succesfully created \n"+ RESET);
        }
      }
      else if (newornot.equalsIgnoreCase("no")){
        newUserOrNot = true;
        System.out.println(WHITE_BOLD +"\nEnter your username: " + RESET);
        existingUsername = scanner.nextLine();
        String query = "SELECT * FROM user WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, existingUsername);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
          newUserOrNot = false;
          System.out.println(RED+"Sorry, this username doesn't exist" + RESET);
        }
        else{
          role = resultSet.getString("user_role");
          System.out.println(WHITE_BOLD +"\nYour role is: " + RESET + role);
        }
      }
      else
      { newUserOrNot = false;
        System.out.println(RED+"\nInvalid option entered. Please retry." + RESET);
      }
    }
      if (role.equalsIgnoreCase("admin")) {
        while(!adminDbOption) {
          while(!adminDbOption) {
            int adminOption;
            System.out.println(WHITE_BOLD+"\nPlease choose one of the following actions by entering the relevant option number: "+RESET);
            System.out.println("\n1) Add driver/city/route/bus details ");
            System.out.println("2) Edit driver/city/route/bus details ");
            System.out.println("3) View driver/city/route/booking/bus details ");
            System.out.println("4) Delete driver/city/route/bus details ");
            System.out.println("5) Visualize Bus Type Distribution");
            System.out.println("6) Visualize Bus Booking Chart");
            System.out.println("7) View user information");
            System.out.println("8) Edit user information");
            System.out.println("9) Quit");
            try {
              adminOption = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e){
              System.out.println(RED+"Error : Invalid Input. Please try again"+RESET);
              continue;
            }
            if(adminOption==1) {
              boolean flag = false;
              boolean addFlag=false;
              int adminAddOption;
              while(!addFlag) {

                System.out.println(WHITE_BOLD + "\nChoose the option you want to add data for : " + RESET);
                System.out.println("1) Driver\n2) City\n3) Route\n4) Bus\n5) Return to menu");
                try {
                  adminAddOption = scanner.nextInt();
                  scanner.nextLine();

                } catch(InputMismatchException e) {
                  scanner.nextLine();
                  System.out.println(RED+"Error : Invalid Input. Please try again"+RESET);
                  addFlag=false;
                  continue;
                }

                if (adminAddOption == 1) {
                  System.out.print(WHITE_BOLD + "\nEnter Driver Details\n" + RESET);
                  String firstName = validateStringInput("First Name");
                  String lastName = validateStringInput("Last name");
                  long phone = 0;
                  while (!flag) {
                    System.out.print("Phone Number : ");
                    String input = scanner.nextLine().trim();
                    if (input.length() >= 10) {
                      try {
                        phone = Long.parseLong(input);
                        flag = true;
                      } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid phone number. Please enter a valid number." + RESET);
                      }
                    } else {
                      System.out.println(RED + "Invalid phone number. Please enter a valid number." + RESET);
                    }
                  }
                  System.out.print("License Number : ");
                  String license = scanner.nextLine();
                  String procedureCall = "{CALL insert_driver(?,?,?,?)}";
                  CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
                  callableStatement2.setString(1, firstName);
                  callableStatement2.setString(2, lastName);
                  callableStatement2.setLong(3, phone);
                  callableStatement2.setString(4, license);
                  callableStatement2.execute();
                  System.out.print(GREEN + "\nDriver Details successfully inserted\n" + RESET);
                } else if (adminAddOption == 2) {
                  boolean cityFlag = false;
                  while (!cityFlag) {
                    System.out.print(WHITE_BOLD + "\nEnter City Details\n" + RESET);

                    String city = validateStringInput("City Name");
                    String state = validateStringInput("State Name");

                    if (containsDigitsOrSpecialChars(city) || containsDigitsOrSpecialChars(state)) {
                      System.out.println(RED + "City and State names cannot contain digits. Please try again." + RESET);
                      continue;
                    }

                    String procedureCall = "{CALL insert_city(?,?)}";
                    CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
                    callableStatement2.setString(1, city);
                    callableStatement2.setString(2, state);

                    try {
                      callableStatement2.execute();
                      cityFlag = true;
                    } catch (SQLIntegrityConstraintViolationException e) {
                      System.out.println(RED + "City with name " + city + " already exists." + RESET);
                    }
                  }
                  System.out.print(GREEN + "\nCity Details successfully inserted\n" + RESET);
                } else if (adminAddOption == 3) {
                  boolean timeFlag = false;
                  System.out.print(WHITE_BOLD + "\nEnter Route Details\n" + RESET);
                  String oCity = validateStringInput("Origin City");

                  String dCity = validateStringInput("Destination city");
                  Timestamp start = null;
                  Timestamp end = null;
                  while (!timeFlag) {
                    start = validateTimestampInput("Start Time");
                    end = validateTimestampInput("End Time");
                    if (end.before(start) || end.equals(start)) {
                      System.out.println(RED + "End time must be after the start time. Please enter again." + RESET);
                    } else {
                      timeFlag = true;
                    }
                  }
                  int fare = validateIntegerInput("Total fare");
                  int driverId = validateIntegerInput("Driver ID");
                  int busId = validateIntegerInput("Bus ID");
                  String procedureCall = "{CALL insert_route(?,?,?,?,?,?,?)}";
                  CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
                  callableStatement2.setInt(1, fare);
                  callableStatement2.setTimestamp(2, start);
                  callableStatement2.setTimestamp(3, end);
                  callableStatement2.setString(4, oCity);
                  callableStatement2.setString(5, dCity);
                  callableStatement2.setInt(6, driverId);
                  callableStatement2.setInt(7, busId);
                  callableStatement2.execute();
                  System.out.print(GREEN + "\nRoute Details successfully inserted\n" + RESET);

                } else if (adminAddOption == 4) {
                  System.out.print(WHITE_BOLD + "\nEnter Bus Details\n" + RESET);

                  String type = validateBusType("Bus Type");
                  int cap = validateIntegerInput("Capacity");
                  System.out.print("Model : ");
                  String model = scanner.nextLine();
                  System.out.print("Color : ");
                  String color = scanner.nextLine();
                  System.out.print("License Plate Number : ");
                  String license = scanner.nextLine();
                  String procedureCall = "{CALL insert_bus(?,?,?,?,?)}";
                  CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
                  callableStatement2.setString(1, type);
                  callableStatement2.setInt(2, cap);
                  callableStatement2.setString(3, model);
                  callableStatement2.setString(4, color);
                  callableStatement2.setString(5, license);
                  try {
                    callableStatement2.execute();
                  } catch(SQLException e) {
                    System.out.print(RED + "\nAn Error Occured\n" + RESET);

                  }
                  System.out.print(GREEN + "\nBus Details successfully inserted\n" + RESET);

                } else if(adminAddOption==5) {
                  break;
                }
                else {
                  System.out.print(RED + "Error : Invalid Input. Please try again\n" + RESET);
                  addFlag = false;

                }
              }

            }
            else if (adminOption==2) {

              boolean editFlag= true;
              while(editFlag) {
                System.out.println(WHITE_BOLD+"\nChoose the data you want to edit: "+RESET);
                System.out.println("1) Driver\n2) City\n3) Route\n4) Bus\n5) Return to menu");
                int adminEditOption ;
                try {
                  adminEditOption = scanner.nextInt();
                  scanner.nextLine();
                } catch(InputMismatchException e){
                  scanner.nextLine();
                  System.out.println(RED+"Error : Invalid Input. Please try again"+RESET);
                  continue;
                }
                if (adminEditOption == 1) {
                  System.out.print(WHITE_BOLD + "\nEnter the Driver ID to edit : " + RESET);
                  int driverID;
                  try {
                    driverID = scanner.nextInt();
                    scanner.nextLine();
                  } catch(InputMismatchException e){
                    scanner.nextLine();
                    System.out.println(RED+"Error : Invalid Input. Please try again"+RESET);
                    continue;
                  }
                  PreparedStatement preparedStatement = connection.prepareStatement("SELECT getCountOfDriver(?) AS count");
                  preparedStatement.setInt(1, driverID);
                  ResultSet resultSet = preparedStatement.executeQuery();
                  if(resultSet.next()) {
                    if (resultSet.getInt("count") < 1) {
                      System.out.println(RED + "The Driver ID : " + driverID + " does not exist." + RESET);
                      continue;
                    }
                  }

                  boolean driverEditflag=false;
                  while(!driverEditflag) {
                    driverEditflag=true;
                    System.out.print(WHITE_BOLD + "\nChoose the Driver Details you want to edit : " + RESET);
                    System.out.println("\n1) First Name\n2) Last Name\n3) Phone No.\n4) License No.");
                    int driverEdit;
                    try {
                      driverEdit = scanner.nextInt();
                      scanner.nextLine();
                    } catch(InputMismatchException e){
                      scanner.nextLine();
                      System.out.println(RED+"Error : Invalid Input. Please try again"+RESET);
                      driverEditflag=false;
                      continue;
                    }

                    if (driverEdit == 1) {
                      System.out.print(WHITE_BOLD + "\nEnter the first name : " + RESET);
                      String fName = validateStringInputForEdit("\nEnter the first name : ");

                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE driver SET first_name = ? WHERE driver_id = ?;");
                      callableStatement2.setString(1, fName);
                      callableStatement2.setInt(2, driverID);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Driver First Name Successfully Updated." + RESET);

                    } else if (driverEdit == 2) {
                      System.out.print(WHITE_BOLD + "\nEnter the last name : " + RESET);
                      String lName = validateStringInputForEdit("\nEnter last first name : ");
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE driver SET last_name = ? WHERE driver_id = ?;");
                      callableStatement2.setString(1, lName);
                      callableStatement2.setInt(2, driverID);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Driver Last Name Successfully Updated." + RESET);

                    } else if (driverEdit == 3) {

                      long phone = 0;
                      boolean phoneFlag=false;
                      while (!phoneFlag) {
                        System.out.print(WHITE_BOLD + "\nEnter the phone no. : " + RESET);
                        String input = scanner.nextLine().trim();
                        if (input.length() >= 10) {
                          try {
                            phone = Long.parseLong(input);
                            phoneFlag = true;
                          } catch (NumberFormatException e) {
                            System.out.println(RED + "Invalid phone number. Please enter a valid number." + RESET);
                          }
                        } else {
                          System.out.println(RED + "Invalid phone number. Please enter a valid number." + RESET);
                        }
                      }

                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE driver SET phone_no = ? WHERE driver_id = ?;");
                      callableStatement2.setLong(1, phone);
                      callableStatement2.setInt(2, driverID);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Driver Phone No. Successfully Updated." + RESET);

                    } else if (driverEdit == 4) {
                      System.out.print(WHITE_BOLD + "\nEnter the license no. : " + RESET);
                      String license = scanner.nextLine();
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE driver SET license_no = ? WHERE driver_id = ?;");
                      callableStatement2.setString(1, license);
                      callableStatement2.setInt(2, driverID);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Driver License No. Successfully Updated." + RESET);

                    } else {
                      System.out.println(RED + "Error : Invalid Input. Please try again\n" + RESET);
                      driverEditflag=false;
                    }
                  }
                } else if(adminEditOption==2) {
                  System.out.print(WHITE_BOLD + "\nEnter the City ID to edit : " + RESET);
                  int cityId ;
                  try {
                    cityId = scanner.nextInt();
                    scanner.nextLine();
                  } catch(InputMismatchException e){
                    scanner.nextLine();
                    System.out.println(RED+"Error : Invalid Input. Please try again"+RESET);
                    continue;
                  }
                  PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS count FROM city where city_id=? ");
                  preparedStatement.setInt(1, cityId);
                  ResultSet resultSet = preparedStatement.executeQuery();
                  if(resultSet.next()) {
                    if(resultSet.getInt("count")<1) {
                      System.out.println(RED + "City with ID : " + cityId + " does not exist." + RESET);
                      continue;
                    }

                  }
                  boolean cityEditFlag=false;
                  while(!cityEditFlag) {
                    cityEditFlag = true;
                    System.out.print(WHITE_BOLD + "\nChoose the City Details you want to edit : " + RESET);
                    System.out.println("\n1) City Name\n2) State Name");
                    int cityEdit ;
                    try {
                      cityEdit = scanner.nextInt();
                      scanner.nextLine();
                    } catch(InputMismatchException e){
                      scanner.nextLine();
                      System.out.println(RED+"Error : Invalid Input. Please try again"+RESET);
                      cityEditFlag=false;
                      continue;
                    }
                    if (cityEdit == 1) {

                      System.out.print(WHITE_BOLD + "\nEnter the city name : " + RESET);

                      String cName = validateStringInputForEdit("\nEnter the city name : ");
                      do {
                        if(containsDigitsOrSpecialChars(cName)) {
                          System.out.print(RED + "\nCity name cannot contain digits. Please try again." + RESET);
                          System.out.print(WHITE_BOLD + "\nEnter the city name : " + RESET);
                          cName = validateStringInputForEdit("\nEnter the city name :");
                        } else {
                          break;
                        }
                      } while(true);

                      try {
                        CallableStatement callableStatement2 = connection.prepareCall("UPDATE city SET city_name = ? WHERE city_id = ?;");
                        callableStatement2.setString(1, cName);
                        callableStatement2.setInt(2, cityId);
                        callableStatement2.execute();
                      } catch(SQLIntegrityConstraintViolationException e) {
                        System.out.println(RED + "City Name already exists or is currently in use and cannot be updated" + RESET);
                        continue;
                      }
                      System.out.println(GREEN + "City Name Successfully Updated." + RESET);

                    } else if (cityEdit == 2) {
                      System.out.print(WHITE_BOLD + "\nEnter the State name : " + RESET);
                      String sName = validateStringInputForEdit("\nEnter the state name : ");
                      do {
                        if(containsDigitsOrSpecialChars(sName)) {
                          System.out.print(RED + "\nState name cannot contain digits. Please try again." + RESET);
                          System.out.print(WHITE_BOLD + "\nEnter the state name : " + RESET);
                          sName = validateStringInputForEdit("\nEnter the state name :");
                        } else {
                          break;
                        }
                      } while(true);
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE city SET state = ? WHERE city_id = ?;");
                      callableStatement2.setString(1, sName);
                      callableStatement2.setInt(2, cityId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "State Name Successfully Updated." + RESET);

                    } else {
                      System.out.println(RED + "Error : Invalid Input. Please try again\n" + RESET);
                      cityEditFlag=false;
                    }
                  }
                } else if(adminEditOption==3) {
                  System.out.print(WHITE_BOLD + "\nEnter the Route ID to edit : " + RESET);

                  int routeId;
                  try {
                    routeId = scanner.nextInt();
                    scanner.nextLine();
                  } catch(InputMismatchException e){
                    scanner.nextLine();
                    System.out.println(RED+"Error : Invalid Input. Please try again"+RESET);
                    continue;
                  }
                  PreparedStatement preparedStatement = connection.prepareStatement("SELECT getCountOfRoute(?) AS count");
                  preparedStatement.setInt(1, routeId);
                  ResultSet resultSet = preparedStatement.executeQuery();
                  if(resultSet.next()) {
                    if(resultSet.getInt("count")<1) {
                      System.out.println(RED + "The Route ID : " + routeId + " does not exist." + RESET);
                      continue;
                    }

                  }
                  boolean routeEditFlag=false;
                  while(!routeEditFlag) {
                    routeEditFlag = true;
                    System.out.print(WHITE_BOLD + "\nChoose the Route Details you want to edit : " + RESET);
                    System.out.println("\n1) Origin City\n2) Destination City \n3) Board Time\n4) Arrival Time\n5) Fare\n6) Driver on route\n7) Bus on route");
                    int routeEdit;
                    try {
                      routeEdit = scanner.nextInt();
                      scanner.nextLine();
                    } catch (InputMismatchException e) {
                      scanner.nextLine();
                      System.out.println(RED + "Error : Invalid Input. Please try again" + RESET);
                      routeEditFlag = false;
                      continue;
                    }
                    if(routeEdit==1){
                      System.out.print(WHITE_BOLD + "\nEnter the Origin City : " + RESET);
                      String origin = scanner.nextLine();
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_route SET origin_city_name = ? WHERE route_id = ?;");
                      callableStatement2.setString(1, origin);
                      callableStatement2.setInt(2, routeId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Route Origin City Successfully Updated." + RESET);
                    } else if(routeEdit==2){
                      System.out.print(WHITE_BOLD + "\nEnter the Destination City : " + RESET);
                      String dest = scanner.nextLine();
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_route SET destination_city_name = ? WHERE route_id = ?;");
                      callableStatement2.setString(1, dest);
                      callableStatement2.setInt(2, routeId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Route Destination City Successfully Updated." + RESET);
                    } else if(routeEdit==3){
                      System.out.print(WHITE_BOLD + "\nEnter the boarding time : " + RESET);

                      Timestamp boardTime = validateTimestampInputForEdit("boarding time");
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_route SET start_date_time = ? WHERE route_id = ?;");
                      callableStatement2.setTimestamp(1, boardTime);
                      callableStatement2.setInt(2, routeId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Boarding Time Successfully Updated." + RESET);
                    } else if(routeEdit==4){
                      System.out.print(WHITE_BOLD + "\nEnter the arrival time : " + RESET);
                      Timestamp arrivalTime = validateTimestampInputForEdit("arrival time");
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_route SET end_date_time = ? WHERE route_id = ?;");
                      callableStatement2.setTimestamp(1, arrivalTime);
                      callableStatement2.setInt(2, routeId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Arrival Time Successfully Updated." + RESET);
                    } else if(routeEdit==5){
                      System.out.print(WHITE_BOLD + "\nEnter the new fare : " + RESET);
                      int fare = validateIntegerInputForEdit("fare");
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_route SET total_fare = ? WHERE route_id = ?;");
                      callableStatement2.setInt(1, fare);
                      callableStatement2.setInt(2, routeId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Fare Successfully Updated." + RESET);
                    } else if(routeEdit==6){
                      System.out.print(WHITE_BOLD + "\nEnter the new driver id : " + RESET);
                      int driver = validateIntegerInputForEdit("driver id");
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_route SET driver_id = ? WHERE route_id = ?;");
                      callableStatement2.setInt(1, driver);
                      callableStatement2.setInt(2, routeId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Driver ID Successfully Updated." + RESET);
                    } else if(routeEdit==7) {
                      System.out.print(WHITE_BOLD + "\nEnter the new bus id : " + RESET);
                      int bus = validateIntegerInputForEdit("bus id");
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_route SET bus_id = ? WHERE route_id = ?;");
                      callableStatement2.setInt(1, bus);
                      callableStatement2.setInt(2, routeId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Bus ID Successfully Updated." + RESET);
                    }else {
                      System.out.println(RED + "Error : Invalid Input. Please try again\n" + RESET);
                      routeEditFlag=false;
                    }
                  }
                }
                else if(adminEditOption==4){
                  System.out.print(WHITE_BOLD + "\nEnter the Bus ID to edit : " + RESET);

                  int busId ;
                  try {
                    busId = scanner.nextInt();
                    scanner.nextLine();
                  } catch(InputMismatchException e){
                    scanner.nextLine();
                    System.out.println(RED+"Error : Invalid Input. Please try again"+RESET);
                    continue;
                  }
                  PreparedStatement preparedStatement = connection.prepareStatement("SELECT getCountOfBus(?) AS count");
                  preparedStatement.setInt(1, busId);
                  ResultSet resultSet = preparedStatement.executeQuery();
                  if(resultSet.next()) {
                    if(resultSet.getInt("count")<1) {
                      System.out.println(RED + "The Bus ID : " + busId + " does not exist." + RESET);
                      continue;
                    }

                  }
                  boolean busEditFlag=false;
                  while(!busEditFlag) {
                    busEditFlag = true;
                    System.out.print(WHITE_BOLD + "\nChoose the Bus Details you want to edit : " + RESET);
                    System.out.println("\n1) Bus Type\n2) Capacity\n3) Model\n4) Color\n5) License Plate");
                    int busEdit;
                    try {
                      busEdit = scanner.nextInt();
                      scanner.nextLine();
                    } catch (InputMismatchException e) {
                      scanner.nextLine();
                      System.out.println(RED + "Error : Invalid Input. Please try again" + RESET);
                      busEditFlag = false;
                      continue;
                    }
                    if (busEdit == 1) {
                      System.out.print(WHITE_BOLD + "\nEnter the Bus Type : " + RESET);
                      String busType = scanner.nextLine();
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_type SET type = ? WHERE bus_id = ?;");
                      callableStatement2.setString(1, busType);
                      callableStatement2.setInt(2, busId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Bus Type Successfully Updated." + RESET);

                    } else if (busEdit == 2) {
                      System.out.print(WHITE_BOLD + "\nEnter the new Bus Capacity : " + RESET);
                      int capacity = validateIntegerInputForEdit("Bus Capacity");
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_type SET capacity = ? WHERE bus_id = ?;");
                      callableStatement2.setInt(1, capacity);
                      callableStatement2.setInt(2, busId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Bus Capacity Successfully Updated." + RESET);

                    } else if (busEdit == 3) {
                      System.out.print(WHITE_BOLD + "\nEnter the Bus Model : " + RESET);
                      String model = scanner.nextLine();
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_type SET model = ? WHERE bus_id = ?;");
                      callableStatement2.setString(1, model);
                      callableStatement2.setInt(2, busId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Bus Model Successfully Updated." + RESET);

                    } else if (busEdit == 4) {
                      System.out.print(WHITE_BOLD + "\nEnter the Bus Color : " + RESET);
                      String color = scanner.nextLine();
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_type SET color = ? WHERE bus_id = ?;");
                      callableStatement2.setString(1, color);
                      callableStatement2.setInt(2, busId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "Bus Color Successfully Updated." + RESET);

                    } else if (busEdit == 5) {
                      System.out.print(WHITE_BOLD + "\nEnter the License Plate No. : " + RESET);
                      String lPlate = scanner.nextLine();
                      CallableStatement callableStatement2 = connection.prepareCall("UPDATE bus_type SET license_plate = ? WHERE bus_id = ?;");
                      callableStatement2.setString(1, lPlate);
                      callableStatement2.setInt(2, busId);
                      callableStatement2.execute();
                      System.out.println(GREEN + "License Plate Successfully Updated." + RESET);

                    } else {
                      System.out.println(RED + "Error : Invalid Input. Please try again\n" + RESET);
                      busEditFlag = false;
                    }
                  }
                }
                else if(adminEditOption==5){
                  break;
                } else {
                  System.out.println(RED + "Error : Invalid Input. Please try again\n" + RESET);
                }
              }

            }
            else if(adminOption==3) {
              boolean viewFlag=false;
              while(!viewFlag) {
                System.out.println(WHITE_BOLD + "\nChoose the data you want to view: " + RESET);
                System.out.println("1) Driver\n2) City\n3) Route\n4) Booking\n5) Bus\n6) Return to menu");
                int adminViewOption;
                try {
                  adminViewOption = scanner.nextInt();
                  scanner.nextLine();
                } catch(InputMismatchException e){
                  scanner.nextLine();
                  System.out.println(RED+"Error : Invalid Input. Please try again"+RESET);
                  continue;
                }
                if (adminViewOption == 1) {
                  PreparedStatement ps = connection.prepareStatement("SELECT * FROM driver;");
                  ResultSet resultSet = ps.executeQuery();
                  System.out.format(WHITE_BOLD + "\n--------------------------------------------------------------------------------" + RESET);
                  System.out.format(WHITE_BOLD + "%n%1s\t\t%-10s\t\t%10s\t\t%15s\t\t%20s\t%n", "ID ", "First Name", "Last Name", "Phone No", "License No" + RESET);
                  System.out.format(WHITE_BOLD + "--------------------------------------------------------------------------------\n" + RESET);
                  while (resultSet.next()) {
                    System.out.format(WHITE_BOLD + "%2s\t\t%-10s\t\t%5s\t\t%20s\t\t%10s\t%n" + RESET, resultSet.getString("driver_id")
                            , resultSet.getString("first_name"),
                            resultSet.getString("last_name")
                            , resultSet.getLong("phone_no"),
                            resultSet.getString("license_no"));
                  }
                } else if (adminViewOption == 2) {
                  PreparedStatement ps = connection.prepareStatement("SELECT * FROM city;");
                  ResultSet resultSet = ps.executeQuery();
                  System.out.format(WHITE_BOLD + "\n-------------------------------------" + RESET);
                  System.out.format(WHITE_BOLD + "%n%1s\t\t%10s\t\t%15s\t%n", "ID ", "City", "State" + RESET);
                  System.out.format(WHITE_BOLD + "-------------------------------------\n" + RESET);
                  while (resultSet.next()) {
                    System.out.format(WHITE_BOLD + "%2s\t\t%10s\t\t%12s\t%n" + RESET, resultSet.getInt("city_id")
                            , resultSet.getString("city_name"),
                            resultSet.getString("state"));
                  }
                } else if (adminViewOption == 3) {
                  PreparedStatement ps = connection.prepareStatement("CALL view_bus_route();");
                  ResultSet resultSet = ps.executeQuery();
                  System.out.format(WHITE_BOLD + "%n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
                  System.out.format(WHITE_BOLD + "%1s\t\t%-10s\t\t%15s\t\t%20s\t\t%17s\t\t%22s\t\t%27s\t\t%13s\t\t%13s\t\t%13s%n", "ID", "Origin", "Destination", "Duration(hours)", "Board Time", "Arrival Time", "Bus License Plate", "Driver Name", "Driver License", "Fare(USD)" + RESET);
                  System.out.format(WHITE_BOLD + "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);

                  while (resultSet.next()) {
                    System.out.format(WHITE_BOLD + "%2s\t\t%10s\t\t%16s\t\t%10s\t\t%30s\t\t%14s\t\t%16s\t\t%17s\t\t%11s\t\t%10s%n" + RESET,
                            resultSet.getInt("route_id"),
                            resultSet.getString("origin_city_name"),
                            resultSet.getString("destination_city_name"),
                            resultSet.getLong("duration"),
                            resultSet.getTimestamp("start_date_time"),
                            resultSet.getTimestamp("end_date_time"),
                            resultSet.getString("license_plate"),
                            resultSet.getString("driver_name"),
                            resultSet.getString("license_no"),
                            resultSet.getInt("total_fare"));
                  }

                } else if (adminViewOption == 4) {
                  PreparedStatement ps = connection.prepareStatement("CALL view_cust_booking();");
                  ResultSet resultSet = ps.executeQuery();
                  System.out.format(WHITE_BOLD + "%n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
                  System.out.format(WHITE_BOLD + "%1s\t\t%-10s\t\t%10s\t\t%15s\t\t%-10s\t\t%22s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s%n", "Booking ID", "Origin city", "Destination city", "Amount Paid", "Duration", "Board Time", "Arrival Time", "Seats Booked", "Payment Method", "Bus Type", "Bus Capacity", "Bus Model", "Bus Color", "Driver's First Name", "Driver's Last Name" + RESET);
                  System.out.format(WHITE_BOLD + "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
                  while(resultSet.next()) {
                    System.out.format(WHITE_BOLD + "%1s\t\t%15s\t\t%15s\t\t%18s\t\t%9s\t\t%26s\t\t%26s\t\t%18s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%20s%n" + RESET,
                            resultSet.getString("booking_id"),
                            resultSet.getString("origin_city_name"),
                            resultSet.getString("destination_city_name"),
                            resultSet.getString("amount_paid"),
                            resultSet.getString("duration"),
                            resultSet.getString("start_date_time"),
                            resultSet.getString("end_date_time"),
                            resultSet.getString("seats_booked"),
                            resultSet.getString("payment_method"),
                            resultSet.getString("type"),
                            resultSet.getString("capacity"),
                            resultSet.getString("model"),
                            resultSet.getString("color"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"));
                  }


                } else if (adminViewOption == 5) {
                  PreparedStatement ps = connection.prepareStatement("SELECT * FROM bus_type;");
                  ResultSet resultSet = ps.executeQuery();
                  System.out.format(WHITE_BOLD + "%n----------------------------------------------------------------------------------------------------%n" + RESET);
                  System.out.format(WHITE_BOLD + "%1s\t\t%-14s\t\t%10s\t\t%20s\t\t%9s\t\t%15s%n", "ID", "Type", "Capacity", "License Plate", "Model", "Color" + RESET);
                  System.out.format(WHITE_BOLD + "----------------------------------------------------------------------------------------------------%n" + RESET);
                  while (resultSet.next()) {
                    System.out.format(WHITE_BOLD + "%2s\t\t%-14s\t\t%5s\t\t%17s\t\t%17s\t\t%11s%n" + RESET,
                            resultSet.getInt("bus_id"),
                            resultSet.getString("type"),
                            resultSet.getInt("capacity"),
                            resultSet.getString("license_plate"),
                            resultSet.getString("model"),
                            resultSet.getString("color"));
                  }
                } else if(adminViewOption==6) {
                  break;
                } else {
                  System.out.println(RED + "Error : Invalid Input. Please try again\n" + RESET);
                }
              }
            }
            else if(adminOption==4) {
              boolean adminDeleteFlag=false;
              while (!adminDeleteFlag) {
                System.out.println(WHITE_BOLD + "\nChoose the data you want to delete: \n" + RESET);
                System.out.println("1) Driver\n2) City\n3) Route\n4) Bus\n5) Return to menu");
                int adminDeleteOption;
                try {
                  adminDeleteOption = scanner.nextInt();
                  scanner.nextLine();
                } catch (InputMismatchException e) {
                  scanner.nextLine();
                  System.out.println(RED + "Error : Invalid Input. Please try again" + RESET);
                  continue;
                }
                if(adminDeleteOption==1) {
                  System.out.print(WHITE_BOLD+"\nEnter data to be deleted \n"+RESET);
                  int driverID = validateIntegerInput("Driver ID");

                  PreparedStatement preparedStatement = connection.prepareStatement("SELECT getCountOfDriver(?) AS count");
                  preparedStatement.setInt(1, driverID);
                  ResultSet resultSet = preparedStatement.executeQuery();
                  if(resultSet.next()) {
                    if(resultSet.getInt("count")<1) {
                      System.out.println(RED + "The Driver ID : " + driverID + " does not exist." + RESET);
                      continue;
                    }

                  }
                  CallableStatement callableStatement2 = connection.prepareCall("DELETE FROM driver WHERE driver_id = ?");
                  callableStatement2.setInt(1, driverID);
                  try {
                    callableStatement2.execute();
                  } catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println(RED + "The Driver ID : " + driverID + " is currently in use and cannot be deleted." + RESET);
                    continue;
                  }
                  System.out.println(GREEN + "Driver ID : " + driverID + " successfully deleted.");

                } else if(adminDeleteOption==2) {
                  System.out.print(WHITE_BOLD+"\nEnter data to be deleted \n"+RESET);
                  String cName = validateStringInput("City name");
                  do {
                    if(containsDigitsOrSpecialChars(cName)) {
                      System.out.print(RED + "City name cannot contain digits. Please try again.\n" + RESET);
                      cName = validateStringInput("City name ");
                    } else {
                      break;
                    }
                  } while(true);


                  PreparedStatement preparedStatement = connection.prepareStatement("SELECT getCountOfCity(?) AS count");
                  preparedStatement.setString(1, cName);
                  ResultSet resultSet = preparedStatement.executeQuery();
                  if(resultSet.next()) {
                    if(resultSet.getInt("count")<1) {
                      System.out.println(RED + "City with name : " + cName + " does not exist." + RESET);
                      continue;
                    }

                  }
                  CallableStatement callableStatement2 = connection.prepareCall("DELETE FROM city WHERE city_name = ?");
                  callableStatement2.setString(1, cName);
                  try {
                    callableStatement2.execute();
                  } catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println(RED + "City Name : " + cName + " is currently in use and cannot be deleted." + RESET);
                    continue;
                  }
                  System.out.println(GREEN + "City : " + cName + " successfully deleted.");


                } else if(adminDeleteOption==3) {
                  System.out.print(WHITE_BOLD+"\nEnter data to be deleted \n"+RESET);
                  int routeId = validateIntegerInput("Route ID");

                  PreparedStatement preparedStatement = connection.prepareStatement("SELECT getCountOfRoute(?) AS count");
                  preparedStatement.setInt(1, routeId);
                  ResultSet resultSet = preparedStatement.executeQuery();
                  if(resultSet.next()) {
                    if(resultSet.getInt("count")<1) {
                      System.out.println(RED + "The Route ID : " + routeId + " does not exist." + RESET);
                      continue;
                    }

                  }
                  CallableStatement callableStatement2 = connection.prepareCall("DELETE FROM bus_route WHERE route_id = ?");
                  callableStatement2.setInt(1, routeId);
                  try {
                    callableStatement2.execute();
                  } catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println(RED + "The Route ID : " + routeId + " is currently in use and cannot be deleted." + RESET);
                    continue;
                  }
                  System.out.println(GREEN + "Route ID : " + routeId + " successfully deleted.");

                } else if(adminDeleteOption==4) {
                  System.out.print(WHITE_BOLD+"\nEnter data to be deleted \n"+RESET);
                  int busId = validateIntegerInput("Bus ID");

                  PreparedStatement preparedStatement = connection.prepareStatement("SELECT getCountOfBus(?) AS count");
                  preparedStatement.setInt(1, busId);
                  ResultSet resultSet = preparedStatement.executeQuery();
                  if(resultSet.next()) {
                    if(resultSet.getInt("count")<1) {
                      System.out.println(RED + "The Bus ID : " + busId + " does not exist." + RESET);
                      continue;
                    }
                  }
                  CallableStatement callableStatement2 = connection.prepareCall("DELETE FROM bus_type WHERE bus_id = ?");
                  callableStatement2.setInt(1, busId);
                  try {
                    callableStatement2.execute();
                  } catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println(RED + "The Bus ID : " + busId + " is currently in use and cannot be deleted." + RESET);
                    continue;
                  }
                  System.out.println(GREEN + "Bus ID : " + busId + " successfully deleted.");
                }
                else if(adminDeleteOption==5) {
                  break;
                }
              }

            }
            else if(adminOption==5){
              new BusTypeDistributionChart(connection);
            }
            else if(adminOption==6){
               new BusBookingChart(connection);
            }
            else if(adminOption==7){
              String procedureCall = "{CALL view_admin(?)}";
              CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
              callableStatement2.setString(1, existingUsername);
              callableStatement2.execute();
              ResultSet resultSet= callableStatement2.getResultSet();
              if (!resultSet.next()) {
                System.out.println(RED + "\nSorry, no user details found" + RESET);
              } else {
                System.out.format(WHITE_BOLD + "%n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
                System.out.format(WHITE_BOLD + "%1s\t\t%-10s\t\t%10s\t\t%16s\t\t%15s\t\t%27s%n", "Username", "Role", "First Name", "Last Name", "Email ID", "Phone Number" + RESET);
                System.out.format(WHITE_BOLD + "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);

                System.out.format(WHITE_BOLD + "%2s\t\t%15s\t\t%12s\t\t%15s\t\t%20s\t\t%16s%n" + RESET,
                        resultSet.getString("username"),
                        resultSet.getString("user_role"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_no"));

              }
            }
            else if(adminOption==8){
              System.out.print(WHITE_BOLD +"\nWhat do you wish to edit? Enter the relevant option number from the menu below:" + RESET);
              System.out.print("\n1) First Name ");
              System.out.print("\n2) Last Name");
              System.out.print("\n3) Email ID");
              System.out.print("\n4) Phone Number\n");
              int editOption = validateIntegerInput("Option Number");
              if (editOption == 1)
              {
                System.out.print(WHITE_BOLD + "\nEnter the new First Name: " + RESET);
                String newFn = scanner.nextLine();
                String procedureCall1 = "{CALL update_adminfn(?,?)}";
                CallableStatement callableStatement3 = connection.prepareCall(procedureCall1);
                callableStatement3.setString(1, existingUsername);
                callableStatement3.setString(2, newFn);
                callableStatement3.execute();
                System.out.print(GREEN+ "\nFirst Name successfully updated\n" + RESET);
              }
              if (editOption == 2)
              {
                System.out.print(WHITE_BOLD +"\nEnter the new Last Name: " + RESET);
                String newLn = scanner.nextLine();
                String procedureCall1 = "{CALL update_adminln(?,?)}";
                CallableStatement callableStatement3 = connection.prepareCall(procedureCall1);
                callableStatement3.setString(1, existingUsername);
                callableStatement3.setString(2, newLn);
                callableStatement3.execute();
                System.out.print(GREEN+ "\nLast Name successfully updated\n" + RESET);
              }

              if (editOption == 3)
              {
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern emailPattern = Pattern.compile(emailRegex);
                String newEmail = "";
                do {
                  System.out.println("\nEnter the new Email ID:");
                  newEmail = scanner.nextLine();
                  Matcher emailMatcher = emailPattern.matcher(newEmail);
                  if (!emailMatcher.matches()) {
                    System.out.println(RED+"Invalid email format. Please enter a valid email."+RESET);
                  }
                } while (!emailPattern.matcher(newEmail).matches());
                String procedureCall1 = "{CALL update_admine(?,?)}";
                CallableStatement callableStatement3 = connection.prepareCall(procedureCall1);
                callableStatement3.setString(1, existingUsername);
                callableStatement3.setString(2, newEmail);
                callableStatement3.execute();
                System.out.print(GREEN+"\nEmail ID successfully updated\n"+RESET);
              }

              if (editOption == 4)
              {
                String phoneRegex = "^[0-9]{10}$";
                Pattern phonePattern = Pattern.compile(phoneRegex);
                Long newPn;
                do {
                  System.out.println("\nEnter your phone number");
                  newPn = scanner.nextLong();
                  scanner.nextLine();
                  newPn.toString();
                  Matcher phoneMatcher = phonePattern.matcher(newPn.toString());
                  if (!phoneMatcher.matches()) {
                    System.out.println(RED+"Invalid phone number format. Please enter a 10-digit number."+RESET);
                  }
                } while (!phonePattern.matcher(newPn.toString()).matches());

                String procedureCall1 = "{CALL update_adminpn(?,?)}";
                CallableStatement callableStatement3 = connection.prepareCall(procedureCall1);
                callableStatement3.setString(1, existingUsername);
                callableStatement3.setLong(2, newPn);
                callableStatement3.execute();
                System.out.print(GREEN +"\nPhone number successfully updated" + RESET);
              }
            }
            else if(adminOption==9)
            {
              System.out.println(GREEN+"You have quit the program."+RESET);
              System.exit(0);
            } else
            {

              System.out.println(RED+"Error : Invalid Input. Please try again"+RESET);
            }
          }


        }


      }
      else if (role.equalsIgnoreCase("customer")) {

        while (!customerDbOption) {
          System.out.println(WHITE_BOLD + "\n\nMain Menu: " + RESET);
          System.out.println(WHITE_BOLD + "Please choose one of the following actions by entering the relevant option number: " + RESET);
          System.out.print("1) Create new booking ");
          System.out.print("\n2) Edit existing booking ");
          System.out.print("\n3) View existing booking ");
          System.out.print("\n4) Cancel existing booking");
          System.out.print("\n5) View user information");
          System.out.print("\n6) Edit user information");
          System.out.print("\n7) Quit\n");
          int customerOption = Integer.parseInt(scanner.nextLine());

          if (customerOption == 1) {
            System.out.print(WHITE_BOLD + "\n How do you want to make your booking? Choose the relevant option number: " + RESET);
            System.out.print("\n1) View available trip routes ");
            System.out.print("\n2) Enter the source and destination cities\n");
            int bookingOption = Integer.parseInt(scanner.nextLine());

            if (bookingOption == 1) {
              ResultSet resultSet = statement.executeQuery("SELECT * FROM bus_route");
              int count = 1;
              System.out.format(WHITE_BOLD + "%n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
              System.out.format(WHITE_BOLD + "%1s\t\t%-10s\t\t%10s\t\t%20s\t\t%-10s\t\t%22s\t\t%23s%n", "Route ID", "Origin city name", "Destination city name", "Total Fare", "Duration", "Start Date and Time", "End Date and Time" + RESET);
              System.out.format(WHITE_BOLD + "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
              while (resultSet.next()) {
                System.out.format(WHITE_BOLD + "%2s\t\t%15s\t\t%23s\t\t%29s\t\t%10s\t\t%23s\t\t%18s%n" + RESET,
                        resultSet.getInt("route_id"),
                        resultSet.getString("origin_city_name"),
                        resultSet.getString("destination_city_name"),
                        resultSet.getString("total_fare"),
                        resultSet.getString("duration"),
                        resultSet.getTimestamp("start_date_time"),
                        resultSet.getTimestamp("end_date_time"));

              }
              System.out.print(WHITE_BOLD + "\nDo you wish to book your journey for any of these available trip routes? \nIf yes, enter the relevant route ID. If no, enter 'No': " + RESET);
              String routeId = scanner.nextLine();
              if (routeId.equalsIgnoreCase("no")) {
                break;
              } else {
                int route_id = Integer.parseInt(routeId);
                int total_fare = 0;
                String query = "SELECT total_fare FROM bus_route WHERE route_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, route_id);
                ResultSet resultSet2 = preparedStatement.executeQuery();
                if (resultSet2.next()) {
                  total_fare = resultSet2.getInt("total_fare");
                  System.out.println(WHITE_BOLD + "\nYour total fare is: " + RESET + total_fare);
                } else {
                  System.out.println(RED + "\nIncorrect route id provided." + RESET);
                  break;
                }

                System.out.print(WHITE_BOLD + "\nChoose your payment method: Credit Card, Debit Card, Online Wallet\n" + RESET);
                String paymentMethod = scanner.nextLine();

                System.out.print(WHITE_BOLD + "\nHow many seats do you want to book?\n" + RESET);
                int seats = Integer.parseInt(scanner.nextLine());

                String procedureCall = "{CALL create_booking(?,?,?,?,?)}";
                CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
                callableStatement2.setInt(1, route_id);
                callableStatement2.setInt(2, total_fare);
                callableStatement2.setString(3, paymentMethod);
                callableStatement2.setInt(4, seats);
                callableStatement2.setString(5, existingUsername);
                callableStatement2.execute();
                System.out.print(GREEN + "\nBooking successfully created\n" + RESET);
              }
            }

            if (bookingOption == 2) {
              System.out.print(WHITE_BOLD + "Enter your starting city\n" + RESET);
              String startingCity = scanner.nextLine();
              System.out.print(WHITE_BOLD + "\nEnter your destination city\n" + RESET);
              String destinationCity = scanner.nextLine();

              String query = "SELECT * FROM bus_route WHERE origin_city_name = ? AND destination_city_name = ?";
              PreparedStatement preparedStatement = connection.prepareStatement(query);
              preparedStatement.setString(1, startingCity);
              preparedStatement.setString(2, destinationCity);
              ResultSet resultSet3 = preparedStatement.executeQuery();

              if (!resultSet3.next()) {
                System.out.println(RED + "\nSorry, we don't have any buses on this route currently" + RESET);
              } else {
                System.out.format(WHITE_BOLD + "%n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
                System.out.format(WHITE_BOLD + "%1s\t\t%-10s\t\t%10s\t\t%20s\t\t%-10s\t\t%22s\t\t%23s%n", "Route ID", "Origin city name", "Destination city name", "Total Fare", "Duration", "Start Date and Time", "End Date and Time" + RESET);
                System.out.format(WHITE_BOLD + "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);

                do {
                  System.out.format(WHITE_BOLD + "%2s\t\t%15s\t\t%23s\t\t%29s\t\t%10s\t\t%23s\t\t%18s%n" + RESET,
                          resultSet3.getInt("route_id"),
                          resultSet3.getString("origin_city_name"),
                          resultSet3.getString("destination_city_name"),
                          resultSet3.getString("total_fare"),
                          resultSet3.getString("duration"),
                          resultSet3.getTimestamp("start_date_time"),
                          resultSet3.getTimestamp("end_date_time"));
                } while (resultSet3.next());

                System.out.print(WHITE_BOLD + "\nDo you wish to book your journey for this trip routes? If yes, enter the route ID. If no, enter 'No': \n" + RESET);
                String routeId = scanner.nextLine();
                if (routeId.equalsIgnoreCase("no")) {
                  break;
                } else {
                  int route_id = Integer.parseInt(routeId);

                  int total_fare = 0;
                  String query1 = "SELECT total_fare FROM bus_route WHERE route_id = ?";
                  PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                  preparedStatement1.setInt(1, route_id);
                  ResultSet resultSet2 = preparedStatement1.executeQuery();

                  if (resultSet2.next()) {
                    total_fare = resultSet2.getInt("total_fare");
                    System.out.println(WHITE_BOLD + "\nYour total fare is: " + RESET + total_fare);
                  } else {
                    System.out.println(RED + "\nIncorrect route id provided." + RESET);
                    break;
                  }

                  System.out.print(WHITE_BOLD + "\nChoose your payment method: Credit Card, Debit Card, Online Wallet\n" + RESET);
                  String paymentMethod = scanner.nextLine();

                  System.out.print(WHITE_BOLD + "\nHow many seats do you want to book?\n" + RESET);
                  int seats = Integer.parseInt(scanner.nextLine());

                  String procedureCall = "{CALL create_booking(?,?,?,?,?)}";
                  CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
                  callableStatement2.setInt(1, route_id);
                  callableStatement2.setInt(2, total_fare);
                  callableStatement2.setString(3, paymentMethod);
                  callableStatement2.setInt(4, seats);
                  callableStatement2.setString(5, existingUsername);
                  callableStatement2.execute();
                  System.out.print(GREEN + "\nBooking successfully created" + RESET);
                }
              }
            }
          }
          else if (customerOption == 2) {
            String query = "SELECT booking_id FROM bus_journey_booking WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, existingUsername);
            ResultSet resultSet3 = preparedStatement.executeQuery();
            if (!resultSet3.next()) {
              System.out.println(RED + "\n You don't have any existing bookings. Please create a booking first" + RESET);
            } else {
              System.out.println(WHITE_BOLD + "\nThis is your existing booking ID(s): " + RESET);
              List bid = new ArrayList<Integer>();
              do {
                int id = resultSet3.getInt("booking_id");
                bid.add(id);
                System.out.format("%2s%n",
                        resultSet3.getInt("booking_id"));
              } while (resultSet3.next());
              System.out.println(WHITE_BOLD + "\nEnter the booking id you wish to edit :" + RESET);
              int booking_id = Integer.parseInt(scanner.nextLine());
              if (!bid.contains(booking_id)) {
                System.out.println(RED + "\nSorry, this is not your booking ID" + RESET);
              } else {
                String procedureCall5 = "{CALL check_booking_exists(?)}";
                CallableStatement callableStatement5 = connection.prepareCall(procedureCall5);
                callableStatement5.setInt(1, booking_id);
                callableStatement5.execute();
                ResultSet resultSet5 = callableStatement5.getResultSet();
                if (!resultSet5.next()) {
                  System.out.println(RED + "\nSorry, this is an incorrect booking ID" + RESET);
                } else {
                  System.out.print(WHITE_BOLD + "\nWhat do you wish to edit? Enter the relevant option number from the menu below:" + RESET);
                  System.out.print("\n1) Number of seats booked ");
                  System.out.print("\n2) Trip route cities\n");
                  int editOption = Integer.parseInt(scanner.nextLine());
                  if (editOption == 1) {
                    System.out.print(WHITE_BOLD + "\nEnter the new number of seats: " + RESET);
                    int newSeats = Integer.parseInt(scanner.nextLine());
                    String procedureCall1 = "{CALL update_seats(?,?)}";
                    CallableStatement callableStatement3 = connection.prepareCall(procedureCall1);
                    callableStatement3.setInt(1, booking_id);
                    callableStatement3.setInt(2, newSeats);
                    callableStatement3.execute();
                    System.out.print(GREEN + "\nNumber of seats successfully updated" + RESET);
                  }
                  if (editOption == 2) {
                    System.out.print(WHITE_BOLD + "\nHow do you want to edit your trip route? Choose the relevant option number: " + RESET);
                    System.out.print("\n1) View available trip routes ");
                    System.out.print("\n2) Enter the source and destination cities\n");
                    int bookingOption = Integer.parseInt(scanner.nextLine());
                    if (bookingOption == 1) {
                      ResultSet resultSet = statement.executeQuery("SELECT * FROM bus_route");
                      System.out.format(WHITE_BOLD + "%n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
                      System.out.format(WHITE_BOLD + "%1s\t\t%-10s\t\t%10s\t\t%20s\t\t%-10s\t\t%22s\t\t%23s%n", "Route ID", "Origin city name", "Destination city name", "Total Fare", "Duration", "Start Date and Time", "End Date and Time" + RESET);
                      System.out.format(WHITE_BOLD + "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);

                      while (resultSet.next()) {
                        System.out.format(WHITE_BOLD + "%2s\t\t%15s\t\t%23s\t\t%29s\t\t%10s\t\t%23s\t\t%18s%n" + RESET,
                                resultSet.getInt("route_id"),
                                resultSet.getString("origin_city_name"),
                                resultSet.getString("destination_city_name"),
                                resultSet.getString("total_fare"),
                                resultSet.getString("duration"),
                                resultSet.getTimestamp("start_date_time"),
                                resultSet.getTimestamp("end_date_time"));
                      }
                      System.out.print(WHITE_BOLD + "\nDo you wish to update your bus journey route to any of these existing trip routes? \nIf yes, enter the relevant route ID. If no, enter 'No': " + RESET);
                      String routeId = scanner.nextLine();
                      if (routeId.equalsIgnoreCase("no")) {
                        break;
                      } else {
                        int route_id = Integer.parseInt(routeId);
                        int total_fare = 0;
                        String query7 = "SELECT total_fare FROM bus_route WHERE route_id = ?";
                        PreparedStatement preparedStatement7 = connection.prepareStatement(query7);
                        preparedStatement7.setInt(1, route_id);
                        ResultSet resultSet2 = preparedStatement7.executeQuery();
                        if (resultSet2.next()) {
                          total_fare = resultSet2.getInt("total_fare");
                          System.out.println(WHITE_BOLD + "\nYour total fare is: " + RESET + total_fare);
                        } else {
                          System.out.println(RED + "\nIncorrect route id provided." + RESET);
                          break;
                        }

                        String procedureCall = "{CALL edit_route(?, ?, ?)}";
                        CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
                        callableStatement2.setInt(1, booking_id);
                        callableStatement2.setInt(2, route_id);
                        callableStatement2.setInt(3, total_fare);
                        callableStatement2.execute();
                        System.out.print(GREEN + "\nBooking successfully updated\n" + RESET);
                      }
                    }

                    if (bookingOption == 2) {
                      System.out.print(WHITE_BOLD + "\nEnter your starting city\n" + RESET);
                      String startingCity = scanner.nextLine();
                      System.out.print(WHITE_BOLD + "\nEnter your destination city\n" + RESET);
                      String destinationCity = scanner.nextLine();

                      String query8 = "SELECT * FROM bus_route WHERE origin_city_name = ? AND destination_city_name = ?";
                      PreparedStatement preparedStatement8 = connection.prepareStatement(query8);
                      preparedStatement8.setString(1, startingCity);
                      preparedStatement8.setString(2, destinationCity);
                      ResultSet resultSet33 = preparedStatement8.executeQuery();

                      if (!resultSet33.next()) {
                        System.out.println(RED + "\nSorry, we don't have any buses on this route currently" + RESET);
                      } else {
                        System.out.format(WHITE_BOLD + "%n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
                        System.out.format(WHITE_BOLD + "%1s\t\t%-10s\t\t%10s\t\t%20s\t\t%-10s\t\t%22s\t\t%23s%n", "Route ID", "Origin city name", "Destination city name", "Total Fare", "Duration", "Start Date and Time", "End Date and Time" + RESET);
                        System.out.format(WHITE_BOLD + "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
                        do {
                          System.out.format(WHITE_BOLD + "%2s\t\t%15s\t\t%23s\t\t%29s\t\t%10s\t\t%23s\t\t%18s%n" + RESET,
                                  resultSet33.getInt("route_id"),
                                  resultSet33.getString("origin_city_name"),
                                  resultSet33.getString("destination_city_name"),
                                  resultSet33.getString("total_fare"),
                                  resultSet33.getString("duration"),
                                  resultSet33.getTimestamp("start_date_time"),
                                  resultSet33.getTimestamp("end_date_time"));
                        } while (resultSet33.next());

                        System.out.print(WHITE_BOLD + "\nDo you wish to update your journey to this trip route? If yes, enter the route ID. If no, enter 'No': " + RESET);
                        String routeId = scanner.nextLine();
                        if (routeId.equalsIgnoreCase("no")) {
                          break;
                        } else {
                          int route_id = Integer.parseInt(routeId);

                          int total_fare = 0;
                          String query1 = "SELECT total_fare FROM bus_route WHERE route_id = ?";
                          PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                          preparedStatement1.setInt(1, route_id);
                          ResultSet resultSet2 = preparedStatement1.executeQuery();

                          if (resultSet2.next()) {
                            total_fare = resultSet2.getInt("total_fare");
                            System.out.println(WHITE_BOLD + "\nYour total fare is: " + RESET + total_fare);
                          } else {
                            System.out.println(RED + "\nIncorrect route id provided." + RESET);
                            break;
                          }

                          String procedureCall = "{CALL edit_route(?, ?, ?)}";
                          CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
                          callableStatement2.setInt(1, booking_id);
                          callableStatement2.setInt(2, route_id);
                          callableStatement2.setInt(3, total_fare);
                          callableStatement2.execute();
                          System.out.print(GREEN + "\nBooking successfully updated\n" + RESET);
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          else if (customerOption == 3) {
            String query = "SELECT booking_id FROM bus_journey_booking WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, existingUsername);
            ResultSet resultSet3 = preparedStatement.executeQuery();
            if (!resultSet3.next()) {
              System.out.println(RED + "\n You don't have any existing bookings. Please create a booking first\n" + RESET);
            } else {
              System.out.println(WHITE_BOLD + "\nThis is your existing booking ID(s): " + RESET);
              List bid = new ArrayList<Integer>();
              do {
                int id = resultSet3.getInt("booking_id");
                bid.add(id);
                System.out.format("%2s%n",
                        resultSet3.getInt("booking_id"));
              } while (resultSet3.next());
              System.out.println(WHITE_BOLD + "\nEnter the booking id you wish to edit :" + RESET);
              int booking_id = Integer.parseInt(scanner.nextLine());
              if (!bid.contains(booking_id)) {
                System.out.println(RED + "\nSorry, this is not your booking ID" + RESET);
              } else {
                String procedureCall = "{CALL view_booking(?)}";
                CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
                callableStatement2.setInt(1, booking_id);
                callableStatement2.execute();
                ResultSet resultSet = callableStatement2.getResultSet();
                if (!resultSet.next()) {
                  System.out.println(RED + "\nSorry, this is an incorrect booking ID" + RESET);
                } else {
                  System.out.format(WHITE_BOLD + "%n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
                  System.out.format(WHITE_BOLD + "%1s\t\t%-10s\t\t%10s\t\t%15s\t\t%-10s\t\t%22s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s%n", "Booking ID", "Origin city", "Destination city", "Amount Paid", "Duration", "Board Time", "Arrival Time", "Seats Booked", "Payment Method", "Bus Type", "Bus Capacity", "Bus Model", "Bus Color", "Driver's First Name", "Driver's Last Name" + RESET);
                  System.out.format(WHITE_BOLD + "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
                  do {
                    System.out.format(WHITE_BOLD + "%1s\t\t%15s\t\t%15s\t\t%18s\t\t%9s\t\t%26s\t\t%26s\t\t%18s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%23s\t\t%20s" + RESET,
                            resultSet.getString("booking_id"),
                            resultSet.getString("origin_city_name"),
                            resultSet.getString("destination_city_name"),
                            resultSet.getString("amount_paid"),
                            resultSet.getString("duration"),
                            resultSet.getString("start_date_time"),
                            resultSet.getString("end_date_time"),
                            resultSet.getString("seats_booked"),
                            resultSet.getString("payment_method"),
                            resultSet.getString("type"),
                            resultSet.getString("capacity"),
                            resultSet.getString("model"),
                            resultSet.getString("color"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"));
                  } while (resultSet.next());
                }
              }
            }
          }
          else if (customerOption == 4) {
            String query = "SELECT booking_id FROM bus_journey_booking WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, existingUsername);
            ResultSet resultSet3 = preparedStatement.executeQuery();
            if (!resultSet3.next()) {
              System.out.println(RED + "\n You don't have any existing bookings. Please create a booking first\n" + RESET);
            } else {
              System.out.println(WHITE_BOLD + "\nThis is your existing booking ID(s): " + RESET);
              List bid = new ArrayList<Integer>();
              do {
                int id = resultSet3.getInt("booking_id");
                bid.add(id);
                System.out.format("%2s%n",
                        resultSet3.getInt("booking_id"));
              } while (resultSet3.next());
              System.out.println(WHITE_BOLD + "\nEnter the booking id you wish to delete :" + RESET);
              int booking_id = Integer.parseInt(scanner.nextLine());
              if (!bid.contains(booking_id)) {
                System.out.println(RED + "\nSorry, this is not your booking ID" + RESET);
              } else {
                String procedureCall = "{CALL view_booking(?)}";
                CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
                callableStatement2.setInt(1, booking_id);
                callableStatement2.execute();
                ResultSet resultSet = callableStatement2.getResultSet();
                if (!resultSet.next()) {
                  System.out.println(RED + "\nSorry, this is an incorrect booking ID" + RESET);
                } else {
                  String procedureCall1 = "{CALL delete_booking(?)}";
                  CallableStatement callableStatement3 = connection.prepareCall(procedureCall1);
                  callableStatement3.setInt(1, booking_id);
                  callableStatement3.execute();
                  System.out.print(GREEN + "\nBooking successfully deleted\n" + RESET);
                }
              }
            }
          }
          else if (customerOption == 5){
            String procedureCall = "{CALL view_user(?)}";
            CallableStatement callableStatement2 = connection.prepareCall(procedureCall);
            callableStatement2.setString(1, existingUsername);
            callableStatement2.execute();
            ResultSet resultSet= callableStatement2.getResultSet();
            if (!resultSet.next()) {
              System.out.println(RED + "\nSorry, no user details found" + RESET);
            } else {
              System.out.format(WHITE_BOLD + "%n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);
              System.out.format(WHITE_BOLD + "%1s\t\t%-10s\t\t%10s\t\t%16s\t\t%15s\t\t%27s%n", "Username", "Role", "First Name", "Last Name", "Email ID", "Phone Number" + RESET);
              System.out.format(WHITE_BOLD + "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n" + RESET);

                System.out.format(WHITE_BOLD + "%2s\t\t%15s\t\t%12s\t\t%15s\t\t%20s\t\t%16s%n" + RESET,
                resultSet.getString("username"),
                resultSet.getString("user_role"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone_no"));

            }
          }
          else if (customerOption == 6){
            System.out.print(WHITE_BOLD +"\nWhat do you wish to edit? Enter the relevant option number from the menu below:" + RESET);
            System.out.print("\n1) First Name ");
            System.out.print("\n2) Last Name");
            System.out.print("\n3) Email ID");
            System.out.print("\n4) Phone Number\n");
            int editOption = validateIntegerInput("Option Number");
            if (editOption == 1)
            {
              System.out.print(WHITE_BOLD + "\nEnter the new First Name: " + RESET);
              String newFn = scanner.nextLine();
              String procedureCall1 = "{CALL update_fn(?,?)}";
              CallableStatement callableStatement3 = connection.prepareCall(procedureCall1);
              callableStatement3.setString(1, existingUsername);
              callableStatement3.setString(2, newFn);
              callableStatement3.execute();
              System.out.print(GREEN+ "\nFirst Name successfully updated\n" + RESET);
            }
            if (editOption == 2)
            {
              System.out.print(WHITE_BOLD +"\nEnter the new Last Name: " + RESET);
              String newLn = scanner.nextLine();
              String procedureCall1 = "{CALL update_ln(?,?)}";
              CallableStatement callableStatement3 = connection.prepareCall(procedureCall1);
              callableStatement3.setString(1, existingUsername);
              callableStatement3.setString(2, newLn);
              callableStatement3.execute();
              System.out.print(GREEN+ "\nLast Name successfully updated\n" + RESET);
            }

            if (editOption == 3)
            {
              String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
              Pattern emailPattern = Pattern.compile(emailRegex);
              String newEmail = "";
              do {
                System.out.println("\nEnter the new Email ID:");
                newEmail = scanner.nextLine();
                Matcher emailMatcher = emailPattern.matcher(newEmail);
                if (!emailMatcher.matches()) {
                  System.out.println(RED+"Invalid email format. Please enter a valid email."+RESET);
                }
              } while (!emailPattern.matcher(newEmail).matches());
              String procedureCall1 = "{CALL update_e(?,?)}";
              CallableStatement callableStatement3 = connection.prepareCall(procedureCall1);
              callableStatement3.setString(1, existingUsername);
              callableStatement3.setString(2, newEmail);
              callableStatement3.execute();
              System.out.print(GREEN+"\nEmail ID successfully updated\n"+RESET);
            }

            if (editOption == 4)
            {
              String phoneRegex = "^[0-9]{10}$";
              Pattern phonePattern = Pattern.compile(phoneRegex);
              Long newPn;
              do {
                System.out.println("\nEnter your phone number");
                newPn = scanner.nextLong();
                scanner.nextLine();
                newPn.toString();
                Matcher phoneMatcher = phonePattern.matcher(newPn.toString());
                if (!phoneMatcher.matches()) {
                  System.out.println(RED+"Invalid phone number format. Please enter a 10-digit number."+RESET);
                }
              } while (!phonePattern.matcher(newPn.toString()).matches());

              String procedureCall1 = "{CALL update_pn(?,?)}";
              CallableStatement callableStatement3 = connection.prepareCall(procedureCall1);
              callableStatement3.setString(1, existingUsername);
              callableStatement3.setLong(2, newPn);
              callableStatement3.execute();
              System.out.print(GREEN +"\nPhone number successfully updated" + RESET);
            }

          }
          else if(customerOption==7)
          {
            System.out.println(GREEN+"\nYou have quit the program."+RESET);
            System.exit(0);
          } else
          {
            System.out.println(RED+"\nError : Invalid Input. Please try again"+RESET);
          }
        }
      }
    }
  private static boolean containsDigits(String s) {
    return Pattern.compile("\\d").matcher(s).find();
  }
  private static String validateStringInput(String name) {
    Scanner scanner = new Scanner(System.in);

    String input;
    do {
      System.out.print(name+" : ");
      input = scanner.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println(RED + "Input cannot be empty. Please try again." + RESET);
      }
    } while (input.isEmpty());
    return input;
  }

  private static Timestamp validateTimestampInput(String fieldName) {
    Scanner scanner = new Scanner(System.in);

    Timestamp timestamp;
    do {
      try {
        System.out.print(fieldName + " : ");
        timestamp = Timestamp.valueOf(scanner.nextLine());
        return timestamp;
      } catch (IllegalArgumentException e) {
        System.out.println(RED + "Invalid " + fieldName + ". Format must be yyyy-mm-dd hh:mm:ss" + RESET);
      }
    } while (true);
  }

  private static String validateBusType(String fieldName) {
    Scanner scanner = new Scanner(System.in);

    String bus_type;
    do {

        System.out.print(fieldName + " : ");
        bus_type = scanner.nextLine();
        if(!bus_type.equalsIgnoreCase("Sleeper") && !bus_type.equalsIgnoreCase("Seater")
        && !bus_type.equalsIgnoreCase("Double Decker") && !bus_type.equalsIgnoreCase("shuttle") ) {
          System.out.println(RED + "Invalid " + fieldName + ". Type can be : sleeper, seater, Double Decker, Shuttle" + RESET);
          continue;
        }
        return bus_type;


    } while (true);
  }
  private static int validateIntegerInput(String fieldName) {
    Scanner scanner = new Scanner(System.in);

    int intValue;
    do {
      try {
        System.out.print(fieldName + " : ");
        intValue = scanner.nextInt();
        return intValue;
      } catch (InputMismatchException e) {
        System.out.println(RED + "Invalid " + fieldName + ". Please enter a valid integer." + RESET);
        scanner.nextLine();
      }
    } while (true);
  }

  private static String validateStringInputForEdit(String text) {
    Scanner scanner = new Scanner(System.in);

    String input;
    do {
      input = scanner.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println(RED + "Input cannot be empty. Please try again." + RESET);
        System.out.print(WHITE_BOLD+text+RESET);
      }
    } while (input.isEmpty());
    return input;
  }
  private static boolean containsDigitsOrSpecialChars(String inputString) {
    Pattern pattern = Pattern.compile("[0-9!@#$%^&*()_+{}\\[\\]:;<>,.?~\\\\/-]");
    Matcher matcher = pattern.matcher(inputString);
    return matcher.find();
  }

  private static int validateIntegerInputForEdit(String fieldName) {
    Scanner scanner = new Scanner(System.in);

    int intValue;
    do {
      try {
        intValue = scanner.nextInt();
        return intValue;
      } catch (InputMismatchException e) {
        System.out.println(RED + "Invalid " + fieldName + ". Please enter a valid integer." + RESET);
        System.out.print(WHITE_BOLD + "\nEnter the new " + fieldName + " : " + RESET);
        scanner.nextLine();
      }
    } while (true);
  }

  private static Timestamp validateTimestampInputForEdit(String fieldName) {
    Scanner scanner = new Scanner(System.in);

    Timestamp timestamp;
    do {
      try {
        timestamp = Timestamp.valueOf(scanner.nextLine());
        return timestamp;
      } catch (IllegalArgumentException e) {
        System.out.println(RED + "Invalid " + fieldName + ". Format must be yyyy-mm-dd hh:mm:ss" +RESET);
        System.out.print(WHITE_BOLD+"\nEnter the "+fieldName+" : "+RESET);
      }
    } while (true);
  }

  }


