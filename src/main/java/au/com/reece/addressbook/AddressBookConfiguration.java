package au.com.reece.addressbook;

import io.dropwizard.Configuration;

/**
 *
 * @author anjali
 * 
 * Each Dropwizard application has its own subclass of the Configuration class which specifies environment-specific parameters.
 * These parameters are specified in a YAML configuration file which is deserialized to an instance of your application’s
 * configuration class and validated.
 */
public class AddressBookConfiguration extends Configuration {
    //As this is a simple application, I have not added any parameters in the yml file.
}
