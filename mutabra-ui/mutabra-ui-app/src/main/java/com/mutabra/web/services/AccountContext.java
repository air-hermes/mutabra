package com.mutabra.web.services;

import com.mutabra.domain.player.Hero;
import com.mutabra.domain.security.Account;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public interface AccountContext {

	Account getAccount();

	Hero getHero();
}
