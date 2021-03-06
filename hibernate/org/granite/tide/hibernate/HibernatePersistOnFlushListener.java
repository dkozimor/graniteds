/*
  GRANITE DATA SERVICES
  Copyright (C) 2011 GRANITE DATA SERVICES S.A.S.

  This file is part of Granite Data Services.

  Granite Data Services is free software; you can redistribute it and/or modify
  it under the terms of the GNU Library General Public License as published by
  the Free Software Foundation; either version 2 of the License, or (at your
  option) any later version.

  Granite Data Services is distributed in the hope that it will be useful, but
  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
  FITNESS FOR A PARTICULAR PURPOSE. See the GNU Library General Public License
  for more details.

  You should have received a copy of the GNU Library General Public License
  along with this library; if not, see <http://www.gnu.org/licenses/>.
*/

package org.granite.tide.hibernate;

import java.util.Map;

import org.granite.hibernate.HibernateOptimisticLockException;
import org.hibernate.HibernateException;
import org.hibernate.StaleObjectStateException;
import org.hibernate.event.PersistEvent;
import org.hibernate.event.def.DefaultPersistOnFlushEventListener;


public class HibernatePersistOnFlushListener extends DefaultPersistOnFlushEventListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void onPersist(PersistEvent event) throws HibernateException {
		try {
			super.onPersist(event);
		}
		catch (StaleObjectStateException e) {
			HibernateOptimisticLockException.rethrowOptimisticLockException(event.getSession(), e);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onPersist(PersistEvent event, Map copiedAlready) throws HibernateException {
		try {
			super.onPersist(event, copiedAlready);
		}
		catch (StaleObjectStateException e) {
			HibernateOptimisticLockException.rethrowOptimisticLockException(event.getSession(), e);
		}
	}
}
