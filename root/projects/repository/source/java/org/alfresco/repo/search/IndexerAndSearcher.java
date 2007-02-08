/*
 * Copyright (C) 2005 Alfresco, Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.alfresco.repo.search;

import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchService;

/**
 * Interface for Indexer and Searcher Factories to implement
 * 
 * @author andyh
 * 
 */
public interface IndexerAndSearcher
{
    /**
     * Get an indexer for a store
     * 
     * @param storeRef
     * @return
     * @throws IndexerException
     */
    public abstract IndexerSPI getIndexer(StoreRef storeRef) throws IndexerException;

    /**
     * Get a searcher for a store
     * 
     * @param storeRef
     * @param searchDelta -
     *            serach the in progress transaction as well as the main index
     *            (this is ignored for searches that do full text)
     * @return
     * @throws SearcherException
     */
    public abstract SearchService getSearcher(StoreRef storeRef, boolean searchDelta) throws SearcherException;
    
    
    /**
     * Do any indexing that may be pending on behalf of the current transaction.
     *
     */
    public abstract void flush();
}
