package com.mutabra.web.base.components;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Locale;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class AbstractComponent {

    @Inject
    private ComponentResources resources;

    @Inject
    private Messages messages;

    @Inject
    private Locale locale;

    public ComponentResources getResources() {
        return resources;
    }

    public Messages getMessages() {
        return messages;
    }

    public Locale getLocale() {
        return locale;
    }

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected String format(final String key, final Object... parameters) {
        return messages.format(key, parameters);
    }

    protected String message(final String key) {
        return messages.get(key);
    }

    protected String label(final String key) {
        return messages.get(i18n("label", key));
    }


    protected static String i18n(final String group, final String key) {
        return group + "." + normalize(key);
    }

    protected static String i18n(final String group, final String key, final String variant) {
        return group + "." + normalize(key) + "." + variant;
    }

    protected static String normalize(final String key) {
        return key.replaceAll("([A-Z])", "-$1").toLowerCase();
    }
}