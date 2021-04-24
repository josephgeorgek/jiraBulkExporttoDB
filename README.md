### An introduction for usage of JIRA Java APIs. 

I'll pick up as the example to export JIRA issues' summaries into Microsoft Excel. 
It is already provided as a standard function by Atlassian(*), but I hope this could be helpful for person who starts learning how to use JIRA Java APIs.

\* [JIRA Exporting Search Results to Microsoft Excel](https://confluence.atlassian.com/jira064/exporting-search-results-to-microsoft-excel-720416693.html)

#### Essential codes for searching JIRA issues are only two steps.;

```java
    // 1. Create JiraRestClient with using AsynchronousJiraRestClientFactory.
    JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
    URI uri = new URI("https://your-project-name.atlassian.net");
    jiraRestClient = factory.createWithBasicHttpAuthentication(uri, userName, password);

    // 2. Get SearchRestClient and search by jql(JIRA Query Language).
    SearchRestClient searchRestClient = jiraRestClient.getSearchClient();
    final Set<String> fields = new HashSet<String>();
    String jql = "status!=done"; // Only not done issues.
    SearchResult result = searchRestClient.searchJql(jql, max, offset, fields).claim();
    Iterable<Issue> issues = result.getIssues();
    List<Issue> list = new ArrayList<Issue>();
    for (final Issue issue : issues) {
        list.add(issue);
    }
```

----
-- Table: public.jira_bv

-- DROP TABLE public.jira_bv;

CREATE TABLE public.jira_bv
(
    summary text COLLATE pg_catalog."default",
    issuekey text COLLATE pg_catalog."default",
    applicationame text COLLATE pg_catalog."default",
    issueid bigint,
    issuetype text COLLATE pg_catalog."default",
    status text COLLATE pg_catalog."default",
    projectkey text COLLATE pg_catalog."default",
    projectname text COLLATE pg_catalog."default",
    projecttype text COLLATE pg_catalog."default",
    projectlead text COLLATE pg_catalog."default",
    projectdescription double precision,
    projecturl double precision,
    priority text COLLATE pg_catalog."default",
    resolution double precision,
    sprint text COLLATE pg_catalog."default",
    fixversions text COLLATE pg_catalog."default",
    assignee text COLLATE pg_catalog."default",
    storypoints_ bigint,
    sprintenddata timestamp without time zone,
    reporter text COLLATE pg_catalog."default",
    creator text COLLATE pg_catalog."default",
    created timestamp without time zone,
    updated timestamp without time zone,
    sprintdate timestamp without time zone,
    "Last Viewed" timestamp without time zone,
    resolved timestamp without time zone,
    "Affects Version/s" double precision,
    "Component/s" text COLLATE pg_catalog."default",
    "Component/s.1" double precision,
    "Due Date" text COLLATE pg_catalog."default",
    "Labels" double precision,
    "Labels.1" double precision,
    "Labels.2" double precision,
    description double precision,
    "Environment" double precision,
    "Log Work" double precision,
    "Original Estimate" bigint,
    "Remaining Estimate" bigint,
    timespent bigint,
    "Work Ratio" double precision,
    "Σ Original Estimate" double precision,
    "Σ Remaining Estimate" double precision,
    "Σ Time Spent" double precision,
    "Security Level" double precision,
    "Inward issue link _Relates_" double precision,
    "Outward issue link _Relates_" double precision,
    "Attachment" double precision,
    "Custom field _Approvers_" double precision,
    "Custom field _Approvers_.1" double precision,
    "Custom field _Change completion date_" double precision,
    "Custom field _Change start date_" double precision,
    "Customer Request Type" double precision,
    "Custom field _Epic Colour_" double precision,
    "Custom field _Epic Link_" text COLLATE pg_catalog."default",
    "Custom field _Epic Name_" double precision,
    "Custom field _Epic Status_" double precision,
    "Custom field _Impact_" double precision,
    "Custom field _Linked major incidents_" double precision,
    "Custom field _Operational categorization_" double precision,
    "Custom field _Organizations_" double precision,
    "Custom field _Organizations_.1" double precision,
    "Custom field _Product categorization_" double precision,
    "Custom field _RAG_" text COLLATE pg_catalog."default",
    "Custom field _Rank_" text COLLATE pg_catalog."default",
    "Custom field _Request participants_" double precision,
    "Custom field _Reviewed_" text COLLATE pg_catalog."default",
    "Custom field _Root cause_" double precision,
    "Satisfaction score _out of 5_" double precision,
    "Custom field _Scripted Number Field_" bigint,
    "Unnamed: 68" double precision,
    "Unnamed: 69" double precision,
    "Time to approve normal change" double precision,
    "Time to approve normal change simplified" double precision,
    "Time to close after resolution" double precision,
    "Time to close after resolution simplified" double precision,
    "Time to first response" double precision,
    "Time to first response simplified" double precision,
    "Time to resolution" double precision,
    "Time to resolution simplified" double precision,
    "Custom field _Workaround_" double precision,
    "Comment" double precision,
    "Comment.1" double precision
)

TABLESPACE pg_default;

ALTER TABLE public.jira_bv
    OWNER to postgres;



mvn compile exec:java -Dexec.mainClass="sample.jira.export.App"

/https://zetcode.com/java/postgresql//


https://github.com/PgBulkInsert/PgBulkInsert/blob/master/PgBulkInsert/src/main/java/de/bytefish/pgbulkinsert/mapping/AbstractMapping.java


