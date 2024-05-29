package tech.reactivemedia.paymentfmtsvc.core.utils;

import io.quarkus.runtime.util.HashUtil;

import java.util.Base64;

public final class HashUtils {
    public static String sha1WithBase64(String input) {
        var sha1Bytes = HashUtil.sha1(input).getBytes();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(sha1Bytes);
    }
}
