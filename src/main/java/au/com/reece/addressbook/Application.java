package au.com.reece.addressbook;

import au.com.reece.addressbook.controller.AddressBookController;
import au.com.reece.addressbook.exception.ApplicationExceptionMapper;
import au.com.reece.addressbook.service.AddressBookService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author anjali
 */
public class Application extends io.dropwizard.Application<AddressBookConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Override
    public void initialize(Bootstrap<AddressBookConfiguration> bootstrap) {
    }

    @Override
    public void run(AddressBookConfiguration configuration, Environment environment) throws Exception {

        Injector injector = Guice.createInjector();
        AddressBookService addressBookService = injector.getInstance(AddressBookService.class);
        LOGGER.info("Registering REST resources");
        environment.jersey().register(new AddressBookController(addressBookService));
        environment.jersey().register(new ApplicationExceptionMapper());
    }

    public static void main(String[] args) throws Exception {
        new Application().run(args);
    }
}
