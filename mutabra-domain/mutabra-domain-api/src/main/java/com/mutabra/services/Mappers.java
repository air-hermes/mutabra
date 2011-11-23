package com.mutabra.services;

import com.mutabra.domain.Translation;
import com.mutabra.domain.game.Hero;
import com.mutabra.domain.security.Account;
import com.mutabra.domain.security.Role;
import com.mutabra.services.game.HeroMapper;
import com.mutabra.services.security.AccountMapper;
import com.mutabra.services.security.RoleMapper;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public interface Mappers {
	TranslationMapper<Translation> translation$ = new TranslationMapper<Translation>(null);
	RoleMapper<Role> role$ = new RoleMapper<Role>(null);
	AccountMapper<Account> account$ = new AccountMapper<Account>(null);
	HeroMapper<Hero> hero$ = new HeroMapper<Hero>(null);
}