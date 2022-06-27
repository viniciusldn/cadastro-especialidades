package app.trybe.specialityapp.controller;

import app.trybe.specialityapp.model.Professional;
import app.trybe.specialityapp.service.ProfessionalService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/api/professional")
public class ProfessionalController {

  @Autowired
  private ProfessionalService service;

  /**
   * Find all.
   *
   * @param name the name
   * @return the response
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/all")
  public Response findAll(@QueryParam("filter") String name) {
    try {
      List<Professional> listProfessionals = service.findAll();
      return Response.ok(listProfessionals).build();
    } catch (Exception e) {
      String[] err = e.getMessage().split("†");
      return Response.status(Integer.parseInt(err[0]), err[1]).build();
    }
  }

  /**
   * Find by id.
   *
   * @return the response
   */
  @GET
  @Path("/{id}")
  public Response findById() {
    return Response.ok().build();
  }

  /**
   * Insert.
   *
   * @param professional the professional
   * @return the response
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/add")
  public Response insert(Professional professional) {
    try {
      service.insert(professional);
      return Response.status(201, "Inserido").build();
    } catch (Exception e) {
      String[] err = e.getMessage().split("†");
      return Response.status(Integer.parseInt(err[0]), err[1]).build();
    }
  }

  /**
   * Edits the.
   *
   * @param id           the id
   * @param professional the professional
   * @return the response
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/edit/{id}")
  public Response edit(@PathParam("id") Integer id, Professional professional) {
    try {
      service.edit(id, professional);
      return Response.status(201, "Atualizado").build();
    } catch (Exception e) {
      String[] err = e.getMessage().split("†");
      return Response.status(Integer.parseInt(err[0]), err[1]).build();
    }
  }

  /**
   * Delete.
   *
   * @param id the id
   * @return the response
   */
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/delete/{id}")
  public Response delete(@PathParam("id") Integer id) {
    try {
      service.delete(id);
      String message = String.format("ID [%d] removido", id);
      return Response.status(201, message).build();
    } catch (Exception e) {
      String[] err = e.getMessage().split("†");
      return Response.status(Integer.parseInt(err[0]), err[1]).build();
    }
  }

}
