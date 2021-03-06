package com.mutabra.web.pages;

import com.mutabra.web.base.pages.AbstractPage;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class Index extends AbstractPage {

    @Inject
    @Path("gomer.jpg")
    private Asset logo;

    public Asset getLogo() {
        return logo;
    }
}
