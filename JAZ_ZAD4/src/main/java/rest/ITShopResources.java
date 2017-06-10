package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Comments;
import domain.ITShop;
import domain.services.ITShopService;

@Path("/ITShop")
@Stateless
public class ITShopResources {

	private ITShopService db = new ITShopService();
	
	@PersistenceContext
	EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ITShop> getAll() {
		return em.createNamedQuery("all.things", ITShop.class).getResultList();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(ITShop thing) {
		em.persist(thing);
		return Response.ok(thing.getId()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id){
		ITShop result = em.createNamedQuery("id.thing", ITShop.class)
				.setParameter("ITShopId", id)
				.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		return Response.ok(result).build();
		
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, ITShop p){
		ITShop result = em.createNamedQuery("id.thing", ITShop.class)
				.setParameter("ITShopId", id)
				.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		result.setName(p.getName());
		result.setType(p.getType());
		result.setPrice(p.getPrice());

		em.persist(result);
		return Response.ok().build();
	}
	
	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ITShop> getName(@PathParam("name") String name){
		return em.createNamedQuery("name.thing", ITShop.class)
				.setParameter("ITShopName", "%" + name + "%")
				.getResultList();		
	}
	
	@GET
	@Path("/fromPrice/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ITShop> getLowerPrice(@PathParam("price") int price){
		return em.createNamedQuery("lower.price", ITShop.class)
				.setParameter("LowerPrice", price)
				.getResultList();		
	}
	
	@GET
	@Path("/toPrice/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ITShop> getHigherPrice(@PathParam("price") int price){
		return em.createNamedQuery("higher.price", ITShop.class)
				.setParameter("HigherPrice", price)
				.getResultList();		
	}
	@GET
	@Path("/RAM")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ITShop> getRAMThings(){
		return em.createNamedQuery("ram.type", ITShop.class)
				.getResultList();		
	}
	@GET
	@Path("/Motherboard")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ITShop> getMotherboardThings(){
		return em.createNamedQuery("motherboard.type", ITShop.class)
				.getResultList();		
	}
	@GET
	@Path("/GPU")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ITShop> getGPUThings(){
		return em.createNamedQuery("GPU.type", ITShop.class)
				.getResultList();		
	}
	@GET
	@Path("/HDD")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ITShop> getHDDThings(){
		return em.createNamedQuery("HDD.type", ITShop.class)
				.getResultList();		
	}
	
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id){
		ITShop result = em.createNamedQuery("id.thing", ITShop.class)
				.setParameter("ITShopId", id)
				.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		em.remove(result);
		return Response.ok().build();
	}
	
	@GET
	@Path("/{ThingID}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comments> getComments(@PathParam("ThingID") int id){
		ITShop result = em.createNamedQuery("id.thing", ITShop.class)
				.setParameter("ITShopId", id)
				.getSingleResult();
		if(result==null)
			return null;
		return result.getComments();
	}
	
	@POST
	@Path("/{id}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCar(@PathParam("id") int id, Comments comment){
		ITShop result = em.createNamedQuery("id.thing", ITShop.class)
				.setParameter("ITShopId", id)
				.getSingleResult();
		if(result==null)
			return Response.status(404).build();
		result.getComments().add(comment);
		comment.setThing(result);
		em.persist(comment);
		return Response.ok().build();
	}
	
	
	
	@DELETE
	@Path("/{id}/comments/{commId}")
	public Response RemoveComm(@PathParam("id") int id,@PathParam("commId") int commId){
		ITShop result = em.createNamedQuery("id.thing", ITShop.class)
				.setParameter("ITShopId", id)
				.getSingleResult();
		if(result == null)
			return Response.status(404).build();
		commId--;
		result.getComments().remove(commId);
		
		
		return Response.ok().build();
		
	}
	
}
