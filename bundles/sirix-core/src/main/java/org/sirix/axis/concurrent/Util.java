package org.sirix.axis.concurrent;

import javax.annotation.Nonnull;

import org.sirix.api.IAxis;
import org.sirix.exception.SirixXPathException;
import org.sirix.service.xml.xpath.EXPathError;
import org.sirix.settings.EFixed;
import org.sirix.utils.LogWrapper;
import org.slf4j.LoggerFactory;

public final class Util {

	/** Logger. */
	private static final LogWrapper LOGGER = new LogWrapper(LoggerFactory.getLogger(Util.class));
	
	/**
	 * Get next key.
	 * 
	 * @param pAxis
	 * 					the {@link IAxis}
	 * @return the next result of the axis. If the axis has no next result, the
	 *         null node key is returned.
	 */
	public static long getNext(@Nonnull final IAxis pAxis) {
		return (pAxis.hasNext()) ? pAxis.next() : EFixed.NULL_NODE_KEY
				.getStandardProperty();
	}

	/**
	 * Checks, whether the given node key belongs to a node or an atomic value.
	 * Returns true for a node and throws an exception for an atomic value,
	 * because these are not allowed in the except expression.
	 * 
	 * @param nodeKey
	 *          the node key to validate
	 * @return {@code true}, if key is a key of a node, otherwise throws an
	 *         exception
	 * @throws SirixXPathException
	 *           if the key is no node key
	 */
	public static boolean isValid(final long nodeKey) {
		if (nodeKey < 0) {
			try {
				throw EXPathError.XPTY0004.getEncapsulatedException();
			} catch (final SirixXPathException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return true;
	}

}
