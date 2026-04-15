package com.PFE.demo.Dto;

import com.PFE.demo.Entity.Enum.AgeCategory;

public class AutoGroupRequest {
    private Long coachId;
    private String groupName;
    private int numberOfGroups;
    private AgeCategory age;

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public String getGroupName() {
        return groupName;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNumberOfGroups(int numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    public AgeCategory getAge() {
        return age;
    }
    public void setAge(AgeCategory age) {}
}
