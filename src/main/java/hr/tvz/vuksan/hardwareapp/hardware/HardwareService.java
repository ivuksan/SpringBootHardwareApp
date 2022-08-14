package hr.tvz.vuksan.hardwareapp.hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    Optional<HardwareDTO> findByCode(long code);

    Optional<HardwareDTO> saveHardware(HardwareCommand command);

    Optional<HardwareDTO> updateHardware(long code, HardwareCommand command);

    void deleteHardware(long code);
}
