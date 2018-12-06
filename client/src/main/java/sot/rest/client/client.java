package sot.rest.client;

import org.glassfish.jersey.client.ClientConfig;
import sot.rest.client.model.room;
import sot.rest.client.model.user;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer menuUtama = 0;
        System.out.println("[NOTICE]For Burp, Client is using port 8088 ");
        System.out.println("To Login, use username = \"user\" and password = \"user\" ");

        while (login()) {
            menuUtama = 0;
            while (menuUtama != 3) {

                System.out.println(" ##Menu##");
                System.out.println(" 1. Room ");
                System.out.println(" 2. User ");
                System.out.println(" 3. Out");
                System.out.println("Choose Menu");
                menuUtama = scanner.nextInt();
                if (menuUtama == 1) {
                    Integer menuDalam = 0;
                    while (menuDalam != 8) {
                        System.out.println("%%Menu%%");
                        System.out.println("1. Delete House ");
                        System.out.println("2. Add new House ");
                        System.out.println("3. Reserve the House ");
                        System.out.println("4. View Available Room ");
                        System.out.println("5. View All Room ");
                        System.out.println("6. Update House Detail");
                        System.out.println("7. Get Room by ID");
                        System.out.println("8. Back to Main");
                        System.out.println("Choose Menu");
                        menuDalam = scanner.nextInt();

                        if (menuDalam == 1) {
                            deleteRoom();
                        } else if (menuDalam == 2) {
                            addNewHome();
                        } else if (menuDalam == 3) {
                            reserveRoom();
                        } else if (menuDalam == 4) {
                            viewAvailRoom();
                        } else if (menuDalam == 5) {
                            getAllRoom();
                        } else if (menuDalam == 6) {
                            updateHome();
                        } else  if(menuDalam == 7) {
                            getRoomByID();
                        }
                    }
                } else if (menuUtama == 2) {
                    Integer menuDalam = 0;
                    while (menuDalam != 6) {
                        System.out.println("%%Menu%%");
                        System.out.println("1. Delete User ");
                        System.out.println("2. Add User ");
                        System.out.println("3. Update User ");
                        System.out.println("4. Get User by Username ");
                        System.out.println("5. Get All User");
                        System.out.println("6. Back to Main");
                        System.out.println("Choose Menu");
                        menuDalam = scanner.nextInt();

                        if (menuDalam == 1) {
                            deleteUser();
                        } else if (menuDalam == 2) {
                            addUser();
                        } else if (menuDalam == 3) {
                            updateUser();
                        } else if (menuDalam == 4) {
                            getUserbyUsername();
                        } else if (menuDalam == 5) {
                            getAllUser();
                        }
                    }
                }
            }
        }

    }

    public static void getRoomByID() {
        URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/room").build();
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget serviceTarget = client.target(baseUri);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert ID : ");
        Integer id = scanner.nextInt();

        Invocation.Builder requestBulider = serviceTarget.path(id.toString()).request().accept(MediaType.APPLICATION_JSON);
        Response response = requestBulider.get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            room entity = response.readEntity(room.class);
            System.out.println("House on " + entity.getAddress() +" "+entity.getNumber()+" is "+ entity.getSize()+" m3 and priced "+ entity.getPrice());

        } else {
            System.out.println("User not Found!");
        }
    }

    public static void deleteRoom() {
         URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/room").build();
         Client client = ClientBuilder.newClient(new ClientConfig());
         WebTarget serviceTarget = client.target(baseUri);

         Scanner scanner = new Scanner(System.in);
         System.out.println("Insert ID : ");
         Integer id = scanner.nextInt();

         Invocation.Builder requestBuilder = serviceTarget.path(id.toString()).request().accept(MediaType.TEXT_PLAIN);
         Response response = requestBuilder.delete();

         if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
             System.out.println("House Deleted!");
         } else {
             System.out.println("House doen't exist!");
         }
     }
     public static void updateHome() {
         URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/room").build();
         Client client = ClientBuilder.newClient(new ClientConfig());
         WebTarget serviceTarget = client.target(baseUri);

         Scanner scanner = new Scanner(System.in);
         System.out.println("Insert ID : ");
         Integer id = scanner.nextInt();
         scanner.nextLine();
         System.out.println("Insert Address :");
         String address = scanner.nextLine();
         System.out.println("Insert House Number : ");
         String number = scanner.nextLine();
         System.out.println("Insert Price : ");
         Integer price = scanner.nextInt();
         System.out.println("Insert Size : ");
         Integer size = scanner.nextInt();



         room Room = new room(id, address, number, price, size,0);
         Entity<room> entity = Entity.entity(Room, MediaType.APPLICATION_JSON);

         Invocation.Builder requestBuilder = serviceTarget.path("update").request().accept(MediaType.TEXT_PLAIN);
         Response response = requestBuilder.put(entity);

         if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode())
             System.out.println("House Updated!");
         else
             System.out.println(response);
     }
     public static void addNewHome() {
         URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/room").build();
         Client client = ClientBuilder.newClient(new ClientConfig());
         WebTarget serviceTarget = client.target(baseUri);

         Scanner scanner = new Scanner(System.in);
         System.out.println("Insert ID : ");
         Integer id = scanner.nextInt();
         scanner.nextLine();
         System.out.println("Insert Address :");
         String address = scanner.nextLine();
         System.out.println("Insert House Number : ");
         String number = scanner.nextLine();
         System.out.println("Insert Price : ");
         Integer price = scanner.nextInt();
         System.out.println("Insert Size : ");
         Integer size = scanner.nextInt();

         room Room = new room(id, address, number, price, size,0);
         Entity<room> entity = Entity.entity(Room, MediaType.APPLICATION_JSON);

         Invocation.Builder requestBuilder = serviceTarget.request().accept(MediaType.TEXT_PLAIN);
         Response response = requestBuilder.post(entity);

         if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
             System.out.println("Room Added!");
         } else {
             System.out.println(response);
         }
     }

     public static void reserveRoom() {
         URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/room").build();
         Client client = ClientBuilder.newClient(new ClientConfig());
         WebTarget serviceTarget = client.target(baseUri);

         Scanner scanner = new Scanner(System.in);
         System.out.println("Insert ID : ");
         Integer id = scanner.nextInt();

         Form form = new Form();
         form.param("id",Integer.toString(id));
         form.param("status",Integer.toString(1));
         Entity<Form> entity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED);

         Invocation.Builder requestBuilder = serviceTarget.path("reserve").request().accept(MediaType.TEXT_PLAIN);
         Response response = requestBuilder.put(entity);

         if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
             System.out.println("Reserved!");
         } else {
               System.out.println("Room is not available");
         }
     }
     public static void viewAvailRoom() {
         URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/room").build();
         Client client = ClientBuilder.newClient(new ClientConfig());
         WebTarget serviceTarget = client.target(baseUri);

         Invocation.Builder requestBulider = serviceTarget.queryParam("status", "0").request().accept(MediaType.APPLICATION_JSON);
         Response response = requestBulider.get();

         if (response.getStatus() == Response.Status.OK.getStatusCode()) {
             GenericType<ArrayList<room>> genericType = new GenericType<>() {
             };
             ArrayList<room> entity2 = response.readEntity(genericType);
             System.out.println("Available room are ");
             System.out.println("Address   |   Number   |   Price   |   Size   ");
             for (room rooms : entity2) {
                 System.out.println(rooms.getAddress()+"    |    "+rooms.getNumber()+"    |    "+rooms.getPrice()+"    |    "+rooms.getSize());
             }
         } else {
             System.out.println("Room Error!");
         }
     }

     public static void getAllRoom(){
         URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/room").build();
         Client client = ClientBuilder.newClient(new ClientConfig());
         WebTarget serviceTarget = client.target(baseUri);

        Invocation.Builder requestBuilder = serviceTarget.path("all").request().accept(MediaType.APPLICATION_JSON);
        Response response = requestBuilder.get();

        if(response.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<ArrayList<room>> genericTypeAll = new GenericType<>() {};
            ArrayList<room> entity2 = response.readEntity(genericTypeAll);
            System.out.println("Address   |   Number   |   Price   |   Size   ");
            for (room rooms : entity2){
                System.out.println(rooms.getAddress()+"    |    "+rooms.getNumber()+"    |    "+rooms.getPrice()+"    |    "+rooms.getSize());
            }
        } else {
            System.out.println("Room all Error!");
        }

    }

    public static void updateUser(){
        URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/user").build();
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget serviceTarget = client.target(baseUri);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert Name :");
        String name = scanner.nextLine();
        System.out.println("Insert Username : ");
        String username = scanner.nextLine();
        System.out.println("Insert Password : ");
        String password = scanner.nextLine();


        user User = new user(name, username, password, 0);
        Entity<user> entity = Entity.entity(User, MediaType.APPLICATION_JSON);

        Invocation.Builder requestBuilder = serviceTarget.path("update").request().accept(MediaType.TEXT_PLAIN);
        Response response = requestBuilder.put(entity);

        if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode())
            System.out.println("User Updated!");
        else
            System.out.println(response);
    }
    public static void deleteUser() {
        URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/user").build();
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget serviceTarget = client.target(baseUri);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert ID : ");
        Integer id = scanner.nextInt();

        Invocation.Builder requestBuilder = serviceTarget.path(id.toString()).request().accept(MediaType.TEXT_PLAIN);
        Response response = requestBuilder.delete();

        if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
            System.out.println("User Deleted!");
        } else {
            System.out.println(response);
        }
    }

    public static void getUserbyUsername(){
        URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/user").build();
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget serviceTarget = client.target(baseUri);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert Username : ");
        String uname = scanner.nextLine();


        Invocation.Builder requestBulider = serviceTarget.path(uname).request().accept(MediaType.APPLICATION_JSON);
        Response response = requestBulider.get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            user entity = response.readEntity(user.class);
            System.out.println("Name under the username " + entity.getUsername() + " is " + entity.getName());
        } else {
            System.out.println("User not Found!");
        }
    }

    public static void getAllUser(){
        URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/user").build();
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget serviceTarget = client.target(baseUri);

        Invocation.Builder requestBuilder = serviceTarget.path("all").request().accept(MediaType.APPLICATION_JSON);
        Response response = requestBuilder.get();

        if(response.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<ArrayList<user>> genericTypeAll = new GenericType<>() {};
            ArrayList<user> entity = response.readEntity(genericTypeAll);
            System.out.println("Name    |    Username    |    Password");
            for (user users : entity){
                System.out.println(users.getName() +"    |     "+ users.getUsername() +"    |    "+users.getPassword() );
            }
        } else {
            System.out.println("Cannot Retrieve Data!");
        }
    }

    public static void addUser(){
        URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/user").build();
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget serviceTarget = client.target(baseUri);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert Name :");
        String name = scanner.nextLine();
        System.out.println("Insert Username : ");
        String username = scanner.nextLine();
        System.out.println("Insert Passwor : ");
        String password = scanner.nextLine();


        user User = new user(name, username, password, 0);
        Entity<user> entity = Entity.entity(User, MediaType.APPLICATION_JSON);

        Invocation.Builder requestBuilder = serviceTarget.request().accept(MediaType.TEXT_PLAIN);
        Response response = requestBuilder.post(entity);

        if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
            System.out.println("User Added!");
        } else {
            System.out.println(response);
        }
    }
    public static boolean login(){
        URI baseUri = UriBuilder.fromUri("http://localhost:8088/rental/rest/user").build();
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget serviceTarget = client.target(baseUri);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert Username : ");
        String username = scanner.nextLine();
        System.out.println("Insert Password : ");
        String password = scanner.nextLine();


        user User = new user("",username, password, 0);
        Entity<user> entity = Entity.entity(User, MediaType.APPLICATION_JSON);

        Invocation.Builder requestBuilder = serviceTarget.path("login").queryParam("username",username).queryParam("password", password).request().accept(MediaType.TEXT_PLAIN);
        Response response = requestBuilder.post(entity);

        Integer check;

        if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode())
            check = 0;
        else {
            System.out.println(response);
            check = 1;
        }

        if (check==0)
            return true;
        else
            return false;
    }
}
