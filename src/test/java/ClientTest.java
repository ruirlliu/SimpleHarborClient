import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.harbor.client.client.HarborClientBuilder;
import org.harbor.client.client.model.Artifact;
import org.harbor.client.client.model.ArtifactType;
import org.harbor.client.client.model.Project;
import org.harbor.client.client.model.ProjectMetadata;
import org.harbor.client.client.model.ProjectReq;
import org.harbor.client.client.model.Repository;
import org.harbor.client.client.v1.HarborClientV1;
import org.harbor.client.client.v1.HarborResponse;
import org.harbor.client.client.v1.flag.ResponseConfigure;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liurui
 * @date 2021/2/23
 */
public class ClientTest {

    HarborClientV1 clientV1 = new HarborClientBuilder()
            .setUrl("http://192.168.1.72:30000/")
            .setUsername("admin")
            .setPassword("Harbor123456")
            .setConnectionTimeout(30, TimeUnit.SECONDS)
            .enable(ResponseConfigure.FAILED_THROW)
            .buildV1();

    // --- project
    // get
    public void get() {
        Project test = clientV1.projects().withExactName("test").get();
    }

    // delete
    public void delete() {
        HarborResponse response = clientV1.projects().withExactName("test").delete();
    }

    // create
    @Test
    public void create() {
        ProjectReq req = new ProjectReq();
        req.setProjectName("test02231");
        req.setMetadata(new ProjectMetadata(false));
        HarborResponse harborResponse = clientV1.projects().create(req);
        System.out.println(harborResponse);
    }

    // ---- repository---

    //get
    @Test
    public void testRepoList() {
        List<Repository> boc = clientV1.projects().withExactName("boc").repositories().list(null);
        System.out.println(boc);
    }

    @Test
    public void testRepoGet() {
        Repository repository = clientV1.projects().withExactName("boc").repositories().repository("wartest").get();
        System.out.println(repository);
    }


    // --- artifact ---
    // list
    @Test
    public void testArtifactList() {
        List<Artifact> list = clientV1.projects().withExactName("boc").repositories().repository("wartest")
                .artifacts().list(null);
        System.out.println(list);
    }


}
