package it.ricette.service;

import it.ricette.model.BlacklistedToken;
import it.ricette.repository.BlacklistedTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TokenBlacklistService {
    @Autowired
    private BlacklistedTokenRepository blacklistedTokenRepository;

    public void blacklistToken(String token, Date expirationDate, List<String> roles) {
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpirationDate(expirationDate);
        blacklistedToken.setRoles(String.join(",", roles)); // converte l'elenco in stringa
        blacklistedTokenRepository.save(blacklistedToken);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokenRepository.existsByToken(token);
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void purgeExpiredTokens() {
        Date now = new Date();
        blacklistedTokenRepository.findAll().forEach(token -> {
            if (token.getExpirationDate().before(now)) {
                blacklistedTokenRepository.delete(token);
            }
        });
    }
}

