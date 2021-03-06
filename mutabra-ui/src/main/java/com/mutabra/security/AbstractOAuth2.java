package com.mutabra.security;

import org.scribe.builder.api.Api;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public abstract class AbstractOAuth2 extends AbstractOAuth implements OAuth2 {

    protected AbstractOAuth2(final Class<? extends Api> apiClass,
                             final String consumerKey,
                             final String consumerSecret,
                             final String redirectUri) {
        super(apiClass, consumerKey, consumerSecret, redirectUri);
    }

    public Session connect(final String code) {
        return connect(null, code);
    }

    @Override
    protected Token getRequestToken(final OAuthService service) {
        return null;
    }

    @Override
    protected Token getAccessToken(final OAuthService service, final String token, final String secret) {
        return service.getAccessToken(null, new Verifier(secret));
    }
}
