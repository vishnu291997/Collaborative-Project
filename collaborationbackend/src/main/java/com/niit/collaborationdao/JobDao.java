package com.niit.collaborationdao;

import java.util.List;

import com.niit.collaborationbackend.Job;

public interface JobDao {
	void saveJob(Job job);
	List<Job>   getAllJobs();

}
