package fr.loicmathieu.dojo.quarkus.messaging.order.enhancer;

import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MessageManager {
    private static final Logger LOGGER = LoggerFactory.getLogger( MessageManager.class );
    @Inject
    UserService userService;

    // TODO enhance the order using a method that register to the 'orders' channel and send the enhanced order to the 'enhanced-orders' channel
    // use the userService to get the user and enhance the order with the user info

    @Incoming( "orders" )
    @Outgoing( "enhanced-orders" )
    public EnhancedOrder enhancedOrder( Order order ) {
        LOGGER.info( "Receiving order: {}", order );
        return new EnhancedOrder( order, userService.getUser( order.userId() ) );
    }
}
