package com.mutabra.services.battle;

import com.mutabra.domain.battle.Battle;
import com.mutabra.domain.battle.BattleAbility;
import com.mutabra.domain.battle.BattleCard;
import com.mutabra.domain.battle.BattleCreature;
import com.mutabra.domain.battle.BattleHero;
import com.mutabra.domain.battle.Position;
import com.mutabra.domain.game.Hero;
import com.mutabra.services.BaseEntityService;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public interface BattleService extends BaseEntityService<Battle> {

    void create(Hero hero1, Hero hero2);

    void start(Battle battle);

    void end(Battle battle);

    void endRound(Battle battle);

    void cast(Battle battle, BattleHero hero, BattleCard card, Position target);

    void cast(Battle battle, BattleCreature creature, BattleAbility ability, Position target);

    void skip(Battle battle, BattleHero hero);
}