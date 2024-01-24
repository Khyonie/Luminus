package coffee.khyonieheart.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Denotes that a symbol with this annotation should never be/return null.
 */
@Target({ ElementType.METHOD, ElementType.PARAMETER })
public @interface NotNull {}
