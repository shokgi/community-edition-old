<%--
  Copyright (C) 2005 Alfresco, Inc.
 
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
--%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/alfresco.tld" prefix="a" %>
<%@ taglib uri="/WEB-INF/repo.tld" prefix="r" %>

<%@ page buffer="32kb" contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<h:outputText value="#{msg.reassign_select_user}<br/><br/>" escape="false" />

<a:genericPicker id="user-picker" showAddButton="false" filters="#{DialogManager.bean.filters}" 
                 queryCallback="#{DialogManager.bean.pickerCallback}" multiSelect="false" />

<script type="text/javascript">
   document.getElementById("dialog:dialog-body:user-picker_results").onchange = checkButtonState;
   
   function checkButtonState()
   {
      var button = document.getElementById("dialog:finish-button");
      var list = document.getElementById("dialog:dialog-body:user-picker_results");
      button.disabled = (list.selectedIndex == -1);
   }
</script>