package com.mutabra.services.battle;

import com.mutabra.domain.battle.Battle;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public interface ScriptEngine {

    void executeScripts(Battle battle);
}
