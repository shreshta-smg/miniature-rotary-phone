package tech.reactivemedia.paymentfmtsvc.core.utils;

import org.apache.commons.validator.routines.UrlValidator;

import java.net.MalformedURLException;

public final class ValidatorUtils {
    public static boolean isValidURL(String url) throws MalformedURLException {
        UrlValidator validator = new UrlValidator();
        return validator.isValid(url);
    }
}
