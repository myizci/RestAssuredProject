package pojo;

public class GithubPOJO {

    private String name, description;
    private boolean isPrivate,has_issues, has_projects, has_wiki;

    public GithubPOJO() {

    }

    public GithubPOJO(String name, String description, boolean isPrivate, boolean has_issues, boolean has_projects, boolean has_wiki) {
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.has_issues = has_issues;
        this.has_projects = has_projects;
        this.has_wiki = has_wiki;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHas_issues() {
        return has_issues;
    }

    public void setHas_issues(boolean has_issues) {
        this.has_issues = has_issues;
    }

    public boolean isHas_projects() {
        return has_projects;
    }

    public void setHas_projects(boolean has_projects) {
        this.has_projects = has_projects;
    }

    public boolean isHas_wiki() {
        return has_wiki;
    }

    public void setHas_wiki(boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
