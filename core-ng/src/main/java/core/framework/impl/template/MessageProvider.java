package core.framework.impl.template;

import java.util.Optional;

/**
 * @author neo
 */
public interface MessageProvider {
    Optional<String> message(String key);
}
