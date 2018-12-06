package sot.rest.service.endpoint;

import sot.rest.service.model.room;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("room")
@Singleton
public class RoomResources {

    private List<room> rooms = new ArrayList<>();

    public RoomResources(){
        rooms.add(new room(1,"Rumah1","3",2000,65,0));
        rooms.add(new room(2,"Rumah2","3",1000,25,0));
        rooms.add(new room(3,"Rumah3","3",5000,25,0));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String trywell() {return "This Application Works!";}

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<room> getRoom(){
        return rooms;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public room getRoomById(@PathParam("id") int id){
        for (room sroom: rooms){
            if(sroom.getId() == id){
                return sroom;
            }
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<room> getRoomByStatus(@QueryParam("status") int sta){
        List<room> tmp = new ArrayList<>();
        for (room sroom : rooms){
            if(sroom.getStatus() == sta){
                tmp.add(sroom);
            }
        }
        return tmp;
    }

    @DELETE
    @Path("{id}")
    public void deleteRoom(@PathParam("id") int id){
        room Room = this.getRoomById(id);
        if(Room != null)
            rooms.remove(Room);
        else {
            throw new RuntimeException("Room doesn't exist");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createRoom(room Room){
        if(this.getRoomById(Room.getId()) == null){
            rooms.add(Room);
            System.out.println(" added room");
        }
        else {
            throw new RuntimeException("room with id" + Room.getId() + " already exsist");
        }
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRoomJSON(room Room){
        room OldRoom = this.getRoomById(Room.getId());
        if(OldRoom != null){
            OldRoom.setId(Room.getId());
            OldRoom.setAddress(Room.getAddress());
            OldRoom.setPrice(Room.getPrice());
            OldRoom.setSize(Room.getSize());
            OldRoom.setAddress(Room.getAddress());
            OldRoom.setNumber(Room.getNumber());
        } else {
            throw new RuntimeException("Cannot find the room!");
        }
    }

    @PUT
    @Path("reserve")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateStatusRoom(@FormParam("id") int id, @FormParam("status") int status){
        room old = this.getRoomById(id);
        if(old != null && old.getStatus() == 0){
            old.setStatus(1);
        } else {
            throw new RuntimeException("Cannot find the room!");
        }
    }



}
