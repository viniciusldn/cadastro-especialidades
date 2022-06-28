package app.trybe.specialityapp.commons;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ApplicationError {
  private String message;
  private Response.Status status;

  public ApplicationError(String message, Response.Status status) {
    this.status = status;
    this.message = message;
  }

  public Status getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

}
