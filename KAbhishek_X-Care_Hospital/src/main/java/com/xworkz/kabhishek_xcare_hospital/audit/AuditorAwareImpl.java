package com.xworkz.kabhishek_xcare_hospital.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

    private HttpSession httpSession;

    public AuditorAwareImpl(HttpSession session){
        this.httpSession = session;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        String gmail = (String) httpSession.getAttribute("email");
        log.info(gmail);
        return Optional.ofNullable(gmail);
    }
}
