package service;
import com.miage.altea.tp.battle.service.BattleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BattleServiceTest {

    @Test
    void battleServiceImpl_shouldBeAnnotatedWithService(){
        assertNotNull(BattleServiceImpl.class.getAnnotation(Service.class));
    }
}

