package edu.salleurl.context.pokemon.information.infraestructure.adapters.output.database.adapter;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.FavoriteCount;
import edu.salleurl.context.pokemon.information.domain.model.Id;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.infraestructure.adapters.output.database.repositories.FavoritePokemonCountRepository;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class FavoritePokemonCountAdapterTest {

    @Autowired
    private FavoritePokemonCountAdapter adapter;

    @Autowired
    private FavoritePokemonCountRepository rep;

    @Test
    public void save() throws NotFoundException, RepositoryNotRespondingException {
        Pokemon pokemon = new Pokemon(new Id(20));
        pokemon.setFavoriteCount(new FavoriteCount(2));
        adapter.save(pokemon);

        FavoriteCount pokemonFavoriteCount = adapter.getPokemonFavoriteCount(new Id(20));

        assertEquals(Integer.valueOf(2),pokemonFavoriteCount.getCount());
    }

}