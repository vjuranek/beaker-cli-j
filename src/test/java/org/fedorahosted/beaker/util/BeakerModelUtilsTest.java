package org.fedorahosted.beaker.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.fedorahosted.beaker.model.TaskResult;
import org.fedorahosted.beaker.model.beakermodel.Job;
import org.fedorahosted.beaker.model.beakermodel.Task;
import org.junit.BeforeClass;
import org.junit.Test;

public class BeakerModelUtilsTest {

    private static Job job;
    
    @BeforeClass
    public static void loadJob() throws JAXBException, URISyntaxException {
        File file  = new File(Job.class.getResource("job.xml").toURI());
        JAXBContext jaxbContext = JAXBContext.newInstance(Job.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        job = (Job) jaxbUnmarshaller.unmarshal(file);
    }
    
    @Test
    public void getTaskById() {
        Task task = BeakerModelUtils.getJobTaskById(job, 687991);
        assertEquals("/distribution/reservesys", task.getName());
        assertEquals(TaskResult.Warn, TaskResult.valueOf(task.getResult()));
    }
    
    @Test
    public void getTaskName() {
        Task task = BeakerModelUtils.getJobTaskByName(job, "/distribution/reservesys");
        assertEquals(687991, Integer.decode(task.getId()).intValue());
        assertEquals(TaskResult.Warn, TaskResult.valueOf(task.getResult()));
    }
    
}
