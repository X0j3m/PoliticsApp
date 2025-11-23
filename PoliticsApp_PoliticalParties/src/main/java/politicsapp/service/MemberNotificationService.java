package politicsapp.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import politicsapp.event.PoliticalPartyCreatedEvent;
import politicsapp.event.PoliticalPartyDeletedEvent;

@Service
public class MemberNotificationService {
    private final RestTemplate restTemplate;

    public MemberNotificationService(@Qualifier("member") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @EventListener
    public void onPoliticalPartyDeleted(PoliticalPartyDeletedEvent event) {
        restTemplate.delete("/political-parties/{id}", event.getId());
    }

    @EventListener
    public void onPoliticalPartyCreated(PoliticalPartyCreatedEvent event) {
        restTemplate.put("/political-parties/{id}", event.getId(), event.getId());
    }
}
