package com.javen.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.javen.dao.IAdminDao;
import com.javen.service.IAdminService;

@Service("adminService") 
public class AdminServiceImpl implements IAdminService {
	@Resource  
    private IAdminDao adminDao;

}
