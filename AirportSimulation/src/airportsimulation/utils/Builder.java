/*
 * Copyright (c) 2015 mdhtr.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mdhtr - initial API and implementation and/or initial documentation
 */
package airportsimulation.utils;

/**
 *
 * @author mdhtr
 * @param <T>
 */
public interface Builder<T> {

    public boolean hasNext();

    public T getNext();
}
