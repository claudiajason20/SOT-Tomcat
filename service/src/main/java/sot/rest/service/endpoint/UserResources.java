package sot.rest.service.endpoint;

import sot.rest.service.model.user;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("user")
@Singleton
public class UserResources {
    private List<user> users = new ArrayList<>();

    public UserResources(){
        //status 0 : active, status 1 : inactive
        users.add(new user("user","user","user",0));
        users.add(new user("Claudia","BlackSheep","BlackSheep",0));
        users.add(new user("Yeezy","IamASheep","GuineaSheep",0));
    }

    @POST
    @Path("login")
    public void login(@QueryParam("username") String uname, @QueryParam("password") String pass){
        Integer check = 0;
        for (user suser : users){
            if(suser.getUsername().equals(uname) && suser.getPassword().equals(pass)){
                System.out.println("User passed!");
                break;
            }
            else
                throw new RuntimeException("Username and Password doesn't match");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(user Us){
        if(this.getUserByUsername(Us.getUsername()) == null){
            users.add(Us);
            System.out.println("User is added!\n");
        }
        else {
            throw new RuntimeException("User " + Us.getUsername() + " already exsist! \n");
        }
    }


    @DELETE
    @Path("{uname}")
    public void deleteUser(@PathParam("uname") String uname){
         user Us = this.getUserByUsername(uname);
        if(Us != null)
            users.remove(Us);
        else
            throw new RuntimeException("User doesn't exist!");
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<user> getAllUser(){
        return users;
    }

    @GET
    @Path("{uname}")
    @Produces(MediaType.APPLICATION_JSON)
    public user getUserByUsername(@PathParam("uname") String uname){
        for (user sus: users){
            if(sus.getUsername().equals(uname)){
                return sus;
            }
        }
        return null;
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUserJSON(user User){
        user Old = this.getUserByUsername(User.getUsername());
        if(Old != null){
            Old.setUsername(User.getUsername());
            Old.setName(User.getName());
            Old.setPassword(User.getPassword());
        } else {
            throw new RuntimeException("Cannot find the User!");
        }
    }

}
