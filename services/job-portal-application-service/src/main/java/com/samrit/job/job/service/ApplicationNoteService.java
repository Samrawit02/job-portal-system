package com.samrit.job.job.service;

import com.samrit.job.dto.ApplicationNoteResponse;
import com.samrit.job.dto.ApplicationResponse;
import com.samrit.job.job.payload.AddApplicationRequest;

import java.util.List;

public interface ApplicationNoteService {

    ApplicationNoteResponse addNote(
            Long applicationId, Long employerId, AddApplicationRequest request
    );

    List<ApplicationResponse> getNotesByApplication( Long applicationId, Long employerId);
    void getNote(Long applicationId, Long noteId, Long employerId);

}
