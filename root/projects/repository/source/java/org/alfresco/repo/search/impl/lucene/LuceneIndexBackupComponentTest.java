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
package org.alfresco.repo.search.impl.lucene;

import java.io.File;

import junit.framework.TestCase;

import org.alfresco.repo.search.impl.lucene.LuceneIndexerAndSearcherFactory2.LuceneIndexBackupComponent;
import org.alfresco.repo.security.authentication.AuthenticationComponent;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.transaction.TransactionService;
import org.alfresco.util.ApplicationContextHelper;
import org.alfresco.util.TempFileProvider;
import org.springframework.context.ApplicationContext;

/**
 * @see org.alfresco.repo.search.impl.lucene.LuceneIndexerAndSearcherFactory.LuceneIndexBackupComponent
 * 
 * @author Derek Hulley
 */
public class LuceneIndexBackupComponentTest extends TestCase
{
    private static ApplicationContext ctx = ApplicationContextHelper.getApplicationContext();
    
    private LuceneIndexBackupComponent backupComponent;
    private File tempTargetDir;
    
    private AuthenticationComponent authenticationComponent;
    
    @Override
    public void setUp() throws Exception
    {
        TransactionService transactionService = (TransactionService) ctx.getBean("transactionComponent");
        NodeService nodeService = (NodeService) ctx.getBean("NodeService");
        LuceneIndexerAndSearcher factory = (LuceneIndexerAndSearcher) ctx.getBean("luceneIndexerAndSearcherFactory");
        
        this.authenticationComponent = (AuthenticationComponent)ctx.getBean("authenticationComponent");
        this.authenticationComponent.setSystemUserAsCurrentUser();
        
        tempTargetDir = new File(TempFileProvider.getTempDir(), getName());
        tempTargetDir.mkdir();
        
        backupComponent = new LuceneIndexBackupComponent();
        backupComponent.setTransactionService(transactionService);
        backupComponent.setFactory(factory);
        backupComponent.setNodeService(nodeService);
        backupComponent.setTargetLocation(tempTargetDir.toString());
    }
    
    @Override
    protected void tearDown() throws Exception
    {
        authenticationComponent.clearCurrentSecurityContext();
        super.tearDown();
    }
    
    public void testBackup()
    {
        backupComponent.backup();
        
        // make sure that the target directory was created
        assertTrue("Target location doesn't exist", tempTargetDir.exists());
    }
}
