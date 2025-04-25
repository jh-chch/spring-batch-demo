package com.example.batch.scheduler;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.batch.domain.User;
import com.example.batch.mapper.UserMapper;

@Component
public class BatchScheduler {
    private final JobLauncher jobLauncher;
    private final Job sendEmailJob;
    private final UserMapper userMapper;

    public BatchScheduler(JobLauncher jobLauncher, Job sendEmailJob, UserMapper userMapper) {
        this.jobLauncher = jobLauncher;
        this.sendEmailJob = sendEmailJob;
        this.userMapper = userMapper;
    }

    @Scheduled(cron = "10 * * * * *")
    public void runEmailBatch() throws Exception {
        List<User> eligibleUsers = userMapper.findEligibleUsers();
        System.out.println(eligibleUsers);

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(sendEmailJob, jobParameters);
    }
}
