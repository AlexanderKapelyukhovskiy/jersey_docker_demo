package com.alkapa.tradeshift.jersey.docker.demo;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alkapa.tradeshift.jersey.docker.demo.db.CompaniesStorage;

/**
 * Root resource (exposed at "companies" path)
 */
@Path("companies")
public class Companies {

    private CompaniesStorage storage;

    public Companies() {
        String connectionString
            = "jdbc:mysql://mysql:3306/AmazingCo?user=root&password=qwerty";
        CompaniesStorage storage = new CompaniesStorage(connectionString);
        this.storage = storage;
    }

    @GET
    @Path("/{id}/child")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChildCompaniesById(@PathParam("id") int id) {
        try {
            Company[] companies = this.storage.getChildCompanies(id);
            if(companies.length == 0) {
                return Response.status(Response.Status.NOT_FOUND)
                    .entity("Company not found for id: " + id).build();
            }
            Company[] parentCompanies = this.storage.getParentCompanies(id);
            if(parentCompanies.length == 0) {
                return Response.status(Response.Status.NOT_FOUND)
                    .entity("Company not found for id: " + id).build();
            }
            Company root = parentCompanies[parentCompanies.length - 1];
            int heightDelta = root.getDepth();

            for (int i = 0; i < companies.length; i++) {
                Company c = companies[i];
                c.setRootId(root.getId());
                c.setHeight(c.getDepth() + heightDelta);
            }

            return Response.ok().entity(companies).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCompany(Company company, @PathParam("id") int id) {
        try {
            if (company == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Company can't be null").build();
            }
            int parentId = company.getParentId();

            Company[] companies = this.storage.getChildCompanies(parentId);
            if(companies.length == 0){
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Incorrect parentId").build();
            }
            this.storage.updateCompanyParentId(id, parentId);
            company.setId(id);
            return Response.ok().entity(company).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
