package com.alkapa.tradeshift.jersey.docker.demo;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "companies" path)
 */
@Path("companies")
public class Companies {

    @GET
    @Path("/{id}/child")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyById(@PathParam("id") int id) {
        Company c = new Company(id, "Test", 0);
        Company[] arr = new Company[2];
        arr[0] = c;
        arr[1] = c;
        return Response.ok().entity(arr).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCompany(Company company, @PathParam("id") int id) {
        return Response.ok().entity(company).build();
    }
}
