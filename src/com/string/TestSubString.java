package com.string;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TestSubString {
    private static final String OAUTH_REFRESH_TOKEN_KEY = "refresh_token";
    private static final String OAUTH_REFRESH_TOKEN_VALUE = "1/KmD8f5S8dfhBg9HmoJOO6EFTMPjmZgO3glO-lx1_LSc";

    private static final String OAUTH_CLIENT_ID_KEY = "client_id";
    private static final String OAUTH_CLIENT_ID_VALUE = "931446726990.apps.googleusercontent.com";

    private static final String OAUTH_CLIENT_SECRET_KEY = "client_secret";
    private static final String OAUTH_CLIENT_SECRET_VALUE = "3mZB5_dC6HsifgJXOTe6l7z9";

    private static final String OAUTH_GRANT_TYPE_KEY = "grant_type";
    private static final String OAUTH_GRANT_TYPE_VALUE = "refresh_token";

    public static void main(String[] args) {
        String s = "https://www.googleapis.com/androidpublisher/v3/applications/com.pandora.android/purchases/subscriptions/p.sub.plus.monthly.googleplay.499.trial.2019/tokens/ihoagokgkabcmdffhglgglfb.AO-J1OwLYSSzXyd3sJscW6SqjgDX7GqR-l_IK2qyo1Skv9m8p3ziuqrvM5fUI9eVPE8wChPp1LV2XxM1NZ-ohtLmbMHzkJeBXbPubTUaKsmDxRZ-i88Xq0lqNss1gM_GzDBO0cRxPYOiyGpROtiw8kelv4kYjmv96F2u1OauB77IrZ8d0ERq39E:cancel?access_token=ya29.a0AfH6SMBRcReKPn_5dYCyAhsJFxT1Eh5kgjlvrrmBRAcJon9YumfBOIogNkeg0UZNfL-fNXnVNgwLaHD5HBOo1so76-Uwcv_WWW0PfOO3yt5mVlLHdIYT4m9DBQHffOd-KM8fXiTfn20PmypmPQ9TwLmDUDqEdQeJsMA";
        List<String> list = Arrays.asList(":gh", ":revoke", ":refund");
        if (list.stream().anyMatch(action -> s.contains(action))) {
            System.out.println("----------");
        }
        Integer x = Integer.MAX_VALUE;
        System.out.println(x.hashCode() * 17);
        int[][] board = new int[][]{{1, 2, 3}, {4, 5, 0}};
        System.out.println(Arrays.deepToString(board));
        System.out.println(Integer.toBinaryString(4));
        int[] ar = new int[]{1, 6, 7, 2, 5, 9, 2, 1, 4, 5, 8};
        System.out.println(Arrays.stream(ar).reduce(ar[0], (a, b) -> a > b ? a : b));
        String res = "0";
        System.out.println(IntStream.rangeClosed(0, 9)
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString());
        System.out.println(IntStream.range(0, 9));

        ZonedDateTime maxTime = ZonedDateTime.now().plusDays(100);
        ZonedDateTime deferredTime = ZonedDateTime.now().plusDays(100).plusHours(23);
        System.out.println("deferred " + deferredTime);
        System.out.println("maxTime " + maxTime);
        if (deferredTime.isAfter(maxTime)) {
            if (deferredTime.toInstant().isBefore(maxTime.plusDays(1).toInstant())) {
                System.out.println("-----------");
            } else {
                System.out.println("000000000");
            }
        }
    }
}
