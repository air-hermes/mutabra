package com.mutabra.web.components.game;

import com.mutabra.domain.battle.BattleCreature;
import com.mutabra.web.base.components.AbstractComponent;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class CreatureDescription extends AbstractComponent implements ClientElement {
    public static final String ID_PREFIX = "d_creature";

    @Property
    @Parameter(required = true, allowNull = false)
    private BattleCreature creature;

    public String getClientId() {
        return ID_PREFIX + encode(BattleCreature.class, creature);
    }

    public String getName() {
        return property("name");
    }

    public String getDescription() {
        return property("description");
    }

    private String property(final String property) {
        return message(i18n("creature", creature.getCode(), property));
    }
}
