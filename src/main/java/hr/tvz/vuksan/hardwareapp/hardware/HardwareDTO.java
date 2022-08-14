package hr.tvz.vuksan.hardwareapp.hardware;

public class HardwareDTO {

    private final long code;
    private final String name;
    private final double price;

    public HardwareDTO(long code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return "HardwareDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
