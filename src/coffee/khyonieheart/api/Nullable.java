package coffee.khyonieheart.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Denotes that a symbol with this annotation can be/return null, and extra caution should be exercised.
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
public @interface Nullable {}
