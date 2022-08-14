package hr.tvz.vuksan.hardwareapp.hardware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {

    private final HardwareServiceMock hardwareServiceMock;

    public HardwareController(HardwareServiceMock hardwareServiceMock){
        this.hardwareServiceMock = hardwareServiceMock;
    }

    @GetMapping
    public List<HardwareDTO> getAllHardware(){
        return hardwareServiceMock.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable final long code){
        return hardwareServiceMock.findByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @PostMapping
    public ResponseEntity<HardwareDTO> saveHardware(@Valid @RequestBody final HardwareCommand command){
        return hardwareServiceMock.saveHardware(command)
                .map(
                        hardwareDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> updateHardware(@PathVariable final long code,
                                                      @Valid @RequestBody final HardwareCommand command){
        return hardwareServiceMock.updateHardware(code, command)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void deleteHardware(@PathVariable long code){
        hardwareServiceMock.deleteHardware(code);
    }
}
