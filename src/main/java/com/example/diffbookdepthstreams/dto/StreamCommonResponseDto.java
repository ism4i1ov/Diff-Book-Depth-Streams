package com.example.diffbookdepthstreams.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamCommonResponseDto implements Serializable {
    private String stream;
    private StreamDataResponseDto data;

    public StreamDataResponseDto getData() {
        return data;
    }

    public StreamCommonResponseDto setData(StreamDataResponseDto data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "stream='" + stream + '\'' +
                ", data=" + data +
                '}';
    }

    public String getStream() {
        return stream;
    }

    public StreamCommonResponseDto setStream(String stream) {
        this.stream = stream;
        return this;
    }
}
