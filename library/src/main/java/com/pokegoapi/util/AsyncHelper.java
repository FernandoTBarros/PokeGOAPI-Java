/*
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.pokegoapi.util;

import com.pokegoapi.exceptions.AsyncPokemonGoException;
import com.pokegoapi.exceptions.CaptchaActiveException;
import com.pokegoapi.exceptions.LoginFailedException;
import com.pokegoapi.exceptions.RemoteServerException;
import com.pokegoapi.exceptions.hash.HashException;
import rx.Observable;

public class AsyncHelper {
	/**
	 * Convert an observable to the actual result, recovering the actual exception and throwing that
	 *
	 * @param observable Observable to handle
	 * @param <T> Result type
	 * @return Result of the observable
	 * @throws LoginFailedException If an AsyncLoginFailedException was thrown
	 * @throws RemoteServerException If an AsyncRemoteServerException was thrown
	 * @throws CaptchaActiveException if an AsyncCaptchaActiveException was thrown
	 * @throws HashException if an exception occurred while requesting hash
	 */
	public static <T> T toBlocking(Observable<T> observable)
			throws LoginFailedException, RemoteServerException, CaptchaActiveException, HashException {
		try {
			return observable.toBlocking().first();
		} catch (RuntimeException e) {
			throw new AsyncPokemonGoException("Unknown exception occurred. ", e);
		}
	}
}
