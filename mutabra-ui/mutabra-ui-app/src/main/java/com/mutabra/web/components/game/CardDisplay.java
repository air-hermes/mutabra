package com.mutabra.web.components.game;

import com.mutabra.domain.battle.BattleHero;
import com.mutabra.domain.common.Card;
import com.mutabra.domain.common.TargetType;
import com.mutabra.web.base.components.AbstractComponent;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class CardDisplay extends AbstractComponent implements ClientElement {

	@Property
	@Parameter
	private BattleHero hero;

	@Property
	@Parameter
	private Card value;

	@Inject
	private JavaScriptSupport support;

	private String clientId;

	public String getClientId() {
		return clientId;
	}

	@SetupRender
	void setupCard() {
		clientId = "c_" + value.getCode();
	}

	@AfterRender
	void renderScript() {
		final TargetType targetType = value.getTargetType();
		support.addInitializerCall("card", new JSONObject()
				.put("id", getClientId())
				.put("url", getResources().createEventLink("registerActions", hero, value).toAbsoluteURI())
				.put("massive", targetType.isMassive())
				.put("supports_enemy_side", targetType.supportsEnemy())
				.put("supports_friend_side", targetType.supportsFriend())
				.put("supports_empty_point", targetType.supportsEmpty())
				.put("supports_hero_point", targetType.supportsHero())
				.put("supports_summon_point", targetType.supportsSummon()));
	}
}
