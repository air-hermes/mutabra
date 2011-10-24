package com.mutabra.web.components.social;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.corelib.base.AbstractComponentEventLink;
import org.apache.tapestry5.internal.util.CaptureResultCallback;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class FacebookConnect extends AbstractComponentEventLink {
	private static final String CONNECT_EVENT = "connect";
	private static final String CONNECTED_EVENT = "connected";

	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String scope;

	@Inject
	private ComponentResources resources;

	@InjectService("facebookService")
	private OAuth2ServiceProvider<Facebook> facebookService;

	@Cached
	protected String getRedirectUri() {
		return resources.createEventLink(CONNECTED_EVENT).toAbsoluteURI();
	}

	@Override
	protected Link createLink(final Object[] eventContext) {
		return resources.createEventLink(CONNECT_EVENT);
	}

	@OnEvent(CONNECT_EVENT)
	URL connect() throws MalformedURLException {
		final OAuth2Parameters parameters = new OAuth2Parameters();
		if (scope != null) {
			parameters.setScope(scope);
		}
		parameters.setRedirectUri(getRedirectUri());

		return new URL(buildConnectURL(parameters));
	}

	@OnEvent(CONNECTED_EVENT)
	Object connected(
			@RequestParameter(value = "code", allowBlank = true) final String code,
			@RequestParameter(value = "error", allowBlank = true) final String error,
			@RequestParameter(value = "error_reason", allowBlank = true) final String errorReason,
			@RequestParameter(value = "error_description", allowBlank = true) String errorDescription) {

		final CaptureResultCallback<Object> callback = new CaptureResultCallback<Object>();
		if (code != null) {
			final AccessGrant accessGrant;
			try {
				accessGrant = facebookService.getOAuthOperations().exchangeForAccess(code, getRedirectUri(), null);
				final Object[] context = {accessGrant.getAccessToken()};
				final boolean handled = resources.triggerEvent(EventConstants.SUCCESS, context, callback);

				if (handled) {
					return callback.getResult();
				}
				return null;
			} catch (Exception e) {
				errorDescription = e.getMessage();
			}
		}

		final Object[] context = {error, errorReason, errorDescription};
		final boolean handled = resources.triggerEvent(EventConstants.FAILURE, context, callback);

		if (handled) {
			return callback.getResult();
		}
		return null;
	}

	private String buildConnectURL(final OAuth2Parameters parameters) {
		return facebookService.getOAuthOperations().buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, parameters);
	}
}