package com.yakupselami.spring6restmvc.services;

import com.yakupselami.spring6restmvc.model.BeerCSVRecord;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface BeerCsvService {
    List<BeerCSVRecord> convertCSV(File csvFile);
}
