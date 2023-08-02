package com.jsp.CloneApiBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneApiBookMyShow.dao.OwnerDao;
import com.jsp.CloneApiBookMyShow.dao.ProductionHouseDao;
import com.jsp.CloneApiBookMyShow.dto.ProductionHouseDto;
import com.jsp.CloneApiBookMyShow.entity.Owner;
import com.jsp.CloneApiBookMyShow.entity.ProductionHouse;
import com.jsp.CloneApiBookMyShow.exception.OwnerIdNotFoundException;
import com.jsp.CloneApiBookMyShow.exception.ProductionHouseIdNotFoundException;
import com.jsp.CloneApiBookMyShow.util.ResponseStructure;

@Service
public class ProductionHouseService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OwnerDao ownerDao;

	@Autowired
	private ProductionHouseDao houseDao;

	public ResponseEntity<ResponseStructure<ProductionHouse>> saveProductionHouse(long ownerId,
			ProductionHouseDto houseDto) {
		Owner dbOwner = ownerDao.fineOwnerById(ownerId);

		if (dbOwner != null) {
			
			ProductionHouse house = this.modelMapper.map(houseDto, ProductionHouse.class);
			if (dbOwner.getHouses().isEmpty()) {
				
				List<ProductionHouse> list = new ArrayList<ProductionHouse>();
				list.add(house);
				dbOwner.setHouses(list);

			}
			else {
				List<ProductionHouse> list=dbOwner.getHouses();
				list.add(house);
				dbOwner.setHouses(list);
			}
			house.setOwner(dbOwner);
			 ProductionHouse dbProductionHouse= houseDao.saveProductionHouse(house);
			 ResponseStructure<ProductionHouse> structure = new ResponseStructure<>();
			 structure.setMessage("productionHouse data fetched successfully");
			 structure.setStatus(HttpStatus.CREATED.value());
			 structure.setData(dbProductionHouse);
			 return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure,HttpStatus.CREATED);
		}
		else {
			//Raise one exception ownerIdisnot present
		throw new OwnerIdNotFoundException("sorry failed to add production house");
		}
	}

	
	
	public ResponseEntity<ResponseStructure<ProductionHouse>> updateProductionHouse(long houseId,
			ProductionHouseDto houseDto) {
		
		ProductionHouse house=	this.modelMapper.map(houseDto, ProductionHouse.class);
		 
		ProductionHouse dbHouse=houseDao.updateproductionHouse(houseId,house);
		if (dbHouse != null  ) {
			
			ResponseStructure<ProductionHouse> structure=new ResponseStructure<ProductionHouse>();
			structure.setMessage("ProductionHouse Update successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbHouse);
			return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure,HttpStatus.OK);
			
		}
		else {
			throw new  ProductionHouseIdNotFoundException("Sorry failed to update ProductionHouse");
		}
	}

	public ResponseEntity<ResponseStructure<ProductionHouse>> getProductionHouseById(long houseId) {
		ProductionHouse dbHouse=houseDao.getProductionHouseById(houseId);
		if(dbHouse!=null) {
			ResponseStructure<ProductionHouse> structure=new ResponseStructure<ProductionHouse>();
			structure.setMessage("ProductionHousedata fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbHouse);
			return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure,HttpStatus.FOUND);
		}else {
			throw new  ProductionHouseIdNotFoundException("Sorry failed to fetch ProductionHouse");
		}
	}



	public ResponseEntity<ResponseStructure<ProductionHouse>> deleteProductionHouseById(long houseId) {
		ProductionHouse dbHouse=houseDao.deleteProductionHouseById(houseId);
		if(dbHouse!=null) {
			ResponseStructure<ProductionHouse> structure=new ResponseStructure<ProductionHouse>();
			structure.setMessage("ProductionHousedata Deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbHouse);
			return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure,HttpStatus.FOUND);
		}else {
			throw new  ProductionHouseIdNotFoundException("Sorry failed to delete ProductionHouse");
		}
	}
	

}
