package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssue();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssue();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);

    }

    private Set<Issue> getIssue() throws IOException {
        String json = getExecutor()
                .execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json); //получаем json элемент
        JsonElement issues = parsed.getAsJsonObject().get("issues");//по ключу извлекаем нужную часть
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json =  getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                                  new BasicNameValuePair("description", newIssue.getDescription())))
                        .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return  parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    @Test
    public void testCreateIssueWithSkip() throws IOException {
        if(isIssueOpen(2280) == true){
            skipIfNotFixed(2280);
        } else{
            Set<Issue> oldIssues = getIssue();
            Issue newIssue = new Issue().withSubject("New issue").withDescription("Next test issue");
            int issueId = createIssue(newIssue);
            Set<Issue> newIssues = getIssue();
            oldIssues.add(newIssue.withId(issueId));
            assertEquals(newIssues, oldIssues);

        }
    }
}
