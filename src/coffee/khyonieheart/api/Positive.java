package coffee.khyonieheart.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Denotes that a parameter with this annotation must not be negative.
 */
@Target({ ElementType.PARAMETER })
public @interface Positive {}
