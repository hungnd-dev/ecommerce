package vn.dev.danghung.entities;

import javax.persistence.*;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @Column(name = "delivery_type")
    private String deliveryType;

    @Column(name = "phone_receive")
    private String phoneReceive;

    @Column(name = "create_at")
    private long createAt;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "state")
    private int state;



    public Order() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getPhoneReceive() {
        return phoneReceive;
    }

    public void setPhoneReceive(String phoneReceive) {
        this.phoneReceive = phoneReceive;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", cartId=" + cartId +
                ", address='" + address + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                ", phoneReceive='" + phoneReceive + '\'' +
                ", createAt=" + createAt +
                ", amount=" + amount +
                ", state=" + state +
                '}';
    }
}