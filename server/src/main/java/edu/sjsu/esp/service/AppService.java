package edu.sjsu.esp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.esp.dao.TechnicianDAO;
import edu.sjsu.esp.dao.WorkOrderDAO;
import edu.sjsu.esp.model.Technician;
import edu.sjsu.esp.model.WorkOrder;

@Service
public class AppService {
	 @Autowired
	 private WorkOrderDAO woRepository;
	 
	 @Autowired
	 private TechnicianDAO technicianRepository;
	 
	 public List<WorkOrder> getWorkOrderService(){
		return woRepository.listWorkOrdersQuery();
	 }
	 
	 public List<WorkOrder> getCustOrderService(Integer userId){
			return woRepository.listCustOrdersQuery(userId);
	 }
	 
	 public List<WorkOrder> getTechnicianOrderService(Integer techId){
			return woRepository.listTechnicianOrdersQuery(techId);
	 }
	 
	 public WorkOrder createWorkOrderService(WorkOrder newWorkOrder) {
		 return woRepository.save(newWorkOrder);
	 }
	 
	 public WorkOrder updateWorkOrderService(WorkOrder newWorkOrder, Integer id) {
		WorkOrder wo = woRepository.findById(id).get();
		if(newWorkOrder.getTechnician()!=null) {
			Technician t = technicianRepository.findById(newWorkOrder.getTechnician().getId()).get();
			wo.setTechnician(t);
		}
		//wo.setDescription(newWorkOrder.getDescription());
		return woRepository.save(wo);    
	 }
	 
	 public List<Technician> getTechnicianService(){
		 return technicianRepository.findAll();
	 }
}