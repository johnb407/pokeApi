package edu.salleurl;


import javax.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMQConfig {

    public class ActiveMqQueueConfig {

        @Bean
        public Queue queue(){
            return new ActiveMQQueue("favorite_pokemon");
        }
    }
}
