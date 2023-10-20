package fr.loicmathieu.dojo.quarkus.messaging.search;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class MessageManager {
    @Inject
    EnhancedOrderService enhancedOrderService;

    //TODO listen to the 'enhanced-orders' channel and add the enhanced order to the DB via the enhancedOrderService
    @Incoming( "enhanced-orders" )
    public void saveOrder( EnhancedOrder enhancedOrder ) {
        enhancedOrderService.add( enhancedOrder );
    }
}
