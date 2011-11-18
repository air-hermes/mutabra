package com.mutabra.domain;

import com.google.appengine.api.datastore.Transaction;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.mutabra.db.MutabraChangeLog;
import com.mutabra.domain.common.Card;
import com.mutabra.domain.common.CardImpl;
import com.mutabra.domain.common.Effect;
import com.mutabra.domain.common.EffectImpl;
import com.mutabra.domain.common.Face;
import com.mutabra.domain.common.FaceImpl;
import com.mutabra.domain.common.Level;
import com.mutabra.domain.common.LevelImpl;
import com.mutabra.domain.common.Race;
import com.mutabra.domain.common.RaceImpl;
import com.mutabra.domain.common.Summon;
import com.mutabra.domain.common.SummonImpl;
import com.mutabra.domain.game.Battle;
import com.mutabra.domain.game.BattleCard;
import com.mutabra.domain.game.BattleCardImpl;
import com.mutabra.domain.game.BattleImpl;
import com.mutabra.domain.game.BattleMember;
import com.mutabra.domain.game.BattleMemberImpl;
import com.mutabra.domain.game.BattleSummon;
import com.mutabra.domain.game.BattleSummonImpl;
import com.mutabra.domain.game.Hero;
import com.mutabra.domain.game.HeroCard;
import com.mutabra.domain.game.HeroCardImpl;
import com.mutabra.domain.game.HeroImpl;
import com.mutabra.domain.security.Account;
import com.mutabra.domain.security.AccountImpl;
import com.mutabra.domain.security.ChangeSet;
import com.mutabra.domain.security.ChangeSetImpl;
import com.mutabra.domain.security.Permission;
import com.mutabra.domain.security.PermissionImpl;
import com.mutabra.domain.security.Role;
import com.mutabra.domain.security.RoleImpl;
import com.mutabra.services.db.DatabaseService;
import com.mutabra.services.db.DefaultDatabaseService;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Startup;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.ValueEncoderFactory;
import org.apache.tapestry5.services.ValueEncoderSource;
import org.greatage.db.gae.GAEDatabase;
import org.greatage.domain.EntityRepository;
import org.greatage.domain.TransactionExecutor;
import org.greatage.domain.objectify.ObjectifyExecutor;
import org.greatage.domain.objectify.ObjectifyRepository;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class DomainModule {

	public static void bind(final ServiceBinder binder) {
		binder.bind(EntityRepository.class, ObjectifyRepository.class);
		binder.bind(TransactionExecutor.class, ObjectifyExecutor.class);
	}

	public DatabaseService buildDatabaseService() {
		return new DefaultDatabaseService(new GAEDatabase(), new MutabraChangeLog());
	}

	public ObjectifyFactory buildObjectifyFactory() {
		final ObjectifyFactory objectifyFactory = new ObjectifyFactory();

		objectifyFactory.register(TranslationImpl.class);

		objectifyFactory.register(AccountImpl.class);
		objectifyFactory.register(RoleImpl.class);
		objectifyFactory.register(PermissionImpl.class);
		objectifyFactory.register(ChangeSetImpl.class);

		objectifyFactory.register(FaceImpl.class);
		objectifyFactory.register(RaceImpl.class);
		objectifyFactory.register(LevelImpl.class);
		objectifyFactory.register(CardImpl.class);

		objectifyFactory.register(HeroImpl.class);
		objectifyFactory.register(HeroCardImpl.class);

		objectifyFactory.register(BattleImpl.class);
		objectifyFactory.register(BattleMemberImpl.class);
		objectifyFactory.register(BattleCardImpl.class);
		objectifyFactory.register(BattleSummonImpl.class);

		return objectifyFactory;
	}

	@Contribute(EntityRepository.class)
	public void contributeEntityRepository(final MappedConfiguration<Class, Class> configuration) {
		configuration.add(Translation.class, TranslationImpl.class);

		configuration.add(Account.class, AccountImpl.class);
		configuration.add(Role.class, RoleImpl.class);
		configuration.add(Permission.class, PermissionImpl.class);
		configuration.add(ChangeSet.class, ChangeSetImpl.class);

		configuration.add(Face.class, FaceImpl.class);
		configuration.add(Race.class, RaceImpl.class);
		configuration.add(Level.class, LevelImpl.class);
		configuration.add(Card.class, CardImpl.class);
		configuration.add(Effect.class, EffectImpl.class);
		configuration.add(Summon.class, SummonImpl.class);

		configuration.add(Hero.class, HeroImpl.class);
		configuration.add(HeroCard.class, HeroCardImpl.class);

		configuration.add(Battle.class, BattleImpl.class);
		configuration.add(BattleMember.class, BattleMemberImpl.class);
		configuration.add(BattleCard.class, BattleCardImpl.class);
		configuration.add(BattleSummon.class, BattleSummonImpl.class);
	}

	@Contribute(ValueEncoderSource.class)
	public void contributeValueEncoderSource(final MappedConfiguration<Class, ValueEncoderFactory> configuration,
											 final EntityRepository repository) {
		configuration.add(BaseEntity.class, new GAEEntityEncoderFactory(repository));
	}


	@Startup
	public void updateDatabase(final DatabaseService databaseService,
							   final @Symbol(SymbolConstants.PRODUCTION_MODE) boolean productionMode) {
		if (!productionMode) {
			databaseService.update(false, false);
		}
	}

	@Startup
	public void linkExecutor(final TransactionExecutor<Transaction, Objectify> executor) {
		Keys.init(executor);
	}
}
