package com.coviwin.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.IdCardException;
import com.coviwin.model.AdharCard;
import com.coviwin.model.IdCard;
import com.coviwin.model.PanCard;
import com.coviwin.repo.IdCardServiceRepo;
import com.coviwin.service.IdCardService;

@Service
public class IdCardServiceImpl implements IdCardService {

	@Autowired
	private IdCardServiceRepo idCardRepo;
	
	@Override
	public IdCard getPanCardByNumber(String panNo)throws IdCardException {

//		IdCard idcard = idCardRepo.getIdCardByPanno(panNo);
//		
//		if(idcard != null) {
//		     return idcard;
//		}else
//			throw new IdCardException("Invalid PanNo...");
		
		PanCard panCard = new PanCard(panNo);
		
		IdCard idcard = idCardRepo.findByPancard(panCard);
		
		if (idcard == null)
			throw new IdCardException("Idcard not found with the  panNo:" + panNo);
		else
			return idcard;
		
		
	}

	@Override
	public List<IdCard> getAdharCardByNo(Long adharNo)throws IdCardException {

//		IdCard idcard = idCardRepo.getIdCardByAadharNo(adharNo);
//		
//		if(idcard != null) {
//		     return idcard;
//		}else
//			throw new IdCardException("Invalid adharNo...");
		
		AdharCard adharCard = new AdharCard(adharNo);
		
		List<IdCard> idcard = idCardRepo.findByAdharcard( adharCard );
		
		if (idcard == null || idcard.isEmpty() )
			throw new IdCardException("Idcard not found with the  adharNo:" + adharNo);
		
		
		return idcard;
		
		
	}

	@Override
	public IdCard addIdCard(IdCard id)throws IdCardException {

		// checking IdCard based on AdharCard
		List< IdCard > id1 = idCardRepo.findByAdharcard( id.getAdharcard() );
		
		if(id1.isEmpty() ) {
			
			// checking IdCard based on Pancard
			IdCard id2 = idCardRepo.findByPancard(id.getPancard());
			
			if( id2 == null )return idCardRepo.save(id);
				
			
			
		}
		
		
		throw new IdCardException("IdCard already registered with id : " + id.getId());
			
		
		
		
//		return idCardRepo.save(id);
         
	}

}
