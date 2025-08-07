package com.tomassirio.practice.model.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "user_appropriateness_tests")
public class UserAppropriatenessTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // The ID of the user taking the test.
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    // The security segment the test is for, stored as a string in the database.
    @Enumerated(EnumType.STRING)
    @Column(name = "security_segment", nullable = false)
    private SecuritySegment securitySegment;

    // The overall state of the test.
    @Enumerated(EnumType.STRING)
    @Column(name = "flow_state", nullable = false)
    private TestFlowState flowState;

    // Maps a category ID to the user's status for that category.
    // This uses a separate join table to store the category status.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_test_id") // This column will be in the category_status table
    @MapKeyColumn(name = "category_id") // The key of the map (categoryId) is a column in the join table
    private Map<String, CategoryStatus> categoryStatuses;

}
