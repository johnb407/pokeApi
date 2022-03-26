package edu.salleurl.context.pokemon.user.infraestructure.adapter.output.jms;

import edu.salleurl.context.pokemon.user.domain.interfaces.FavoritePokemonPublisher;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;


import javax.jms.*;

@Component
public class FavoritePokemonPublisherImpl implements FavoritePokemonPublisher {


    private final JmsTemplate jmsTemplate;

    public FavoritePokemonPublisherImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void publish(String idFavoritePokemon){

        jmsTemplate.send("favorite_pokemon", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(idFavoritePokemon);
                return textMessage;
            }
        });


    }
}
