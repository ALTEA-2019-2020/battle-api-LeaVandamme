package controller;

import bo.battle.Battle;
import bo.trainer.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BattleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/battles")
public class BattleController {

    Map<UUID, Battle> battles = new HashMap<UUID, Battle>();

    @Autowired
    private BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @GetMapping("/")
    public Map<UUID, Battle> getAllBattles() {
        return battles;
    }

    @GetMapping("/{uuid}")
    public Battle getBattle(@PathVariable UUID uuid){
        return battles.get(uuid);
    }

    @PostMapping("/")
    public UUID createBattle(String trainer, String opponent) {
        Battle battle = this.battleService.createBattle(trainer, opponent);
        battles.put(battle.getUuid(), battle);
        return battle.getUuid();
    }

    @PostMapping("/{uuidBattle}/{attacker}/attack")
    public ResponseEntity<?> attack(@PathVariable("uuidBattle") UUID uuidBattle, @PathVariable("attacker") String attacker) {
        return null;
    }

}
