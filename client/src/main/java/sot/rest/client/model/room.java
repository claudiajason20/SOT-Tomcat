package sot.rest.client.model;

public class room {
    private Integer id;
    private String address;
    private String number;
    private Integer price;
    private Integer size;
    private Integer status;

    public room() { this.status = 0; }

    public room(Integer id, String address, String number, Integer price, Integer size, Integer status) {
        this.id = id;
        this.address = address;
        this.number = number;
        this.price = price;
        this.size = size;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String toString(){
        return "Address = " + address + ", number = " + number + ", price = "+ price + ", size = " + size;
    }
}
