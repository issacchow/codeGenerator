package com.navinfo.dataservice.web.codegen.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

/** 
* @ClassName:  ProjectInfo 
* @author code generator
* @date 2016-05-13 06:03:54 
* @Description: TODO
*/
public class ProjectInfo  {
	private Integer projectId ;
	private String projectName ;
	private Integer dbId ;
	
	public ProjectInfo (){
	}
	
	public ProjectInfo (Integer projectId ,String projectName,Integer dbId){
		this.projectId=projectId ;
		this.projectName=projectName ;
		this.dbId=dbId ;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getDbId() {
		return dbId;
	}
	public void setDbId(Integer dbId) {
		this.dbId = dbId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectInfo [projectId=" + projectId +",projectName="+projectName+",dbId="+dbId+"]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((dbId == null) ? 0 : dbId.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectInfo other = (ProjectInfo) obj;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (dbId == null) {
			if (other.dbId != null)
				return false;
		} else if (!dbId.equals(other.dbId))
			return false;
		return true;
	}
	
	
	
}
