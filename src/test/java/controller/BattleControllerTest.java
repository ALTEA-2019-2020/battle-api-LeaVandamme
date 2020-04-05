package controller;

import com.miage.altea.tp.battle.bo.battle.Battle;
import com.miage.altea.tp.battle.bo.battle.BattleTrainer;
import com.miage.altea.tp.battle.controller.BattleController;
import com.miage.altea.tp.battle.service.BattleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BattleControllerTest {

        @Test
        void controllerShouldBeAnnotated(){
            assertNotNull(BattleController.class.getAnnotation(RestController.class));
        }

        @Test
        void battles_shouldReturnAllBattles() {

            BattleService battleService = mock(BattleService.class);
            BattleController battleController = new BattleController(battleService);

            List<Battle> battles = battleController.getAllBattles();
            assertEquals(0, battles.size());
        }

        @Test
        void battles_shouldBeAnnotated() throws NoSuchMethodException {
            var battlesMethod = BattleController.class.getDeclaredMethod("getAllBattles");
            var getMapping = battlesMethod.getAnnotation(GetMapping.class);

            assertNotNull(getMapping);
            assertArrayEquals(new String[]{"/"}, getMapping.value());
        }

    @Test
    void battle_shouldReturnABattleById() {
        Map<UUID, Battle> map = new HashMap<UUID, Battle>();
        Battle b = new Battle();
        UUID uuid = UUID.randomUUID();
        map.put(uuid, b);

        BattleController battleController = Mockito.mock(BattleController.class);
        Mockito.when(battleController.getBattle(uuid)).thenReturn(b);

        Battle battle = battleController.getBattle(uuid);
        assertEquals(b, battle);

    }

    @Test
    void battle_shouldBeAnnotated() throws NoSuchMethodException {
        var battlesMethod = BattleController.class.getDeclaredMethod("getBattle", UUID.class);
        var getMapping = battlesMethod.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/{uuid}"}, getMapping.value());
    }

    @Test
    void createBattle_shouldBeAnnotated() throws NoSuchMethodException {
        var createBattleMethod = BattleController.class.getDeclaredMethod("createBattle", String.class, String.class);
        var postMapping = createBattleMethod.getAnnotation(PostMapping.class);

        assertNotNull(postMapping);
        assertArrayEquals(new String[]{"/"}, postMapping.value());
    }

    @Test
    void createBattle_shouldInsertInMapReturnBattle() throws NoSuchMethodException {
        Map<UUID, Battle> map = new HashMap<UUID, Battle>();
        BattleTrainer b1 = new BattleTrainer("a", true);
        BattleTrainer b2 = new BattleTrainer("b", false);

        Battle battle = new Battle();
        battle.setTrainer(b1);
        battle.setOpponent(b2);
        battle.setUuid(UUID.randomUUID());

        BattleService battleService = Mockito.mock(BattleService.class);
        BattleController battleController = new BattleController(battleService);

        Mockito.when(battleService.createBattle("a", "b")).thenReturn(battle);
        Battle battleResult = battleController.createBattle("a", "b");
        assertEquals(battleResult.getTrainer().getName(), "a");
        assertEquals(battleResult.getOpponent().getName(), "b");
    }

    @Test
    void attack_shouldBeAnnotated() throws NoSuchMethodException {
        var attackMethod = BattleController.class.getDeclaredMethod("attack", UUID.class, String.class);
        var postMapping = attackMethod.getAnnotation(PostMapping.class);

        assertNotNull(postMapping);
        assertArrayEquals(new String[]{"/{uuidBattle}/{attacker}/attack"}, postMapping.value());
    }

    }


