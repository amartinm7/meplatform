package com.amm.certs.infrastructure.cloudresource.cron;

import com.amm.certs.application.cloudresource.saveall.SaveAllCloudResourcesService;
import com.amm.certs.infrastructure.framework.cloudresource.cron.CloudResourceSchedule;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CloudResourceScheduleTest {
    private SaveAllCloudResourcesService saveAllCloudResourcesService = mock(SaveAllCloudResourcesService.class);

    @Test
    void should_sync_with_external_provider_and_save_all_susbcriptions() {
        CloudResourceSchedule cloudResourceSchedule =
            new CloudResourceSchedule(saveAllCloudResourcesService, true);
        cloudResourceSchedule.run();
        verify(saveAllCloudResourcesService, times(1)).execute();
    }

    @Test
    void should_not_run_when_schedule_is_disabled() {
        CloudResourceSchedule cloudResourceSchedule =
            new CloudResourceSchedule(saveAllCloudResourcesService, false);
        cloudResourceSchedule.run();
        verify(saveAllCloudResourcesService, never()).execute();
    }
}

