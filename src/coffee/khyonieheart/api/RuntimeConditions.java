package coffee.khyonieheart.api;

/**
 * Various tools for validating arguments and conditions at runtime.
 */
public class RuntimeConditions
{
	/**
	 * Validates that an integer is positive. 
	 *
	 * @param value Value to validate
	 * 
	 * @return The value given
	 * @throws IllegalArgumentException If the value given is < 0
	 */
	public static int requirePositive(
		int value
	)
		throws IllegalArgumentException
	{
		if (value < 0)
		{
			throw new IllegalArgumentException("Value must be positive");
		}

		return value;
	}

	/**
	 * Validates that an integer is within a range.
	 *
	 * @param value Value to validate
	 * @param min Minimum allowed value
	 * @param max Maximum allowed value
	 *
	 * @return The value given
	 * @throws IllegalArgumentException If the value given is (< min) or (> max)
	 * @throws IllegalArgumentException If min is greater than max
	 */
	public static int requireRange(
		int value,
		int min,
		int max
	) {
		if (min > max)
		{
			throw new IllegalArgumentException("Minimum value must be lower than maximum value");
		}

		if (value < min || value > max)
		{
			throw new IllegalArgumentException("Value must be between " + min + " (inclusive) and " + max + " (inclusive); received " + value);
		}

		return value;
	}
}
