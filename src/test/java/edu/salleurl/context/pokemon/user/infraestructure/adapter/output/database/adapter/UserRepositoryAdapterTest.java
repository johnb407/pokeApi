package edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.adapter;

import edu.salleurl.context.pokemon.user.domain.model.Id;
import edu.salleurl.context.pokemon.user.domain.model.User;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class UserRepositoryAdapterTest {

    @Autowired
    private UserRepositoryAdapter adapter;

    @Autowired
    private UserRepository rep;

    @Test
    public void saveTest(){

        Set<String> pokemonIds = new HashSet<>();
        pokemonIds.add("p123");
        User user = new User("123", pokemonIds);
        adapter.save(user);
        Optional<User> byId = adapter.findById(new Id("123"));
        assertEquals(byId.get().getId().getId(),"123");
        assertEquals(byId.get().getIdFavorites().size(),1);

    }



}