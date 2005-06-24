/*
 * Copyright (C) 2005 Alfresco, Inc.
 *
 * Licensed under the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version
 * 2.1 of the License, or (at your option) any later version.
 * You may obtain a copy of the License at
 *
 *     http://www.gnu.org/licenses/lgpl.txt
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package org.alfresco.filesys.server.auth.acl;

import org.alfresco.config.ConfigElement;
import org.alfresco.filesys.server.SrvSession;
import org.alfresco.filesys.server.config.InvalidConfigurationException;
import org.alfresco.filesys.server.config.ServerConfiguration;
import org.alfresco.filesys.server.core.SharedDevice;
import org.alfresco.filesys.server.core.SharedDeviceList;

/**
 * Access Control Manager Interface
 * <p>
 * Used to control access to shared filesystems.
 */
public interface AccessControlManager
{

    /**
     * Initialize the access control manager
     * 
     * @param config ServerConfiguration
     * @param params ConfigElement
     * @exception InvalidConfigurationException
     */
    public void initialize(ServerConfiguration config, ConfigElement params) throws InvalidConfigurationException;

    /**
     * Check access to the shared filesystem for the specified session
     * 
     * @param sess SrvSession
     * @param share SharedDevice
     * @return int
     */
    public int checkAccessControl(SrvSession sess, SharedDevice share);

    /**
     * Filter a shared device list to remove shares that are not visible or the session does not
     * have access to.
     * 
     * @param sess SrvSession
     * @param shares SharedDeviceList
     * @return SharedDeviceList
     */
    public SharedDeviceList filterShareList(SrvSession sess, SharedDeviceList shares);

    /**
     * Create an access control
     * 
     * @param type String
     * @param params ConfigElement
     * @return AccessControl
     * @exception ACLParseException
     * @exception InvalidACLTypeException
     */
    public AccessControl createAccessControl(String type, ConfigElement params) throws ACLParseException,
            InvalidACLTypeException;

    /**
     * Add an access control parser to the list of available access control types.
     * 
     * @param parser AccessControlParser
     */
    public void addAccessControlType(AccessControlParser parser);
}
