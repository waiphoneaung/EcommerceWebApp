package com.g2.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.model.DeliInfo;
import com.g2.ecommerce.repository.DeliInfoRepository;


@Service
public class DeliInfoServiceImpl implements DeliInfoService {
	@Autowired
	private DeliInfoRepository deliInfoRepository;
	
	@Override
	public List<DeliInfo> getAllDeliInfos(){
		return deliInfoRepository.findAll();
	}
	
	@Override
	public DeliInfo getDeliInfoById(int deliInfo_id) {
		return deliInfoRepository.findById(deliInfo_id).orElse(null);
	}
	
	@Override
	public void saveDeliInfo(DeliInfo deliInfo) {
        deliInfoRepository.save(deliInfo);
    }
	
	@Override
	public void deleteDeliInfo(int deliInfo_id) {
		DeliInfo deliInfo = getDeliInfoById(deliInfo_id);
		if (deliInfo != null) {
			deliInfoRepository.delete(deliInfo);
		}
	}
//	@Override
//	public void editDeliInfo(int deliInfo_id, DeliInfoDto deliInfoDto) {
//		DeliInfo deliInfo = getDeliInfoById(deliInfo_id);
//		
//		
//		deliInfo.getAddress().setAddress(deliInfoDto.getAddress().getAddress());
//		deliInfo.getAddress().setDeli_fees(deliInfoDto.getAddress().getDeli_fees());
//		deliInfo.getAddress().setWaiting_time(deliInfoDto.getAddress().getWaiting_time());
//		deliInfo.getDelivery().setName(deliInfoDto.getDelivery().getName());
//		deliInfo.getDelivery().setPhone(deliInfoDto.getDelivery().getPhone());
//		
//		deliInfoRepository.save(deliInfo);
//	}
}
