package it.ricette.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {
    private ConcurrentHashMap<String, Boolean> tokenBlacklist = new ConcurrentHashMap<>();

    public void blacklistToken(String token) {
        tokenBlacklist.put(token, true);
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.containsKey(token);
    }
}
