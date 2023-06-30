package com.czertainly.core.dao.entity;

import com.czertainly.api.exception.NotFoundException;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name = "approval_profile")
@EntityListeners(AuditingEntityListener.class)
public class ApprovalProfile extends UniquelyIdentified {

    @Column(name = "name")
    private String name;

    @Column(name = "enabled")
    private boolean enabled;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "approvalProfile", fetch = FetchType.EAGER)
    private List<ApprovalProfileVersion> approvalProfileVersions = new ArrayList<>();

    public ApprovalProfileVersion getTheLatestApprovalProfileVersion() {
        return getApprovalProfileVersions().stream().max(Comparator.comparingInt(ApprovalProfileVersion::getVersion)).get();
    }

    public ApprovalProfileVersion getApprovalProfileVersionByVersion(final int version) throws NotFoundException {
        Optional<ApprovalProfileVersion> approvalProfileVersion = getApprovalProfileVersions().stream().filter(apv -> apv.getVersion() == version).findFirst();
        if (!approvalProfileVersion.isPresent()) {
            throw new NotFoundException("Unable to find approval profile version with version " + version);
        }
        return approvalProfileVersion.get();
    }

}
