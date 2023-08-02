package com.jsp.CloneApiBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneApiBookMyShow.dao.AddressDao;
import com.jsp.CloneApiBookMyShow.dao.OwnerDao;
import com.jsp.CloneApiBookMyShow.dao.TheatreDao;
import com.jsp.CloneApiBookMyShow.dto.TheatreDto;
import com.jsp.CloneApiBookMyShow.entity.Address;
import com.jsp.CloneApiBookMyShow.entity.Owner;
import com.jsp.CloneApiBookMyShow.entity.Theatre;
import com.jsp.CloneApiBookMyShow.exception.AddressIdNotFoundException;
import com.jsp.CloneApiBookMyShow.exception.OwnerIdNotFoundException;
import com.jsp.CloneApiBookMyShow.exception.TheatreAlreadyPresentInThisAddressException;
import com.jsp.CloneApiBookMyShow.exception.TheatreIdNotFoundException;
import com.jsp.CloneApiBookMyShow.util.ResponseStructure;

@Service
public class TheatreService {

	@Autowired
	private TheatreDao theatreDao;

	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ModelMapper mapper;
	
	

	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(long ownerId, long addressId, TheatreDto theatreDto) {

		Owner owner = ownerDao.fineOwnerById(ownerId);

		if (owner != null) {

			// proceed your to check address is theire or not
			Address address = addressDao.getAddressById(addressId);

			if (address != null) {

				Theatre addresstheatre = address.getTheatre();

				// checking theatre address is already present
				if (addresstheatre != null) {
					throw new TheatreAlreadyPresentInThisAddressException("sorry address is mapped other theatre");

				}
				// if address not present then we set that address
				Theatre theatre = this.mapper.map(theatreDto, Theatre.class);
				theatre.setOwner(owner);
				theatre.setAddress(address);

				// update owner

				// if theatre is not present for owner so we use arraylist

				if (owner.getTheatres().isEmpty()) {
					List<Theatre> list = new ArrayList<>();
					list.add(theatre);
					owner.setTheatres(list);

				}
				// here list is present we just adding in that
				else {
					List<Theatre> list = owner.getTheatres();
					list.add(theatre);
					owner.setTheatres(list);
				}

				// update address
				address.setTheatre(theatre);

				// now we add theare

				Theatre dbTheatre = theatreDao.saveTheatre(theatre);
				ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
				structure.setData(dbTheatre);
				structure.setMessage("theatre added sucessfully");
				structure.setStatus(HttpStatus.CREATED.value());

				return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.CREATED);

			} else {
				// raise exception AddressNotFound exception

				throw new AddressIdNotFoundException("sory failed to add theeatre ");

			}

		} else {
			// raise owner id or found exception
			throw new OwnerIdNotFoundException("sorry failed to add theatre");

		}

	}
	

	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(long theatreId, TheatreDto theatreDto) {
		Theatre theatre = mapper.map(theatreDto, Theatre.class);
		Theatre dbTheatre = theatreDao.updateTheatre(theatreId, theatre);
		if (dbTheatre != null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
			structure.setMessage("theatre Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbTheatre);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.OK);
		} else {
//			raise one exception
			throw new TheatreIdNotFoundException("sorry failed to update theatre");
		}
	}

	
	
	public ResponseEntity<ResponseStructure<Theatre>> getTheatre(long theatreId) {
		Theatre dbtheatre = theatreDao.getTheatreById(theatreId);
		if (dbtheatre != null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<>();
			structure.setMessage("Theatre fetch sucessfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbtheatre);
			
			return new ResponseEntity<ResponseStructure<Theatre>> (structure,HttpStatus.FOUND);
		}
//		raise one exception
		throw new TheatreIdNotFoundException("sorry failed to update theatre");
	}


	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatreById(long theatreId) {
		
		 Theatre dbTheatre=theatreDao.deleteTheatreById(theatreId);
		if (dbTheatre != null) {
			  ResponseStructure<Theatre>  structure=new ResponseStructure<Theatre>();
			     structure.setMessage("theatre deleted successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dbTheatre);
				return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.FOUND);
			
		}
		
		else {
			// raise one exception
			throw new TheatreIdNotFoundException("sorry failed to delete theatre");
		}
		}
}
