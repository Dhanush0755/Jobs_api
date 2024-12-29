package com.telusko.spring_boot_rest.repo;

import com.telusko.spring_boot_rest.JobRestController;
import com.telusko.spring_boot_rest.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {
    List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);
}



//List<JobPost> jobs = new ArrayList<>(Arrays.asList(
//        new JobPost(1, "Java Developer", "Must have good experience in core java"),
//        new JobPost(2, "Frontend Developer", "Good experience in core java"),
//        new JobPost(3, "Data Scientist", "Should have good experience in core java"),
//        new JobPost(4, "Network Engineer", "Need to have good experience in core java"),
//        new JobPost(5, "Mobile App Developer", "Necessary to have good experience in core java")
//));
//
//public List<JobPost> getAllJobs() {
//    return jobs;
//}
//
//public void addJob(JobPost job) {
//    jobs.add(job);
//    System.out.println(jobs);
//}
//
//public JobPost getJob(int postId) {
//    for(JobPost job : jobs) {
//        if(job.getPostId()==postId) {
//            return job;
//        }
//    }
//    return null;
//}
//
//public void updateJob(JobPost jobPost) {
//    for(JobPost job : jobs) {
//        if(job.getPostId()==jobPost.getPostId()) {
//            job.setPostId(jobPost.getPostId());
//            job.setPostProfile(jobPost.getPostProfile());
//            job.setPostDesc(jobPost.getPostDesc());
//            job.setReqExperience(jobPost.getReqExperience());
//            job.setPostTechStack(jobPost.getPostTechStack());
//        }
//    }
//}
//
//public void deleteJob(int postId) {
//    for(JobPost job : jobs) {
//        if(job.getPostId()==postId) {
//            jobs.remove(job);
//        }
//    }
//}