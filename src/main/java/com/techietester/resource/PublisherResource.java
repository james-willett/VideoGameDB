package com.techietester.resource;

import com.techietester.model.Publisher;
import com.techietester.model.TopDeveloper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/publishers")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PublisherResource {

    @GET
    public List<Publisher> listPublishers() {

        return createData();
    }

    @GET
    @Path("/{publisherId}")
    public Publisher getPublisher(@PathParam("publisherId") Integer publisherId) {

        List<Publisher> publishers = createData();
        return publishers.get(publisherId - 1);
    }



    private TopDeveloper createDeveloper(int id, String name, Long salary) {
        TopDeveloper developer = new TopDeveloper();
        developer.setId(id);
        developer.setName(name);
        developer.setSalary(salary);
        return developer;
    }

    private Publisher createPublisher(int id, String name, int founded, List<TopDeveloper> developers) {
        Publisher publisher = new Publisher();
        publisher.setId(id);
        publisher.setName(name);
        publisher.setFounded(founded);
        publisher.setTopDevelopers(developers);
        return publisher;
    }

    private List<Publisher> createData() {
        TopDeveloper developer1 = createDeveloper(1, "Peter Smith", 5000L);
        TopDeveloper developer2 = createDeveloper(2, "Susan Smith", 7000L);
        TopDeveloper developer3 = createDeveloper(3, "Aaron Golding", 10000L);
        TopDeveloper developer4 = createDeveloper(4, "Bob Xing", 2000L);
        TopDeveloper developer5 = createDeveloper(5, "Peter Malting", 50000L);
        TopDeveloper developer6 = createDeveloper(6, "Anna Grandet", 4500L);

        List<TopDeveloper> developersSet1 = new ArrayList<>();
        developersSet1.add(developer1);
        developersSet1.add(developer2);
        developersSet1.add(developer3);

        List<TopDeveloper> developersSet2 = new ArrayList<>();
        developersSet2.add(developer3);
        developersSet2.add(developer4);

        List<TopDeveloper> developersSet3 = new ArrayList<>();
        developersSet3.add(developer4);
        developersSet3.add(developer5);
        developersSet3.add(developer6);

        Publisher publisher1 = createPublisher(1, "Golden Studios", 2005, developersSet1);
        Publisher publisher2 = createPublisher(2, "Amazing Games", 2010, developersSet2);
        Publisher publisher3 = createPublisher(3, "Caveman Inc", 2012, developersSet3);

        List<Publisher> publishers = new ArrayList<>();
        publishers.add(publisher1);
        publishers.add(publisher2);
        publishers.add(publisher3);

        return publishers;
    }
}
