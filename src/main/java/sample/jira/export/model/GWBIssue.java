
package sample.jira.export.model;

import java.time.LocalDate;
import com.atlassian.jira.rest.client.api.domain.Issue;
import de.bytefish.pgbulkinsert.mapping.AbstractMapping;

public class GWBIssue{


     String status;
     String priority;
     String issueKey;


	 String issueTitle;
	  String issueType;
     String summary;
	  String assignee;
     String tester;
     String ta;
     String tl;
     String ba;
     String po;
     String projectleader;
     String reporter;
     String projectKey;
	  String projectName;
    
     int age;
     String releaseVerion;
     LocalDate UpdateDate;
     LocalDate SprintDate;

     String description;

     public String getStatus() {
          return this.status;
     }

     public void setStatus(String status) {
          this.status = status;
     }

     public String getPriority() {
          return this.priority;
     }

     public void setPriority(String priority) {
          this.priority = priority;
     }

     public String getIssueKey() {
          return this.issueKey;
     }

     public void setIssueKey(String issueKey) {
          this.issueKey = issueKey;
     }

     public String getIssueTitle() {
          return this.issueTitle;
     }

     public void setIssueTitle(String issueTitle) {
          this.issueTitle = issueTitle;
     }

     public String getIssueType() {
          return this.issueType;
     }

     public void setIssueType(String issueType) {
          this.issueType = issueType;
     }

     public String getSummary() {
          return this.summary;
     }

     public void setSummary(String summary) {
          this.summary = summary;
     }

     public String getAssignee() {
          return this.assignee;
     }

     public void setAssignee(String assignee) {
          this.assignee = assignee;
     }

     public String getTester() {
          return this.tester;
     }

     public void setTester(String tester) {
          this.tester = tester;
     }

     public String getTa() {
          return this.ta;
     }

     public void setTa(String ta) {
          this.ta = ta;
     }

     public String getTl() {
          return this.tl;
     }

     public void setTl(String tl) {
          this.tl = tl;
     }

     public String getBa() {
          return this.ba;
     }

     public void setBa(String ba) {
          this.ba = ba;
     }

     public String getPo() {
          return this.po;
     }

     public void setPo(String po) {
          this.po = po;
     }

     public String getProjectleader() {
          return this.projectleader;
     }

     public void setProjectleader(String projectleader) {
          this.projectleader = projectleader;
     }

     public String getReporter() {
          return this.reporter;
     }

     public void setReporter(String reporter) {
          this.reporter = reporter;
     }

     public String getProjectKey() {
          return this.projectKey;
     }

     public void setProjectKey(String projectKey) {
          this.projectKey = projectKey;
     }

     public String getProjectName() {
          return this.projectName;
     }

     public void setProjectName(String projectName) {
          this.projectName = projectName;
     }

     public int getAge() {
          return this.age;
     }

     public void setAge(int age) {
          this.age = age;
     }

     public String getReleaseVerion() {
          return this.releaseVerion;
     }

     public void setReleaseVerion(String releaseVerion) {
          this.releaseVerion = releaseVerion;
     }

     public LocalDate getUpdateDate() {
          return this.UpdateDate;
     }

     public void setUpdateDate(LocalDate UpdateDate) {
          this.UpdateDate = UpdateDate;
     }

     public LocalDate getSprintDate() {
          return this.SprintDate;
     }

     public void setSprintDate(LocalDate SprintDate) {
          this.SprintDate = SprintDate;
     }

     public String getDescription() {
          return this.description;
     }

     public void setDescription(String description) {
          this.description = description;
     }


    


 



}