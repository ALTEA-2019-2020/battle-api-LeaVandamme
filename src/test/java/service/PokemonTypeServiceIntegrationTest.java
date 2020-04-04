package service;

import com.miage.altea.tp.battle.bo.pokemonType.PokemonType;
import com.miage.altea.tp.battle.service.PokemonTypeService;
import com.miage.altea.tp.battle.service.PokemonTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PokemonTypeServiceIntegrationTest {

    @Test
    void listPokemonTypes_shouldReturnAllPokemons() {
        String url = "http://localhost:8080";
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        PokemonTypeService pokemonTypeService = new PokemonTypeServiceImpl();
        pokemonTypeService.setRestTemplate(restTemplate);
        pokemonTypeService.setPokemonTypeServiceUrl(url);

        PokemonType pokemonType1 = new PokemonType();
        PokemonType pokemonType2 = new PokemonType();
        pokemonType1.setName("Pikachu");
        pokemonType2.setName("Bulbizarre");
        PokemonType[] pokemonTypes = {pokemonType1, pokemonType2};

        String expectedUrl = "http://localhost:8080/pokemon-types/";
        Mockito.when(restTemplate.getForObject(expectedUrl, PokemonType[].class)).thenReturn(pokemonTypes);

        List<PokemonType> res = pokemonTypeService.listPokemonsTypes();
        assertNotNull(res);
        assertEquals(2, res.size());

        assertEquals("Pikachu", res.get(0).getName());
    }

    @Test
    void getPokemonType_shouldReturnAPokemonType() {
        /*String url = "http://localhost:8080";
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        PokemonTypeService pokemonTypeService = new PokemonTypeServiceImpl();
        pokemonTypeService.setRestTemplate(restTemplate);
        pokemonTypeService.setPokemonTypeServiceUrl(url);

        PokemonType pokemonType = new PokemonType();
        pokemonType.setName("Pikachu");
        pokemonType.setId(25);

        String expectedUrl = "http://localhost:8080/pokemon-types/";
        Mockito.when(restTemplate.getForObject(expectedUrl+pokemonType.getId(), PokemonType.class)).thenReturn(pokemonType);
        PokemonType res = pokemonTypeService.getPokemonType(pokemonType.getId());
        assertNotNull(res);
        assertEquals("Pikachu", res.getName());*/
    }
}
