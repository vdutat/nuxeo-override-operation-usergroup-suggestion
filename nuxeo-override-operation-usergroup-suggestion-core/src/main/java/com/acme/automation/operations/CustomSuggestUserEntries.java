package com.acme.automation.operations;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.automation.core.operations.users.SuggestUserEntries;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.core.schema.SchemaManager;
import org.nuxeo.ecm.directory.api.DirectoryService;
import org.nuxeo.ecm.platform.usermanager.UserManager;
import org.nuxeo.runtime.api.Framework;

/**
 *
 */
@Operation(id=CustomSuggestUserEntries.ID, category=Constants.CAT_SERVICES, label="Get user/group suggestion (enhanced group restriction)", description="Get the user/group list of the running instance. This is returning a blob containing a serialized JSON array.")
public class CustomSuggestUserEntries extends SuggestUserEntries {

    public static final String ID = "UserGroup.Suggestion";
    
    private static final Log log = LogFactory.getLog(CustomSuggestUserEntries.class);

    @Context
    protected OperationContext ctx;

    @Param(name = "searchTerm", alias = "prefix", required = false)
    protected String prefix;

    @Param(name = "searchType", required = false)
    protected String searchType;

    @Param(name = "groupRestriction", required = false, description = "Enter the id of a group to suggest only user from this group.")
    protected String groupRestriction;

    /**
     * @since 7.10
     */
    @Param(name = "hideAdminGroups", required = false, description = "If set, remove all administrator groups from the suggestions")
    protected boolean hideAdminGroups;

    /**
     * @since 8.3
     */
    @Param(name = "hidePowerUsersGroup", required = false, description = "If set, remove power users group from the suggestions")
    protected boolean hidePowerUsersGroup;

    @Param(name = "userSuggestionMaxSearchResults", required = false)
    protected Integer userSuggestionMaxSearchResults;

    @Param(name = "firstLabelField", required = false)
    protected String firstLabelField;

    @Param(name = "secondLabelField", required = false)
    protected String secondLabelField;

    @Param(name = "thirdLabelField", required = false)
    protected String thirdLabelField;

    @Param(name = "hideFirstLabel", required = false)
    protected boolean hideFirstLabel = false;

    @Param(name = "hideSecondLabel", required = false)
    protected boolean hideSecondLabel = false;

    @Param(name = "hideThirdLabel", required = false)
    protected boolean hideThirdLabel;

    @Param(name = "displayEmailInSuggestion", required = false)
    protected boolean displayEmailInSuggestion;

    @Param(name = "hideIcon", required = false)
    protected boolean hideIcon;

    @Param(name = "lang", required = false)
    protected String lang;

    /*
     * @since 11.4
     */
    @Param(name = "allowSubGroupsRestriction", required = false, description = "Whether to take into account subgroups when evaluating groupRestriction.")
    protected boolean allowSubGroupsRestriction;

    @OperationMethod
    public Blob run() throws IOException {
        if (log.isDebugEnabled()) {
            log.debug("<run> ");
        }
        // Services from extended class need to be instantiated this way, 
        // injection with @Context annotation does not work.
        this.userManager = Framework.getService(UserManager.class);
        this.schemaManager = Framework.getService(SchemaManager.class);
        this.directoryService = Framework.getService(DirectoryService.class);
        return super.run();
    }

    /**
     * Applies group restrictions, and returns Map objects.
     */
    @Override
    protected List<Map<String, Object>> usersToMapWithGroupRestrictions(DocumentModelList userList) {
        if (log.isDebugEnabled()) {
            log.debug("<usersToMapWithGroupRestrictions> ");
        }
        // TODO put your code HERE and commment the following line if not needed
        // See https://github.com/nuxeo/nuxeo/blob/10.10/nuxeo-features/nuxeo-automation/nuxeo-automation-features/src/main/java/org/nuxeo/ecm/automation/core/operations/users/SuggestUserEntries.java
        return super.usersToMapWithGroupRestrictions(userList);
    }

}
