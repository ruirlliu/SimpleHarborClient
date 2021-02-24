package org.harbor.client.client.model;

/**
 * @author liurui
 * @date 2021/2/24
 */
public enum ArtifactType implements Type {

    IMAGE("IMAGE"),

    CHART("CHART");

    private String type;
    ArtifactType(String type) {
        this.type = type;
    }

}
