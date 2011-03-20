package com.mutabra.game;

import com.mutabra.domain.player.Hero;
import org.greatage.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class BattleCommandImpl implements BattleCommand {
	private final Battle battle;
	private final String name;

	private final BattleField field;
	private final Map<String, BattlePlayer> players;

	public BattleCommandImpl(final Battle battle, final String name) {
		this.battle = battle;
		this.name = name;

		field = new BattleFieldImpl(this);
		players = CollectionUtils.newConcurrentMap();
	}

	@Override
	public Battle getBattle() {
		return battle;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public BattleField getField() {
		return field;
	}

	@Override
	public Collection<BattlePlayer> getPlayers() {
		return players.values();
	}

	@Override
	public BattlePlayer getPlayer(final String name) {
		return players.get(name);
	}

	@Override
	public void addPlayer(final String name, final Hero hero) {
		players.put(name, new BattlePlayerImpl(this, hero));
	}
}