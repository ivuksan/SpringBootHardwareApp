package hr.tvz.vuksan.hardwareapp.jobs;

import hr.tvz.vuksan.hardwareapp.hardware.Hardware;
import hr.tvz.vuksan.hardwareapp.hardware.HardwareRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class HardwareAvailabilityJob extends QuartzJobBean {

    private Logger log = LoggerFactory.getLogger(HardwareAvailabilityJob.class);

    private final HardwareRepository hardwareRepository;

    public HardwareAvailabilityJob(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        final List<Hardware> hardwareList = hardwareRepository.findALL();

        if(!hardwareList.isEmpty()){
            log.info("Ovo su trenutno dostupni hardveri");
            log.info("-----------------------------");
            hardwareList.forEach(
                    hardware -> {
                        if (hardware.getAmount() > 0){
                            log.info(hardware.getName() + " - " + hardware.getAmount());
                        }
                    }

            );
            log.info("-----------------------------");
        }else {
            log.info("Nema dostupnih hardvera");
        }
    }
}
