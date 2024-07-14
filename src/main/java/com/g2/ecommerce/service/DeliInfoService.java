package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.model.DeliInfo;

public interface DeliInfoService {
	List<DeliInfo> getAllDeliInfos();
	DeliInfo getDeliInfoById(int deliInfo_id);
	void saveDeliInfo(DeliInfo deliInfo);
	void deleteDeliInfo(int deliInfo_id);
//	void editDeliInfo(int deliInfo_id, DeliInfoDto deliInfoDto);
}
