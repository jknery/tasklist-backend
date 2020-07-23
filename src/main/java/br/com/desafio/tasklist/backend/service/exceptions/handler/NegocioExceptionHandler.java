package br.com.desafio.tasklist.backend.service.exceptions.handler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.desafio.tasklist.backend.service.exceptions.NegocioException;


/**
 * Classe responsável por capturar e manipular os erros lançados através da {@link NegocioException}
 *
 * @author jose-nery
 */
@Provider
public class NegocioExceptionHandler implements ExceptionMapper<NegocioException> {

    @Override
    public Response toResponse(NegocioException e) {
       return Response
        		.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(e.getMessages()).build();
    }
}