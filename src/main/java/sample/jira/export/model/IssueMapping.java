
package sample.jira.export.model;


import de.bytefish.pgbulkinsert.mapping.AbstractMapping;
public class IssueMapping extends AbstractMapping<GWBIssue>
{
    public IssueMapping(String schema,String table) {
        super(schema, table);

       mapString("Issuekey", GWBIssue::getIssueKey);
       mapString("IssueType", GWBIssue::getIssueType);

       mapString("summary", GWBIssue::getIssueTitle);
     
       
       mapString("projectname", GWBIssue::getProjectName);
       mapString("projectkey", GWBIssue::getProjectKey);
       // mapString("Summary", GWBIssue::getDescription);
      //  mapString("Decription", GWBIssue::getDescription);
        mapString("assignee", GWBIssue::getAssignee);
        mapString("projectlead", GWBIssue::getProjectleader);
        
        mapString("reporter", GWBIssue::getReporter);
        mapString("Status", GWBIssue::getStatus);
        mapString("Priority", GWBIssue::getPriority);
        mapString("FixVersions", GWBIssue::getReleaseVerion);
        mapDate("updated", GWBIssue::getUpdateDate);
        mapDate("sprintdate", GWBIssue::getSprintDate);
       // mapInteger("timespent", GWBIssue::getAge);
        

       


      
       // mapString("description", Issue::getDescription);
    }
}
