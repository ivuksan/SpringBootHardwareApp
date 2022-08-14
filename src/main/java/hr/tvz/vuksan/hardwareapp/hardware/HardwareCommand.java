package hr.tvz.vuksan.hardwareapp.hardware;

import hr.tvz.vuksan.hardwareapp.Type;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class HardwareCommand {

    @NotBlank(message = "Hardware name must not be empty")
    private String name;

    @NotNull(message = "Code must be entered")
    private long code;

    @NotNull(message = "Price must be entered")
    @PositiveOrZero(message = "Price must be entered as a positive number")
    private double price;

    @NotNull(message = "Type must not be empty")
    private Type type;

    @NotNull(message = "Amount must not be empty")
    @PositiveOrZero(message = "Amount must be entered as a positive number")
    private int amount;

    public HardwareCommand(@NotBlank(message = "Hardware name must not be empty") String name,
                           @NotNull(message = "Code must be entered") long code,
                           @NotNull(message = "Price must be entered") @PositiveOrZero(message = "Price must be entered as a positive number") double price,
                           @NotNull(message = "Type must not be empty") Type type,
                           @NotNull(message = "Amount must not be empty") @PositiveOrZero(message = "Amount must be entered as a positive number") int amount) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public long getCode() {
        return code;
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
}
