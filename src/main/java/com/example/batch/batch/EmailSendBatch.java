package com.example.batch.batch;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.batch.domain.User;
import com.example.batch.mapper.UserMapper;

@Configuration
public class EmailSendBatch {
    private final SqlSessionFactory sqlSessionFactory;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final JavaMailSender mailSender;
    private final UserMapper userMapper;

    public EmailSendBatch(SqlSessionFactory sqlSessionFactory, JobRepository jobRepository,
            PlatformTransactionManager transactionManager, JavaMailSender mailSender, UserMapper userMapper) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.mailSender = mailSender;
        this.userMapper = userMapper;
    }

    @Bean
    public Job emailSendJob() {
        return new JobBuilder("emailSendJob", jobRepository)
                .start(emailSendStep())
                .build();
    }

    @Bean
    public Step emailSendStep() {
        return new StepBuilder("emailSendStep", jobRepository)
                .<User, User>chunk(10, transactionManager) // <User, User> User타입으로 읽어와서 User타입으로 넘겨준다.
                .reader(emailItemReader())
                .processor(emailItemProcessor())
                .writer(emailItemWriter())
                .build();
    }

    @Bean
    public ItemStreamReader<User> emailItemReader() {
        MyBatisCursorItemReader<User> reader = new MyBatisCursorItemReader<>();
        reader.setSqlSessionFactory(sqlSessionFactory);
        reader.setQueryId("com.example.batch.mapper.UserMapper.findEligibleUsers");
        return reader;
    }

    @Bean
    public ItemProcessor<User, User> emailItemProcessor() {
        return user -> user;
    }

    @Bean
    public ItemWriter<User> emailItemWriter() {
        return users -> {
            System.out.println("===>" + users);
            for (User user : users) {
                System.out.println(user);
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.getEmail());
                message.setSubject("안녕하세요, " + user.getName() + "님");
                message.setText("이메일 배치 테스트입니다.");
                mailSender.send(message);

                userMapper.updateLastNotified(user.getId());
            }
        };
    }

}
