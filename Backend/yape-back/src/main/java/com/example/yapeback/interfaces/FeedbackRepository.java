package com.example.yapeback.interfaces;

import com.example.yapeback.model.Feedback;

public interface FeedbackRepository {
    Feedback save(Feedback feedback);
    Feedback findById(Long id);
    void deleteById(Long id);
}