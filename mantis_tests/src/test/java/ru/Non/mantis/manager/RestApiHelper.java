package ru.Non.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.*;
import ru.Non.mantis.model.IssueData;
import ru.Non.mantis.model.UserData;

public class RestApiHelper extends HelperBase {

    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }

    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);
        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }

    public Long createUser(UserData userData) {
        Long userId = null;
        User user = new User();
        user.setUsername(userData.name());
        user.setPassword(userData.password());
        user.setRealName(userData.realname());
        user.setEmail(userData.email());
        user.setAccessLevel(new AccessLevel().name(userData.accessLevel()));
        user.setEnabled(userData.isEnabled());
        user.setProtected(userData.isProtected());
        UserApi apiInstance = new UserApi();
        try {
            UserResponse response = apiInstance.userAdd(user).getUser();
            userId = response.getId();
        } catch (ApiException e) {
            new RuntimeException(e);
        }
        if (userId == null) {
            throw new IllegalArgumentException("Не удалось получить id нового пользователя");
        }
        return userId;
    }

    public void removeUser(Long id) {
        UserApi apiInstance = new UserApi();
        try {
            apiInstance.userDelete(id);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }
}
