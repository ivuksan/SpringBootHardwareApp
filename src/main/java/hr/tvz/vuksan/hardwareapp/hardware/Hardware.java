package hr.tvz.vuksan.hardwareapp.hardware;

import hr.tvz.vuksan.hardwareapp.review.Review;
import hr.tvz.vuksan.hardwareapp.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="HARDWARE")
public class Hardware implements Serializable {

    @Id
    @Column(name = "CODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;

    @Column(name="NAME")
    private String name;

    @Column(name="PRICE")
    private double price;

    @Column(name="TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name="AMOUNT")
    private int amount;

    @OneToMany(mappedBy = "hardware", fetch = FetchType.EAGER)
    private List<Review> reviewList;

    public Hardware(){}

    public Hardware(long code, String name, double price, Type type, int amount) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.amount = amount;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hardware)) return false;
        Hardware hardware = (Hardware) o;
        return getCode() == hardware.getCode() && Double.compare(hardware.getPrice(), getPrice()) == 0 &&
                getAmount() == hardware.getAmount() && Objects.equals(getName(), hardware.getName()) &&
                getType() == hardware.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getName(), getPrice(), getType(), getAmount());
    }
}
