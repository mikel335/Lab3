package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * This class provides the service of converting country codes to their names.
 */

public class CountryCodeConverter {

    // TODO Task: pick appropriate instance variable(s) to store the data necessary for this class

    private final List<String> countryCode = new ArrayList<>();
    private final List<String> countryNames = new ArrayList<>();

    /**
     * Default constructor which will load the country codes from "country-codes.txt"
     * in the resources folder.
     */
    public CountryCodeConverter() {
        this("country-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the country code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public CountryCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            // TODO Task: use lines to populate the instance variable(s)

            lines.remove(0);

            for (String line : lines) {
                List<String> splitLine = List.of(line.split("\t"));
                this.countryNames.add(splitLine.get(0));
                this.countryCode.add(splitLine.get(2));
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the country for the given country code.
     * @param code the 3-letter code of the country
     * @return the name of the country corresponding to the code
     */
    public String fromCountryCode(String code) {
        // TODO Task: update this code to use an instance variable to return the correct value
        final String capitalizedCode = code.toUpperCase();
        final int index = this.countryCode.indexOf(capitalizedCode);

        return this.countryNames.get(index);
    }

    /**
     * Returns the code of the country for the given country name.
     * @param country the name of the country
     * @return the 3-letter code of the country
     */
    public String fromCountry(String country) {
        // TODO Task: update this code to use an instance variable to return the correct value
        final int index = this.countryNames.indexOf(country);

        return this.countryCode.get(index);
    }

    /**
     * Returns how many countries are included in this code converter.
     * @return how many countries are included in this code converter.
     */
    public int getNumCountries() {
        // TODO Task: update this code to use an instance variable to return the correct value
        if (this.countryNames.isEmpty()) {
            return 0;
        }
        else {
            return this.countryNames.size();
        }
    }
}
