package github.girish3.hackernews.provider;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Girish on 28/05/16.
 */
public class BusProvider {

    private static final Bus BUS = new Bus();

    private BusProvider() {
    }

    public static Bus getBusInstance() {
        return BUS;
    }
}
