package com.navinfo.dataservice.web.codegen.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.navinfo.dataservice.api.ServiceException;
import com.navinfo.dataservice.web.codegen.model.ProjectInfo;
import com.navinfo.dataservice.commons.database.MultiDataSourceFactory;
import com.navinfo.dataservice.commons.log.LoggerRepos;
import com.navinfo.navicommons.database.QueryRunner;
import com.navinfo.navicommons.database.Page;
import com.navinfo.dataservice.commons.util.StringUtils;

import net.sf.json.JSONObject;

/** 
* @ClassName:  ProjectInfoService 
* @author code generator
* @date 2016-05-13 06:03:53 
* @Description: TODO
*/
@Service
public class ProjectInfoService {
	private Logger log = LoggerRepos.getLogger(this.getClass());

	
	public void create(JSONObject json)throws ServiceException{
		Connection conn = null;
		try{
			//持久化
			QueryRunner run = new QueryRunner();
			conn = MultiDataSourceFactory.getInstance().getManDataSource()
					.getConnection();	
			ProjectInfo  bean = (ProjectInfo)JSONObject.toBean(json, ProjectInfo.class);	
			
			String createSql = "insert into project_info (PROJECT_ID, PROJECT_NAME, DB_ID) values(?,?,?)";			
			run.update(conn, 
					   createSql, 
					   bean.getProjectId() , bean.getProjectName(), bean.getDbId()
					   );
		}catch(Exception e){
			DbUtils.rollbackAndCloseQuietly(conn);
			log.error(e.getMessage(), e);
			throw new ServiceException("创建失败，原因为:"+e.getMessage(),e);
		}finally{
			DbUtils.commitAndCloseQuietly(conn);
		}
	}
	public void update(JSONObject json)throws ServiceException{
		Connection conn = null;
		try{
			//持久化
			QueryRunner run = new QueryRunner();
			conn = MultiDataSourceFactory.getInstance().getManDataSource()
					.getConnection();	
			JSONObject obj = JSONObject.fromObject(json);	
			ProjectInfo  bean = (ProjectInfo)JSONObject.toBean(obj, ProjectInfo.class);	
			
			String updateSql = "update project_info set PROJECT_ID=?, PROJECT_NAME=?, DB_ID=? where 1=1 PROJECT_ID=? and PROJECT_NAME=? and DB_ID=?";
			List<Object> values=new ArrayList();
			if (bean!=null&&bean.getProjectId()!=null && StringUtils.isNotEmpty(bean.getProjectId().toString())){
				updateSql+=" and PROJECT_ID=? ";
				values.add(bean.getProjectId());
			};
			if (bean!=null&&bean.getProjectName()!=null && StringUtils.isNotEmpty(bean.getProjectName().toString())){
				updateSql+=" and PROJECT_NAME=? ";
				values.add(bean.getProjectName());
			};
			if (bean!=null&&bean.getDbId()!=null && StringUtils.isNotEmpty(bean.getDbId().toString())){
				updateSql+=" and DB_ID=? ";
				values.add(bean.getDbId());
			};
			run.update(conn, 
					   updateSql, 
					   bean.getProjectId() ,bean.getProjectName(),bean.getDbId(),
					   values.toArray()
					   );
		}catch(Exception e){
			DbUtils.rollbackAndCloseQuietly(conn);
			log.error(e.getMessage(), e);
			throw new ServiceException("修改失败，原因为:"+e.getMessage(),e);
		}finally{
			DbUtils.commitAndCloseQuietly(conn);
		}
	}
	public void delete(JSONObject json)throws ServiceException{
		Connection conn = null;
		try{
			//持久化
			QueryRunner run = new QueryRunner();
			conn = MultiDataSourceFactory.getInstance().getManDataSource()
					.getConnection();	
			JSONObject obj = JSONObject.fromObject(json);	
			ProjectInfo  bean = (ProjectInfo)JSONObject.toBean(obj, ProjectInfo.class);	
			
			String deleteSql = "delete from  project_info where 1=1 ";
			List<Object> values=new ArrayList();
			if (bean!=null&&bean.getProjectId()!=null && StringUtils.isNotEmpty(bean.getProjectId().toString())){
				deleteSql+=" and PROJECT_ID=? ";
				values.add(bean.getProjectId());
			};
			if (bean!=null&&bean.getProjectName()!=null && StringUtils.isNotEmpty(bean.getProjectName().toString())){
				deleteSql+=" and PROJECT_NAME=? ";
				values.add(bean.getProjectName());
			};
			if (bean!=null&&bean.getDbId()!=null && StringUtils.isNotEmpty(bean.getDbId().toString())){
				deleteSql+=" and DB_ID=? ";
				values.add(bean.getDbId());
			};
			if (values.size()==0){
	    		run.update(conn, deleteSql);
	    	}else{
	    		run.update(conn, deleteSql,values.toArray());
	    	}
	    	
		}catch(Exception e){
			DbUtils.rollbackAndCloseQuietly(conn);
			log.error(e.getMessage(), e);
			throw new ServiceException("删除失败，原因为:"+e.getMessage(),e);
		}finally{
			DbUtils.commitAndCloseQuietly(conn);
		}
	}
	public Page list(JSONObject json ,final int currentPageNum)throws ServiceException{
		Connection conn = null;
		try{
			QueryRunner run = new QueryRunner();
			conn = MultiDataSourceFactory.getInstance().getManDataSource()
					.getConnection();	
			JSONObject obj = JSONObject.fromObject(json);	
			ProjectInfo  bean = (ProjectInfo)JSONObject.toBean(obj, ProjectInfo.class);
			
			String selectSql = "select * from project_info where 1=1 ";
			List<Object> values=new ArrayList();
			if (bean!=null&&bean.getProjectId()!=null && StringUtils.isNotEmpty(bean.getProjectId().toString())){
				selectSql+=" and PROJECT_ID=? ";
				values.add(bean.getProjectId());
			};
			if (bean!=null&&bean.getProjectName()!=null && StringUtils.isNotEmpty(bean.getProjectName().toString())){
				selectSql+=" and PROJECT_NAME=? ";
				values.add(bean.getProjectName());
			};
			if (bean!=null&&bean.getDbId()!=null && StringUtils.isNotEmpty(bean.getDbId().toString())){
				selectSql+=" and DB_ID=? ";
				values.add(bean.getDbId());
			};
			ResultSetHandler rsHandler = new ResultSetHandler<Page>(){
				public Page handle(ResultSet rs) throws SQLException {
					List list = new ArrayList();
		            Page page = new Page(currentPageNum);
					while(rs.next()){
						HashMap map = new HashMap();
						page.setTotalCount(rs.getInt(QueryRunner.TOTAL_RECORD_NUM));
						map.put("projectId", rs.getInt("PROJECT_ID"));
						map.put("projectName", rs.getString("PROJECT_NAME"));
						map.put("dbId", rs.getInt("DB_ID"));
						list.add(map);
					}
					page.setResult(list);
					return page;
				}
	    		
	    	}	;
			if (values.size()==0){
	    		return run.query(currentPageNum, 20, conn, selectSql, rsHandler
						);
	    	}
	    	return run.query(currentPageNum, 20, conn, selectSql, rsHandler,values.toArray()
					);
		}catch(Exception e){
			DbUtils.rollbackAndCloseQuietly(conn);
			log.error(e.getMessage(), e);
			throw new ServiceException("查询列表失败，原因为:"+e.getMessage(),e);
		}finally{
			DbUtils.commitAndCloseQuietly(conn);
		}
		
	}
	public List<HashMap> list(JSONObject json)throws ServiceException{
		Connection conn = null;
		try{
			QueryRunner run = new QueryRunner();
			conn = MultiDataSourceFactory.getInstance().getManDataSource()
					.getConnection();	
					
			JSONObject obj = JSONObject.fromObject(json);	
			ProjectInfo  bean = (ProjectInfo)JSONObject.toBean(obj, ProjectInfo.class);	
			String selectSql = "select * from project_info where 1=1 ";
			List<Object> values=new ArrayList();
			if (bean!=null&&bean.getProjectId()!=null && StringUtils.isNotEmpty(bean.getProjectId().toString())){
				selectSql+=" and PROJECT_ID=? ";
				values.add(bean.getProjectId());
			};
			if (bean!=null&&bean.getProjectName()!=null && StringUtils.isNotEmpty(bean.getProjectName().toString())){
				selectSql+=" and PROJECT_NAME=? ";
				values.add(bean.getProjectName());
			};
			if (bean!=null&&bean.getDbId()!=null && StringUtils.isNotEmpty(bean.getDbId().toString())){
				selectSql+=" and DB_ID=? ";
				values.add(bean.getDbId());
			};
			ResultSetHandler<List<HashMap>> rsHandler = new ResultSetHandler<List<HashMap>>(){
				public List<HashMap> handle(ResultSet rs) throws SQLException {
					List<HashMap> list = new ArrayList<HashMap>();
					while(rs.next()){
						HashMap map = new HashMap();
						map.put("projectId", rs.getInt("PROJECT_ID"));
						map.put("projectName", rs.getString("PROJECT_NAME"));
						map.put("dbId", rs.getInt("DB_ID"));
						list.add(map);
					}
					return list;
				}
	    		
	    	}		;
	    	if (values.size()==0){
	    		return run.query(conn, selectSql, rsHandler
						);
	    	}
	    	return run.query(conn, selectSql, rsHandler,values.toArray()
					);
		}catch(Exception e){
			DbUtils.rollbackAndCloseQuietly(conn);
			log.error(e.getMessage(), e);
			throw new ServiceException("查询列表失败，原因为:"+e.getMessage(),e);
		}finally{
			DbUtils.commitAndCloseQuietly(conn);
		}
	}
	public HashMap query(JSONObject json)throws ServiceException{
		Connection conn = null;
		try{
			//持久化
			QueryRunner run = new QueryRunner();
			conn = MultiDataSourceFactory.getInstance().getManDataSource()
					.getConnection();	
			JSONObject obj = JSONObject.fromObject(json);	
			ProjectInfo  bean = (ProjectInfo)JSONObject.toBean(obj, ProjectInfo.class);	
			
			String selectSql = "select * from project_info where PROJECT_ID=? and PROJECT_NAME=? and DB_ID=?";
			ResultSetHandler<HashMap> rsHandler = new ResultSetHandler<HashMap>(){
				public HashMap handle(ResultSet rs) throws SQLException {
					while(rs.next()){
						HashMap map = new HashMap();
						map.put("projectId", rs.getInt("PROJECT_ID"));
						map.put("projectName", rs.getString("PROJECT_NAME"));
						map.put("dbId", rs.getInt("DB_ID"));
						return map;
					}
					return null;
				}
	    		
	    	}		;				
			return run.query(conn, 
					   selectSql,
					   rsHandler, 
					   bean.getProjectId(), bean.getProjectName(), bean.getDbId());
		}catch(Exception e){
			DbUtils.rollbackAndCloseQuietly(conn);
			log.error(e.getMessage(), e);
			throw new ServiceException("查询明细失败，原因为:"+e.getMessage(),e);
		}finally{
			DbUtils.commitAndCloseQuietly(conn);
		}
	}
	
}
