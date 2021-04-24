package sample.jira.export;
import com.atlassian.sal.api.UrlMode;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.httpclient.api.HttpClient;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.httpclient.apache.httpcomponents.DefaultHttpClientFactory;
import com.atlassian.httpclient.api.Request;
import com.atlassian.httpclient.api.factory.HttpClientFactory;
import com.atlassian.httpclient.api.factory.HttpClientOptions;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Version;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import sample.jira.export.model.IssueMapping;
import sample.jira.export.model.GWBIssue;
import com.atlassian.util.concurrent.Promise;
import java.time.LocalDate;
import org.joda.time.DateTime;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.net.URL;
import java.util.Set;
import java.util.Date;
import java.util.Iterator;
import java.io.File;
import org.joda.time.DateTimeZone;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.atlassian.sal.api.executor.ThreadLocalContextManager;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AtlassianHttpClientDecorator;
/**
 * JIRA client
 */
public class JiraClient {
    private JiraRestClient jiraRestClient;

    public JiraClient(final String baseUrl, final String userName, final String password) throws Exception {
        JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        
        URI uri = new URI(baseUrl);
     //   JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
    HttpClientOptions options = new HttpClientOptions();
    options.setTrustSelfSignedCertificates(true);
    DefaultHttpClientFactory defaultHttpClientFactory = new DefaultHttpClientFactory(new NoOpEventPublisher(),
            new RestClientApplicationProperties(uri), new ThreadLocalContextManager() {
        @Override
        public Object getThreadLocalContext() {
            return null;
        }
        
        @Override
        public void setThreadLocalContext(Object context) {
        }
        
        @Override
        public void clearThreadLocalContext() {
        }
    });
    HttpClient httpClient = defaultHttpClientFactory.create(options);

    AtlassianHttpClientDecorator atlassianHttpClientDecorator = new AtlassianHttpClientDecorator(httpClient, new BasicHttpAuthenticationHandler(userName, password)) {
        @Override
        public void destroy() throws Exception {
            defaultHttpClientFactory.dispose(httpClient);
        }
    };

   
     //   jiraRestClient = factory.createWithBasicHttpAuthentication(uri, userName, password);
     jiraRestClient = factory.create(uri, atlassianHttpClientDecorator);

   // JiraRestClient client = factory.create(baseUrl, atlassianHttpClientDecorator);



    }

    public List<GWBIssue> searchIssues(String jql) {
        SearchRestClient searchRestClient = jiraRestClient.getSearchClient();
        final Set<String> fields = new HashSet<String>();
        SearchResult result = searchRestClient.searchJql(jql, 100, 0, fields).claim();
        Iterable<Issue> issues = result.getIssues();
        List<GWBIssue> list = new ArrayList<GWBIssue>();
        for (final Issue issue : issues) {
            GWBIssue gWBIssue = new GWBIssue();
            try {
                gWBIssue.setIssueKey(issue.getKey());
                gWBIssue.setIssueType(issue.getIssueType().getName());
                gWBIssue.setIssueTitle(issue.getSummary());
               
             
                gWBIssue.setDescription(issue.getDescription());
                gWBIssue.setProjectKey(issue.getProject().getKey());
                gWBIssue.setProjectName(issue.getProject().getName());
              //  gWBIssue.setProjectleader(issue.getProject().getLead().getName());

                gWBIssue.setAssignee(issue.getAssignee().getName());
                
                gWBIssue.setReporter(issue.getReporter().getName());
                gWBIssue.setStatus(issue.getStatus().getName());

                gWBIssue.setPriority(issue.getPriority().getName());
                Object verionObj = issue.getFixVersions().iterator().next();
                if(verionObj != null){
                
                gWBIssue.setReleaseVerion(((Version)verionObj).getName());

                }
                gWBIssue.setUpdateDate(toLocalDate(issue.getUpdateDate()));
               gWBIssue.setSprintDate(toLocalDate(issue.getUpdateDate()));
              //  gWBIssue.setAge(issue.getName());

       
       
                     
               
               // getIssueFields(jiraRestClient,issue.getKey());
            } catch (Exception e) {
                //TODO: handle exception
            }
            




            list.add(gWBIssue);
        }
        return list;
    }

    private static void getIssueFields(final JiraRestClient restClient,
            String issueKEY) throws Exception {
        try {

            Promise<Issue> list = restClient.getIssueClient()
                    .getIssue(issueKEY);
            Issue issue = list.get();
            Iterable<IssueField> fields = issue.getFields();
            Iterator<IssueField> it = fields.iterator();
            System.out.println("\n"+issueKEY+"\n");
            while (it.hasNext()) {
                IssueField issueField =  it.next();
                System.out.println(issueField.getName()+"\t "+issueField.getValue());
            }

        } finally {
        }
    }

    public DateTime toDateTime(LocalDate localDate) {
        return new DateTime(DateTimeZone.UTC).withDate(
                localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()
        ).withTime(0, 0, 0, 0);
    }

    /**
     * Convert {@link org.joda.time.DateTime} to {@link java.time.LocalDate}
     */
    public LocalDate toLocalDate(DateTime dateTime) {
        DateTime dateTimeUtc = dateTime.withZone(DateTimeZone.UTC);
        return LocalDate.of(dateTimeUtc.getYear(), dateTimeUtc.getMonthOfYear(), dateTimeUtc.getDayOfMonth());
    }

    private static class NoOpEventPublisher implements EventPublisher
    {
        @Override
        public void publish(Object o) {
        }

        @Override
        public void register(Object o) {
        }

        @Override
        public void unregister(Object o) {
        }

        @Override
        public void unregisterAll() {
        }
    }

    private static class RestClientApplicationProperties implements ApplicationProperties
    {

        private final String baseUrl;

        private RestClientApplicationProperties(URI jiraURI) {
            this.baseUrl = jiraURI.getPath();
        }

        @Override
        public String getBaseUrl() {
            return baseUrl;
        }

        /**
         * We'll always have an absolute URL as a client.
         */
        @Nonnull
        @Override
        public String getBaseUrl( UrlMode urlMode) {
            return baseUrl;
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "Atlassian Jira Rest Java Client";
        }

        @Nonnull
        @Override
        public String getPlatformId() {
            return ApplicationProperties.PLATFORM_JIRA;
        }

        @Nonnull
        @Override
        public String getVersion() {
            return "";
        }

        @Nonnull
        @Override
        public Date getBuildDate() {
            throw new UnsupportedOperationException();
        }

        @Nonnull
        @Override
        public String getBuildNumber() {
            return String.valueOf(0);
        }

        @Override
        public File getHomeDirectory() {
            return new File(".");
        }

        @Override
        public String getPropertyValue(final String s) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

}

