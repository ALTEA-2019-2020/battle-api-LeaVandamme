package com.miage.altea.tp.battle.controller;

import com.miage.altea.tp.battle.bo.battle.Battle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.miage.altea.tp.battle.service.BattleService;

import java.util.*;

@RestController
@RequestMapping("/battles")
public class BattleController {

    Map<UUID, Battle> battles = new HashMap<UUID, Battle>();

    public BattleService getBattleService() {
        return battleService;
    }

    public void setBattleService(BattleService battleService) {
        this.battleService = battleService;
    }

    @Autowired
    private BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @GetMapping("/")
    public List<Battle> getAllBattles(){
        List<Battle> res = new ArrayList<Battle>();
        for(UUID uuid : battles.keySet()) {
            res.add(battles.get(uuid));
        }
        return res;
    }

    @GetMapping("/{uuid}")
    public Battle getBattle(@PathVariable UUID uuid){
        return battles.get(uuid);
    }

    @PostMapping(value = "/", params={"attacker","opponent"}, produces = "application/json")
    public UUID createBattle(@RequestParam String attacker, @RequestParam String opponent) {
        Battle battle = this.battleService.createBattle(attacker, opponent);
        battles.put(battle.getUuid(), battle);
        return battle.getUuid();
    }

    @PostMapping("/{uuidBattle}/{attacker}/attack")
    public ResponseEntity<?> attack(@PathVariable("uuidBattle") UUID uuidBattle, @PathVariable("attacker") String attacker) {
        if(!battles.containsKey(uuidBattle)) return new ResponseEntity<>("Battle does not exist", HttpStatus.BAD_REQUEST);
        try {
            var battle = battleService.attackAction(attacker,battles.get(uuidBattle));
            battles.replace(uuidBattle,battle);
            return new ResponseEntity<>(battle,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
