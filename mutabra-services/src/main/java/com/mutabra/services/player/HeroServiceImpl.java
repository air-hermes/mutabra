package com.mutabra.services.player;

import com.mutabra.domain.player.Hero;
import com.mutabra.services.BaseEntityServiceImpl;
import com.mutabra.services.common.LevelService;
import org.greatage.domain.EntityRepository;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class HeroServiceImpl
		extends BaseEntityServiceImpl<Hero, HeroQuery>
		implements HeroService {

	private final LevelService levelService;

	public HeroServiceImpl(final EntityRepository repository, final LevelService levelService) {
		super(repository, Hero.class, HeroQuery.class);
		this.levelService = levelService;
	}

	@Override
	public Hero create() {
		final Hero hero = super.create();
		hero.setLevel(levelService.getDefaultLevel());
		hero.setAttack(10);
		hero.setDefence(10);
		return hero;
	}
}
