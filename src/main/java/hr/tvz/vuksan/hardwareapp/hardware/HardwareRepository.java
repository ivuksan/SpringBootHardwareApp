package hr.tvz.vuksan.hardwareapp.hardware;

import hr.tvz.vuksan.hardwareapp.hardware.Hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {

    List<Hardware> findALL();

    Optional<Hardware> findByCode(long code);

    Optional<Hardware> saveHardware(Hardware hardware);

    Optional<Hardware> updateHardware(long code, Hardware hardware);

    void deleteHardware(long code);
}
