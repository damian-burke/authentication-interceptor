package com.brainasaservice.authenticationinterceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public abstract class AuthenticationInterceptor implements Interceptor {

    private final String tempHeader;

    private final String authenticationHeader;

    /**
     * @param tempHeader           Temporary header that is going to be removed.
     * @param authenticationHeader Authentication header that will be added to the request.
     */
    public AuthenticationInterceptor(String tempHeader, String authenticationHeader) {
        this.tempHeader = tempHeader;
        this.authenticationHeader = authenticationHeader;
    }

    /**
     * @param header Authentication header that will be used both to determine the token identifier and
     *               will contain the final authentication token.
     */
    public AuthenticationInterceptor(String header) {
        this.tempHeader = header;
        this.authenticationHeader = header;
    }

    /**
     * @param identifier Token identifier based on your temporary headers
     * @return Authentication token for the given identifier
     */
    public abstract String getTokenByIdentifier(String identifier);

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String scopeValue = request.header(tempHeader);
        if (scopeValue != null && !scopeValue.isEmpty()) {
            String token = getTokenByIdentifier(scopeValue);

            request = request.newBuilder()
                    .removeHeader(tempHeader)
                    .addHeader(authenticationHeader, token)
                    .build();
        }

        return chain.proceed(request);
    }
}
