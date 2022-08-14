package hr.tvz.vuksan.hardwareapp.hardware;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceMock implements HardwareService{

    private final HardwareRepository hardwareRepository;

    public HardwareServiceMock(HardwareRepository hardwareRepository){
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findALL().stream().map(this::toHardwareDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> findByCode(final long code) {
        return hardwareRepository.findByCode(code).map(this::toHardwareDTO);
    }

    @Override
    public Optional<HardwareDTO> saveHardware(final HardwareCommand command) {
        return hardwareRepository.saveHardware(toHardware(command)).map(this::toHardwareDTO);
    }

    @Override
    public Optional<HardwareDTO> updateHardware(long code, HardwareCommand command) {
        return hardwareRepository.updateHardware(code, toHardware(command)).map(this::toHardwareDTO);
    }

    @Override
    public void deleteHardware(final long code) {
        hardwareRepository.deleteHardware(code);
    }

    private HardwareDTO toHardwareDTO(final Hardware hardware){
        return new HardwareDTO(hardware.getCode(), hardware.getName(), hardware.getPrice());
    }

    private Hardware toHardware(final HardwareCommand command){
        return new Hardware(command.getCode(), command.getName(), command.getPrice(), command.getType(), command.getAmount());
    }
}
