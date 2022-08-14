package hr.tvz.vuksan.hardwareapp.hardware;

import hr.tvz.vuksan.hardwareapp.Type;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HardwareRepositoryMock implements HardwareRepository{

    private final List<Hardware> hardwareList = new ArrayList<>(
            Arrays.asList(
                new Hardware(1, "Asus TUF RTX 3080", 1599.0, Type.GPU, 3),
                new Hardware(2, "EVGA XC3 RTX 3070 Ti", 1299.0, Type.GPU,  5),
                new Hardware(3, "Samsung 980 PRO SSD 1TB", 299.0, Type.STORAGE,  10),
                new Hardware(4, "AMD RYZEN 5950X", 899.0, Type.CPU,  2),
                new Hardware(5, "Kingston FURY Beast DDR5 32GB", 699.0, Type.RAM,  15)
            )
    );

    @Override
    public List<Hardware> findALL() {
        return hardwareList;
    }

    @Override
    public Optional<Hardware> findByCode(long code) {
        return hardwareList.stream().filter(c -> Objects.equals(c.getCode(), code)).findAny();
    }

    @Override
    public Optional<Hardware> saveHardware(Hardware hardware) {
        if(!hardwareList.contains(hardware)){
            hardwareList.add(hardware);
            return Optional.of(hardware);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> updateHardware(long code, Hardware hardware) {
        boolean statement = hardwareList.removeIf(it -> Objects.equals(it.getCode(), code)
                && Objects.equals(it.getCode(), hardware.getCode()));

        if(statement){
            hardwareList.add(hardware);
            return Optional.of(hardware);
        }else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteHardware(long code) {
        hardwareList.removeIf(it -> Objects.equals(it.getCode(), code));
    }
}
