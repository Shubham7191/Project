package com.jsp.CloneApiBookMyShow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneApiBookMyShow.dao.OwnerDao;
import com.jsp.CloneApiBookMyShow.dto.OwnerDto;
import com.jsp.CloneApiBookMyShow.entity.Owner;
import com.jsp.CloneApiBookMyShow.exception.OwnerIdNotFoundException;
import com.jsp.CloneApiBookMyShow.util.ResponseStructure;


@Service
public class OwnerService {
	
	@Autowired
	private OwnerDao ownerDao;

	public ResponseEntity<ResponseStructure<OwnerDto>> saveOwner(Owner owner) {
		
	 Owner	dbOwner = ownerDao.saveOwner(owner);
	 OwnerDto dto = new OwnerDto();
	 
	 dto.setOwnerId(dbOwner.getOwnerId());
	 dto.setOwnerName(dbOwner.getOwnerName());
	 dto.setOwnerEmail(dbOwner.getOwnerEmail());
	 dto.setOwnerPhoneNumber(dbOwner.getOwnerPhoneNumber());
	 
	 
	 ResponseStructure<OwnerDto> structure = new ResponseStructure<>();
	 structure.setMessage("owner saved successfully");
	 structure.setStatus(HttpStatus.CREATED.value());
	 structure.setData(dto);
		
		return  new ResponseEntity<ResponseStructure<OwnerDto>> (structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<OwnerDto>> findOwnerById(long ownerId) {	
		Owner dbOwner=ownerDao.fineOwnerById(ownerId);
		if(dbOwner!=null) {
			OwnerDto dto=new OwnerDto();
			dto.setOwnerId(dbOwner.getOwnerId());
			dto.setOwnerName(dbOwner.getOwnerName());
			dto.setOwnerEmail(dbOwner.getOwnerEmail());
			dto.setOwnerPhoneNumber(dbOwner.getOwnerPhoneNumber());
			
			ResponseStructure<OwnerDto> structure=new ResponseStructure<OwnerDto>();
			structure.setMessage("OwnerData fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure,HttpStatus.FOUND);
		}else {
			//raise ownerIdnot found exception
			throw new OwnerIdNotFoundException("sorry failed to fetch owner");
		}
	}

	

	public ResponseEntity<ResponseStructure<OwnerDto>> deleteOwnerById(long ownerId) {
		Owner dbowner= ownerDao.deleteOwnerById(ownerId);
		if (dbowner != null) {
			OwnerDto dto = new OwnerDto();
			dto.setOwnerId(dbowner.getOwnerId());
			dto.setOwnerName(dbowner.getOwnerName());
			dto.setOwnerPhoneNumber(dbowner.getOwnerPhoneNumber());
			dto.setOwnerEmail(dbowner.getOwnerEmail());
			
			ResponseStructure<OwnerDto> structure = new ResponseStructure<>();
			structure.setMessage("Owner sucessfully deleted ");
			structure.setData(dto);
			structure.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<OwnerDto>> (structure,HttpStatus.OK);
			
			
		}
		//raise ownerIdnot found exception
		throw new OwnerIdNotFoundException("sorry failed to fetch owner");
	}
	
	
	
	
	
	

	public ResponseEntity<ResponseStructure<OwnerDto>> updateOwnerById(long ownerId, Owner owner) {
		 
		Owner dbOwner=ownerDao.updateOwnerById(ownerId,owner);
		if(dbOwner!=null) {
			OwnerDto dto=new OwnerDto();
			dto.setOwnerId(dbOwner.getOwnerId());
			dto.setOwnerName(dbOwner.getOwnerName());
			dto.setOwnerEmail(dbOwner.getOwnerEmail());
			dto.setOwnerPhoneNumber(dbOwner.getOwnerPhoneNumber());
			
			ResponseStructure<OwnerDto> structure=new ResponseStructure<OwnerDto>();
			structure.setMessage("OwnerData updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure,HttpStatus.OK);
		}else {
//			raise ownerIdnot found exception
			throw new OwnerIdNotFoundException("sorry failed to fetch owner");
		}
	}
}
