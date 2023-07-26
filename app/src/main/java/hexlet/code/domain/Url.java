package hexlet.code.domain;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Entity
public class Url extends Model {
    @Id
    private long id;
    private String name;
    @WhenCreated
    private Instant createdAt;
    @OneToMany
    private List<UrlCheck> urlChecks;

    public Url(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public List<UrlCheck> getUrlChecks() {
        return urlChecks;
    }

    public Instant getLatestCheck() {
        int listSize = urlChecks.size();

        if (listSize == 0) {
            return null;
        }

        UrlCheck latestCheck = urlChecks.get(listSize - 1);
        Instant latestCheckDate = latestCheck.getCreatedAt();

        return latestCheckDate;
    }

    public Integer getLatestStatus() {
        int listSize = urlChecks.size();

        if (listSize == 0) {
            return null;
        }

        UrlCheck latestCheck = urlChecks.get(listSize - 1);
        int latestCheckStatus = latestCheck.getStatusCode();

        return latestCheckStatus;
    }
}
