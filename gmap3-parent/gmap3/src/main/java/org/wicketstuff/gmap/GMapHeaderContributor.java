package org.wicketstuff.gmap;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

public class GMapHeaderContributor extends Behavior
{

    private static final long serialVersionUID = 1L;
    // URL for Google Maps' API endpoint.
    private static final String GMAP_API_URL = "%s://maps.google.com/maps/api/js?v=3&amp";
    private static final String HTTP = "http";
    // We have some custom Javascript.
    private String scheme;
    private String sensor = "false";

    public GMapHeaderContributor()
    {
        this(HTTP, false);
    }

    /**
     * @param sensor this parameter will be ignored
     * @deprecated Since the sensor-parameter is no longer required from Google
     * you should use {@link #GMapHeaderContributor() } instead of this
     * constructor
     */
    public GMapHeaderContributor(final boolean sensor)
    {
        this(HTTP, sensor);
    }

    public GMapHeaderContributor(final String scheme)
    {
        this(scheme, false);
    }

    /**
     * Constructor.
     *
     * Should be added to the page.
     *
     * @param scheme http or https?
     * @param sensor this parameter will be ignored
     * 
     * @deprecated Since the sensor-parameter is no longer required from Google
     * you should use {@link #GMapHeaderContributor(java.lang.String) } instead of this
     * constructor
     */
    public GMapHeaderContributor(final String scheme, final boolean sensor)
    {
        this.scheme = scheme;
        if (sensor)
        {
            this.sensor = "true";
        }
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response)
    {
        super.renderHead(component, response);
        response.render(JavaScriptHeaderItem.forReference(WicketGMapJsReference.INSTANCE));
        response.render(JavaScriptHeaderItem.forUrl(String.format(GMAP_API_URL, scheme)));
    }

    /**
     * @deprecated Since the sensor-parameter is no longer required from Google
     * this method will be removed in future versions
     */
    public String getSensor()
    {
        return sensor;
    }
}
