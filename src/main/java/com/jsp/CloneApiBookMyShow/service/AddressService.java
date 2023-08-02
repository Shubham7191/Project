package com.jsp.CloneApiBookMyShow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneApiBookMyShow.dao.AddressDao;
import com.jsp.CloneApiBookMyShow.dto.AddressDto;
import com.jsp.CloneApiBookMyShow.entity.Address;
import com.jsp.CloneApiBookMyShow.exception.AddressIdNotFoundException;
import com.jsp.CloneApiBookMyShow.util.ResponseStructure;

@Service
public class AddressService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AddressDao addressDao;
	
	

	public ResponseEntity<ResponseStructure<Address>> saveAddress(AddressDto addressDto) {
		Address address=this.modelMapper.map(addressDto, Address.class);
		Address dbAddress=addressDao.saveAddress(address);
		
		ResponseStructure<Address> structure=new ResponseStructure<Address>();
		structure.setMessage("address saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(long addressId, AddressDto addressDto) {
		Address address =this.modelMapper.map(addressDto,Address.class);
		 Address dbaddress    = addressDao.updateAddress(addressId, address);
		 
		 if (dbaddress != null) {
			 
			 ResponseStructure<Address> structure=new ResponseStructure<Address>();
				structure.setMessage("address updated successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dbaddress);
				return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.OK);
			
		}
		 else {

				throw new AddressIdNotFoundException("Failed to update Address");
		 }
		
	}


	public ResponseEntity<ResponseStructure<Address>> getAddress(long addressId) {
		Address dbaddress =addressDao.getAddressById(addressId);
		
		if (dbaddress != null) {
			ResponseStructure<Address> structure = new ResponseStructure<>();
			structure.setMessage("address fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbaddress);
			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FOUND);
			
		}
		throw new AddressIdNotFoundException("Failed to fetch Address");
		
	}
	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(long addressId){
		Address  dbAddress =addressDao.deleteAddress(addressId);
		if(dbAddress!=null) {
			ResponseStructure<Address> structure=new ResponseStructure<Address>();
			structure.setMessage("address deleted  successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FOUND);
		}else {
			throw new AddressIdNotFoundException("Failed to fetch  Address by id ");
		}
		}
	
}
