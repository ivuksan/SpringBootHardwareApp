package hr.tvz.vuksan.hardwareapp.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(HardwareAvailabilityJob.class).withIdentity("HARDWARE_AVAILABILITY_IDENTITY")
                .storeDurably().build();
    }

    @Bean
    public Trigger trigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10).repeatForever();
        return TriggerBuilder.newTrigger().forJob(jobDetail()).withSchedule(scheduleBuilder).build();
    }
}
