package service;

import com.miage.altea.tp.battle.bo.trainer.Trainer;
import com.miage.altea.tp.battle.service.TrainerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {

    @Test
    void listTrainers_shouldCallTheRemoteService() {
        var url = "http://localhost:8081";

        var restTemplate = mock(RestTemplate.class);
        var trainerServiceImpl = new TrainerServiceImpl();
        trainerServiceImpl.setRestTemplate(restTemplate);
        trainerServiceImpl.setTrainerServiceUrl(url);

        var ash = new Trainer();
        ash.setName("ash");

        var expectedUrl = "http://localhost:8081/trainers/";
        Mockito.when(trainerServiceImpl.listTrainer()).thenReturn(Arrays.asList(new Trainer[]{ash}));

        var trainers = trainerServiceImpl.listTrainer();

        assertNotNull(trainers);
        assertEquals(1, trainers.size());

        verify(restTemplate).getForObject(expectedUrl, Trainer[].class);
    }

    @Test
    void pokemonServiceImpl_shouldBeAnnotatedWithService(){
        assertNotNull(TrainerServiceImpl.class.getAnnotation(Service.class));
    }

    @Test
    void setRestTemplate_shouldBeAnnotatedWithAutowired() throws NoSuchMethodException {
        var setRestTemplateMethod = TrainerServiceImpl.class.getDeclaredMethod("setRestTemplate", RestTemplate.class);
        assertNotNull(setRestTemplateMethod.getAnnotation(Autowired.class));
    }

    @Test
    void setTrainerServiceUrl_shouldBeAnnotatedWithValue() throws NoSuchMethodException {
        var setter = TrainerServiceImpl.class.getDeclaredMethod("setTrainerServiceUrl", String.class);
        var valueAnnotation = setter.getAnnotation(Value.class);
        assertNotNull(valueAnnotation);
        assertEquals("${trainer.service.url}", valueAnnotation.value());
    }
}

