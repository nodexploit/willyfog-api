package controllers.v1;

import com.google.inject.Inject;
import daos.CityDao;
import daos.CountryDao;
import models.City;
import models.Country;
import play.mvc.Result;

import java.util.List;

public class CountryController extends BaseController {

    @Inject
    private CountryDao countryDao;
    @Inject
    private CityDao cityDao;

    public Result index() {
        List<Country> cs = countryDao.all();

        return ok(gson.toJson(cs));
    }

    public Result cities(Long countryId) {
        List<City> cs = cityDao.countryCities(countryId);

        return ok(gson.toJson(cs));
    }
}
