package coffee.khyonieheart.api;

import java.util.Iterator;
import java.util.Objects;

/**
 * Specialized iterator for iterating over arrays.
 */
public class ArrayIterator<T> implements Iterator<T>
{
	private int index;
	private T[] data;

	/**
	 * Constructs a new ArrayIterator with the given data, starting at the given index.
	 *
	 * @param data Data to be iterated over
	 * @param index Starting index
	 *
	 * @throws IllegalArgumentException If index is negative
	 * @throws ArrayIndexOutOfBoundsException If index lies outside the bounds of the given array
	 */
	public ArrayIterator(
		@NotNull T[] data,
		@Positive int index
	) {
		this.data = Objects.requireNonNull(data);
		this.index = RuntimeConditions.requirePositive(index);

		if (index > data.length - 1)
		{
			throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds for array length " + data.length);
		}
	}

	/**
	 * Constructs a new ArrayIterator with the given data, starting at index 0.
	 *
	 * @param data Data to be iterated over
	 */
	public ArrayIterator(
		T[] data
	) {
		this(data, 0);
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasNext() 
	{
		return index < data.length;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public T next() 
	{
		return data[index++];
	}
}
