package DanilaZaytsev.TestTask.XmlDom;

public class DepartmentInfo {
    private long id;
    private String depCode;
    private String depJob;
    private String description;

    public DepartmentInfo(long id, String depCode, String depJob, String description) {
        this.id = id;
        this.depCode = depCode;
        this.depJob = depJob;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepJob() {
        return depJob;
    }

    public void setDepJob(String depJob) {
        this.depJob = depJob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
