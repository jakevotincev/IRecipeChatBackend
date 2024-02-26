package ru.jakev.irecipechatbackend.http.responses;

/**
 * @author evotintsev
 * @since 23.02.2024
 */
public class AuthResponse {
    private String token;
    private String refreshToken;
    private String error;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
