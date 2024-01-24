package coffee.khyonieheart.api;

import java.util.Objects;
import java.util.function.Function;

/**
 * Little utilities to handle arrays.
 */
public class Arrays
{
	/**
	 * Converts an array into a string, using either the provided mapper or {@link Object#toString()}.
	 *
	 * @param data Array to convert into a string
	 * @param concatenator String to seperate array entries with
	 * @param mapper Optional mapper function to convert elements in data to a string
	 *
	 * @return String containing all elements in the given array
	 */
	@NotNull
	public static <T> String toString(
		@NotNull T[] data,
		@NotNull String concatenator,
		@Nullable Function<T, String> mapper
	) {
		Objects.requireNonNull(data);
		Objects.requireNonNull(concatenator);

		StringBuilder builder = new StringBuilder();
		ArrayIterator<T> iter = new ArrayIterator<>(data);
		if (mapper != null)
		{
			while (iter.hasNext())
			{
				T next = iter.next();
				builder.append(next == null ? "null" : mapper.apply(iter.next()));

				if (iter.hasNext())
				{
					builder.append(concatenator);
				}
			}

			return builder.toString();
		}

		while (iter.hasNext())
		{
			T next = iter.next();
			builder.append(next == null ? "null" : next.toString());

			if (iter.hasNext())
			{
				builder.append(concatenator);
			}
		}

		return builder.toString();
	}
}
