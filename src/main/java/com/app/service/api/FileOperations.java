package com.app.service.api;

import com.app.model.DataSummary;

import java.util.List;

public interface FileOperations {

    List<DataSummary> read(String path, String date);

    void write(List<DataSummary> dataSummaryList);
}
