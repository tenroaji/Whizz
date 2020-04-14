//package id.magau.whizz.ui.kelas.materi.video;
//
//import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
//
//import java.net.CookieHandler;
//import java.net.CookieManager;
//import java.net.CookiePolicy;
//public static class MySimpleExoPlayerWrapper extends SimpleExoPlayerWrapper {
//    private static final CookieManager DEFAULT_COOKIE_MANAGER;
//    static {
//        DEFAULT_COOKIE_MANAGER = new CookieManager();
//        DEFAULT_COOKIE_MANAGER.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
//    }
//    @Override
//    public DefaultDataSourceFactory createDefaultDataFactory() {
//        if (CookieHandler.getDefault() != DEFAULT_COOKIE_MANAGER) {
//            CookieHandler.setDefault(DEFAULT_COOKIE_MANAGER);
//        }
//        return new DefaultDataSourceFactory(BA.applicationContext, "Mozilla/5.0 AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7D11 Safari/528.16");
//    }
//}
