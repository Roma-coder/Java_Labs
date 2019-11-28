package lab4;

import lab4.model.Route;
import org.testng.annotations.Test;

public class RouteTest {

    private Route validRoute;
    private Route notValidRoute;

    {
        validRoute = new Route("Lviv", "Chernivtsi");
        notValidRoute = new Route(null, null);
    }

    @Test
    public void testRaceValidate() {
        validRoute.validate();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testRaceValidateNegative() {
        notValidRoute.validate();
    }
}
