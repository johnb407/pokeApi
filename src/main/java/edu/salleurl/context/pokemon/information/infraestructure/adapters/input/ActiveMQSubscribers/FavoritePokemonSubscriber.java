package edu.salleurl.context.pokemon.information.infraestructure.adapters.input.ActiveMQSubscribers;

import edu.salleurl.context.pokemon.information.application.IncreaseFavoritePokemonUseCase;
import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class FavoritePokemonSubscriber {

    private final IncreaseFavoritePokemonUseCase useCase;

    public FavoritePokemonSubscriber(IncreaseFavoritePokemonUseCase useCase) {
        this.useCase = useCase;
    }

    @JmsListener(destination = "favorite_pokemon")
    public void receiveQueue(String message) throws NotFoundException, RepositoryNotRespondingException {
        useCase.execute(message);

    }
}
