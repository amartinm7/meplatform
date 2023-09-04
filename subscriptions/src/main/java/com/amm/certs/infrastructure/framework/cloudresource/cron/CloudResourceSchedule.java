package com.amm.certs.infrastructure.framework.cloudresource.cron;

import com.amm.certs.application.cloudresource.saveall.SaveAllCloudResourcesService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CloudResourceSchedule {

    private final SaveAllCloudResourcesService saveAllCloudResourcesService;

    private Boolean isEnabledScheduler;

    public CloudResourceSchedule(
        SaveAllCloudResourcesService saveAllCloudResourcesService,
        @Value("${app.scheduler.cloud_resource.enabled}") Boolean isEnabledScheduler
    ) {
        this.saveAllCloudResourcesService = saveAllCloudResourcesService;
        this.isEnabledScheduler = isEnabledScheduler;
    }

    @Scheduled(cron = "${app.scheduler.cloud_resource.scheduled}")
    @SchedulerLock(
        name = "create_retrieveAllSubscriptionsService",
        lockAtLeastFor = "${app.scheduler.cloud_resource.shedlock.lockAtLeast}",
        lockAtMostFor = "${app.scheduler.cloud_resource.shedlock.lockAtMost}"
    )
    public void run() {
        try {
            LOGGER.info(">>> Starting cron SubscriptionSchedule");
            if (isEnabledScheduler) {
                saveAllCloudResourcesService.execute();
            }
            LOGGER.info(">>> Ended cron SubscriptionSchedule");
        } catch (Exception exception) {
            LOGGER.error(">>> Error executing cron SubscriptionSchedule ${exception.message}", exception);
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CloudResourceSchedule.class);
}
