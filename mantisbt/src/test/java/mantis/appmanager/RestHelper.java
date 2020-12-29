package mantis.appmanager;
import mantis.model.Issue;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;


import java.io.IOException;
import java.util.Set;

public class RestHelper {

  private final ApplicationManager app;

  public RestHelper(ApplicationManager app){

    this.app = app;
  }


  private Executor getExecutor() {

    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public int getIssueId() throws  IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
            .returnContent().asString();
    JsonElement parsed = JsonParser.parseString(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issueList = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());

    return issueList.iterator().next().getId();
  }

  public int getIssueStatus(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = JsonParser.parseString(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issueSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    return issueSet.iterator().next().getStatus();
  }


}


